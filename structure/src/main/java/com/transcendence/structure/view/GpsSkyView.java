package com.transcendence.structure.view;

/**
 * @Author Joephone on 2021/5/26 0026 下午 4:12
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GnssStatus;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;


public class GpsSkyView extends View implements SensorEventListener {

    public static final float MIN_VALUE_CN0 = 10.0f;
    public static final float MAX_VALUE_CN0 = 45.0f;
    public static final float MIN_VALUE_SNR = 0.0f;
    public static final float MAX_VALUE_SNR = 30.0f;

    private static final float PRN_TEXT_SCALE = 0.4f;

    private Context mContext;

    private WindowManager mWindowManager;

    private static int SAT_RADIUS;

    private float[] mCn0Thresholds;

    private int[] mCn0Colors;

    private static int mHeight;

    private static int mWidth;

    private int mSvCount;

    private double mOrientation = 0.0;

    private boolean[] mHasEphemeris;
    private boolean[] mHasAlmanac;
    private boolean[] mUsedInFix;

    private int[] mPrns;
    private int[] mConstellationType;

    private float[] mSnrCn0s;  // Holds either SNR or C/N0 - see #65
    private float[] mElevs;
    private float[] mAzims;

    private Paint mHorizonActiveFillPaint, mHorizonInactiveFillPaint, mHorizonStrokePaint,
            mGridStrokePaint, mSatelliteFillPaint, mSatelliteStrokePaint, mSatelliteUsedStrokePaint,
            mNorthPaint, mNorthFillPaint, mPrnIdPaint, mNotInViewPaint;

    public GpsSkyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        init(context);

        registerSensor();
    }

    /**
     * 用以根据手机朝向动态调整卫星的位置
     */
    private void registerSensor() {
        SensorManager sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);

        Sensor vectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (vectorSensor != null) {
            sensorManager.registerListener(this, vectorSensor, 16000); // ~60hz
        } else {
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            if (sensor != null) {
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
            }
        }
    }


    private void init(Context context) {
        SAT_RADIUS = dpToPixels(context, 5);

        int textColor = Color.parseColor("#8a000000");
        int backgroundColor = Color.WHITE;
        int satStrokeColorUsed = Color.BLACK;


        mHorizonActiveFillPaint = new Paint();
        mHorizonActiveFillPaint.setColor(backgroundColor);
        mHorizonActiveFillPaint.setStyle(Paint.Style.FILL);
        mHorizonActiveFillPaint.setAntiAlias(true);

        mHorizonInactiveFillPaint = new Paint();
        mHorizonInactiveFillPaint.setColor(Color.LTGRAY);
        mHorizonInactiveFillPaint.setStyle(Paint.Style.FILL);
        mHorizonInactiveFillPaint.setAntiAlias(true);

        mHorizonStrokePaint = new Paint();
        mHorizonStrokePaint.setColor(Color.BLACK);
        mHorizonStrokePaint.setStyle(Paint.Style.STROKE);
        mHorizonStrokePaint.setStrokeWidth(2.0f);
        mHorizonStrokePaint.setAntiAlias(true);

        mGridStrokePaint = new Paint();
        mGridStrokePaint.setColor(Color.parseColor("#ff888888"));
        mGridStrokePaint.setStyle(Paint.Style.STROKE);
        mGridStrokePaint.setAntiAlias(true);

        mSatelliteFillPaint = new Paint();
        mSatelliteFillPaint.setColor(Color.parseColor("#ffb300"));
        mSatelliteFillPaint.setStyle(Paint.Style.FILL);
        mSatelliteFillPaint.setAntiAlias(true);

        mSatelliteStrokePaint = new Paint();
        mSatelliteStrokePaint.setColor(Color.BLACK);
        mSatelliteStrokePaint.setStyle(Paint.Style.STROKE);
        mSatelliteStrokePaint.setStrokeWidth(2.0f);
        mSatelliteStrokePaint.setAntiAlias(true);

        mSatelliteUsedStrokePaint = new Paint();
        mSatelliteUsedStrokePaint.setColor(satStrokeColorUsed);
        mSatelliteUsedStrokePaint.setStyle(Paint.Style.STROKE);
        mSatelliteUsedStrokePaint.setStrokeWidth(8.0f);
        mSatelliteUsedStrokePaint.setAntiAlias(true);

        mCn0Thresholds = new float[]{MIN_VALUE_CN0, 21.67f, 33.3f, MAX_VALUE_CN0};
        mCn0Colors = new int[]{Color.GRAY,
                Color.RED,
                Color.YELLOW,
                Color.GREEN};

        mNorthPaint = new Paint();
        mNorthPaint.setColor(Color.BLACK);
        mNorthPaint.setStyle(Paint.Style.STROKE);
        mNorthPaint.setStrokeWidth(4.0f);
        mNorthPaint.setAntiAlias(true);

        mNorthFillPaint = new Paint();
        mNorthFillPaint.setColor(Color.GRAY);
        mNorthFillPaint.setStyle(Paint.Style.FILL);
        mNorthFillPaint.setStrokeWidth(4.0f);
        mNorthFillPaint.setAntiAlias(true);

        mPrnIdPaint = new Paint();
        mPrnIdPaint.setColor(textColor);
        mPrnIdPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPrnIdPaint
                .setTextSize(dpToPixels(getContext(), SAT_RADIUS * PRN_TEXT_SCALE));
        mPrnIdPaint.setAntiAlias(true);

        mNotInViewPaint = new Paint();
        mNotInViewPaint.setColor(Color.parseColor("#11000000"));
        mNotInViewPaint.setStyle(Paint.Style.FILL);
        mNotInViewPaint.setStrokeWidth(4.0f);
        mNotInViewPaint.setAntiAlias(true);

        setFocusable(true);

        // Get the proper height and width of view before drawing
        getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        mHeight = getHeight();
                        mWidth = getWidth();
                        return true;
                    }
                }
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setGnssStatus(GnssStatus status) {
        if (mPrns == null) {
            /**
             * We need to allocate arrays big enough so we don't overflow them.  Per
             * https://developer.android.com/reference/android/location/GnssStatus.html#getSvid(int)
             * 255 should be enough to contain all known satellites world-wide.
             */
            final int MAX_LENGTH = 255;
            mPrns = new int[MAX_LENGTH];
            mSnrCn0s = new float[MAX_LENGTH];
            mElevs = new float[MAX_LENGTH];
            mAzims = new float[MAX_LENGTH];
            mConstellationType = new int[MAX_LENGTH];
            mHasEphemeris = new boolean[MAX_LENGTH];
            mHasAlmanac = new boolean[MAX_LENGTH];
            mUsedInFix = new boolean[MAX_LENGTH];
        }
        int length = status.getSatelliteCount();
        mSvCount = 0;
        int svInViewCount = 0;
        int svUsedCount = 0;
        float cn0InViewSum = 0.0f;
        float cn0UsedSum = 0.0f;

        while (mSvCount < length) {
            mSnrCn0s[mSvCount] = status.getCn0DbHz(mSvCount);  // Store C/N0 values (see #65)
            mElevs[mSvCount] = status.getElevationDegrees(mSvCount);
            mAzims[mSvCount] = status.getAzimuthDegrees(mSvCount);
            mPrns[mSvCount] = status.getSvid(mSvCount);
            mConstellationType[mSvCount] = status.getConstellationType(mSvCount);
            mHasEphemeris[mSvCount] = status.hasEphemerisData(mSvCount);
            mHasAlmanac[mSvCount] = status.hasAlmanacData(mSvCount);
            mUsedInFix[mSvCount] = status.usedInFix(mSvCount);
            // If satellite is in view, add signal to calculate avg
            if (status.getCn0DbHz(mSvCount) != 0.0f) {
                svInViewCount++;
                cn0InViewSum = cn0InViewSum + status.getCn0DbHz(mSvCount);
            }
            if (status.usedInFix(mSvCount)) {
                svUsedCount++;
                cn0UsedSum = cn0UsedSum + status.getCn0DbHz(mSvCount);
            }
            mSvCount++;
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int minScreenDimen;

        minScreenDimen = (mWidth < mHeight) ? mWidth : mHeight;

        // 绘制底图
        drawHorizon(canvas, minScreenDimen);
        // 绘制方向
        drawNorthIndicator(canvas, minScreenDimen);
        // 绘制卫星
        drawSatellites(canvas, minScreenDimen);
    }

    private void drawNorthIndicator(Canvas c, int s) {
        float radius = s / 2;
        double angle = Math.toRadians(-mOrientation);
        final float ARROW_HEIGHT_SCALE = 0.05f;
        final float ARROW_WIDTH_SCALE = 0.1f;

        float x1, y1;  // Tip of arrow
        x1 = radius;
        y1 = elevationToRadius(s, 90.0f);

        float x2, y2;
        x2 = x1 + radius * ARROW_HEIGHT_SCALE;
        y2 = y1 + radius * ARROW_WIDTH_SCALE;

        float x3, y3;
        x3 = x1 - radius * ARROW_HEIGHT_SCALE;
        y3 = y1 + radius * ARROW_WIDTH_SCALE;

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x1, y1);
        path.close();

        // Rotate arrow around center point
        Matrix matrix = new Matrix();
        matrix.postRotate((float) -mOrientation, radius, radius);
        path.transform(matrix);

        c.drawPath(path, mNorthPaint);
        c.drawPath(path, mNorthFillPaint);
    }

    private void drawSatellites(Canvas canvas, int minScreenDimen) {
        if (mElevs != null) {
            int numSats = mSvCount;

            for (int i = 0; i < numSats; i++) {
                if (mElevs[i] != 0.0f || mAzims[i] != 0.0f) {
                    drawSatellite(canvas, minScreenDimen, mElevs[i], mAzims[i], mSnrCn0s[i],
                            mPrns[i], mConstellationType[i], mUsedInFix[i]);
                }
            }
        }
    }

    /**
     * @param canvas            画板
     * @param minScreenDimen    最小屏幕宽度
     * @param elev              俯仰角
     * @param azim              方位角
     * @param snrCn0            信噪比
     * @param prn               卫星标号
     * @param constellationType 星座类型
     * @param usedInFix         是否用于定位
     */
    private void drawSatellite(Canvas canvas, int minScreenDimen, float elev, float azim, float snrCn0, int prn, int constellationType, boolean usedInFix) {
        double radius, angle;
        float x, y;
        // Place PRN text slightly below drawn satellite
        final double PRN_X_SCALE = 1;
        final double PRN_Y_SCALE = 2.5;

        Paint fillPaint;
        if (snrCn0 == 0.0f) {
            // Satellite can't be seen
            fillPaint = mNotInViewPaint;
        } else {
            // Calculate fill color based on signal strength
            fillPaint = getSatellitePaint(mSatelliteFillPaint, snrCn0);
        }

        Paint strokePaint;
        if (usedInFix) {
            strokePaint = mSatelliteUsedStrokePaint;
        } else {
            strokePaint = mSatelliteStrokePaint;
        }
        // 根据俯仰角求出所在卫星图半径
        radius = elevationToRadius(minScreenDimen, elev);
        azim -= mOrientation;
        angle = (float) Math.toRadians(azim);
        // 根据方位角求出落在圆周的那个位置
        x = (float) ((minScreenDimen / 2) + (radius * Math.sin(angle)));
        y = (float) ((minScreenDimen / 2) - (radius * Math.cos(angle)));

        // 根据卫星类型产生不同形状
        GnssType operator = getGnssConstellationType(constellationType);

        switch (operator) {
            case NAVSTAR:
                // 圆形
                canvas.drawCircle(x, y, SAT_RADIUS, fillPaint);
                canvas.drawCircle(x, y, SAT_RADIUS, strokePaint);
                break;
            case GLONASS:
                // 矩形
                canvas.drawRect(x - SAT_RADIUS, y - SAT_RADIUS, x + SAT_RADIUS, y + SAT_RADIUS,
                        fillPaint);
                canvas.drawRect(x - SAT_RADIUS, y - SAT_RADIUS, x + SAT_RADIUS, y + SAT_RADIUS,
                        strokePaint);
                break;
            case QZSS:
                // 六角形
                drawHexagon(canvas, x, y, fillPaint, strokePaint);
                break;
            case BEIDOU:
                // 五角星
                drawPentagon(canvas, x, y, fillPaint, strokePaint);
                break;
            case GALILEO:
                // 三角形
                drawTriangle(canvas, x, y, fillPaint, strokePaint);
                break;
            case IRNSS:
                // 椭圆
                drawOval(canvas, x, y, fillPaint, strokePaint);
                break;
            case SBAS:
                // 钻石
                drawDiamond(canvas, x, y, fillPaint, strokePaint);
                break;
        }
        if (usedInFix) {
            canvas.drawText(String.valueOf(snrCn0), x - (int) (SAT_RADIUS * PRN_X_SCALE),
                    y + (int) (SAT_RADIUS * PRN_Y_SCALE), mPrnIdPaint);
        }
    }


    private void drawTriangle(Canvas c, float x, float y, Paint fillPaint, Paint strokePaint) {
        float x1, y1;  // Top
        x1 = x;
        y1 = y - SAT_RADIUS;

        float x2, y2; // Lower left
        x2 = x - SAT_RADIUS;
        y2 = y + SAT_RADIUS;

        float x3, y3; // Lower right
        x3 = x + SAT_RADIUS;
        y3 = y + SAT_RADIUS;

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x1, y1);
        path.close();

        c.drawPath(path, fillPaint);
        c.drawPath(path, strokePaint);
    }

    private void drawDiamond(Canvas c, float x, float y, Paint fillPaint, Paint strokePaint) {
        Path path = new Path();
        path.moveTo(x, y - SAT_RADIUS);
        path.lineTo(x - SAT_RADIUS * 1.5f, y);
        path.lineTo(x, y + SAT_RADIUS);
        path.lineTo(x + SAT_RADIUS * 1.5f, y);
        path.close();

        c.drawPath(path, fillPaint);
        c.drawPath(path, strokePaint);
    }

    private void drawPentagon(Canvas c, float x, float y, Paint fillPaint, Paint strokePaint) {
        Path path = new Path();
        path.moveTo(x, y - SAT_RADIUS);
        path.lineTo(x - SAT_RADIUS, y - (SAT_RADIUS / 3));
        path.lineTo(x - 2 * (SAT_RADIUS / 3), y + SAT_RADIUS);
        path.lineTo(x + 2 * (SAT_RADIUS / 3), y + SAT_RADIUS);
        path.lineTo(x + SAT_RADIUS, y - (SAT_RADIUS / 3));
        path.close();

        c.drawPath(path, fillPaint);
        c.drawPath(path, strokePaint);
    }

    private void drawHexagon(Canvas c, float x, float y, Paint fillPaint, Paint strokePaint) {
        final float MULTIPLIER = 0.6f;
        final float SIDE_MULTIPLIER = 1.4f;
        Path path = new Path();
        // Top-left
        path.moveTo(x - SAT_RADIUS * MULTIPLIER, y - SAT_RADIUS);
        // Left
        path.lineTo(x - SAT_RADIUS * SIDE_MULTIPLIER, y);
        // Bottom
        path.lineTo(x - SAT_RADIUS * MULTIPLIER, y + SAT_RADIUS);
        path.lineTo(x + SAT_RADIUS * MULTIPLIER, y + SAT_RADIUS);
        // Right
        path.lineTo(x + SAT_RADIUS * SIDE_MULTIPLIER, y);
        // Top-right
        path.lineTo(x + SAT_RADIUS * MULTIPLIER, y - SAT_RADIUS);
        path.close();

        c.drawPath(path, fillPaint);
        c.drawPath(path, strokePaint);
    }

    private void drawOval(Canvas c, float x, float y, Paint fillPaint, Paint strokePaint) {
        RectF rect = new RectF(x - SAT_RADIUS * 1.5f, y - SAT_RADIUS, x + SAT_RADIUS * 1.5f, y + SAT_RADIUS);

        c.drawOval(rect, fillPaint);
        c.drawOval(rect, strokePaint);
    }

    private Paint getSatellitePaint(Paint base, float snrCn0) {
        Paint newPaint;
        newPaint = new Paint(base);
        newPaint.setColor(getSatelliteColor(snrCn0));
        return newPaint;
    }

    public synchronized int getSatelliteColor(float snrCn0) {
        int numSteps;
        final float[] thresholds;
        final int[] colors;

        numSteps = mCn0Thresholds.length;
        thresholds = mCn0Thresholds;
        colors = mCn0Colors;

        if (snrCn0 <= thresholds[0]) {
            return colors[0];
        }

        if (snrCn0 >= thresholds[numSteps - 1]) {
            return colors[numSteps - 1];
        }

        for (int i = 0; i < numSteps - 1; i++) {
            float threshold = thresholds[i];
            float nextThreshold = thresholds[i + 1];
            if (snrCn0 >= threshold && snrCn0 <= nextThreshold) {
                int c1, r1, g1, b1, c2, r2, g2, b2, c3, r3, g3, b3;
                float f;

                c1 = colors[i];
                r1 = Color.red(c1);
                g1 = Color.green(c1);
                b1 = Color.blue(c1);

                c2 = colors[i + 1];
                r2 = Color.red(c2);
                g2 = Color.green(c2);
                b2 = Color.blue(c2);

                f = (snrCn0 - threshold) / (nextThreshold - threshold);

                r3 = (int) (r2 * f + r1 * (1.0f - f));
                g3 = (int) (g2 * f + g1 * (1.0f - f));
                b3 = (int) (b2 * f + b1 * (1.0f - f));
                c3 = Color.rgb(r3, g3, b3);

                return c3;
            }
        }
        return Color.MAGENTA;
    }


    private void drawHorizon(Canvas c, int s) {
        float radius = s / 2;

        c.drawCircle(radius, radius, radius, mHorizonActiveFillPaint);
        drawLine(c, 0, radius, 2 * radius, radius);
        drawLine(c, radius, 0, radius, 2 * radius);

        c.drawCircle(radius, radius, elevationToRadius(s, 60.0f), mGridStrokePaint);
        c.drawCircle(radius, radius, elevationToRadius(s, 30.0f), mGridStrokePaint);
        c.drawCircle(radius, radius, elevationToRadius(s, 0.0f), mGridStrokePaint);
        c.drawCircle(radius, radius, radius, mHorizonStrokePaint);
    }

    private float elevationToRadius(int s, float elev) {
        return ((s / 2) - SAT_RADIUS) * (1.0f - (elev / 90.0f));
    }

    private void drawLine(Canvas c, float x1, float y1, float x2, float y2) {
        // rotate the line based on orientation
        double angle = Math.toRadians(-mOrientation);
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        float centerX = (x1 + x2) / 2.0f;
        float centerY = (y1 + y2) / 2.0f;
        x1 -= centerX;
        y1 = centerY - y1;
        x2 -= centerX;
        y2 = centerY - y2;

        float X1 = cos * x1 + sin * y1 + centerX;
        float Y1 = -(-sin * x1 + cos * y1) + centerY;
        float X2 = cos * x2 + sin * y2 + centerX;
        float Y2 = -(-sin * x2 + cos * y2) + centerY;

        c.drawLine(X1, Y1, X2, Y2, mGridStrokePaint);
    }


    private static boolean mTruncateVector = false;

    private static float[] mRotationMatrix = new float[16];

    private static float[] mRemappedMatrix = new float[16];

    private static float[] mValues = new float[3];

    private static float[] mTruncatedRotationVector = new float[4];

    boolean mFaceTrueNorth;

    private GeomagneticField mGeomagneticField;

    @Override
    public void onSensorChanged(SensorEvent event) {
        double orientation = Double.NaN;
        double tilt = Double.NaN;

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ROTATION_VECTOR:
                // Modern rotation vector sensors
                if (!mTruncateVector) {
                    try {
                        SensorManager.getRotationMatrixFromVector(mRotationMatrix, event.values);
                    } catch (IllegalArgumentException e) {
                        // On some Samsung devices, an exception is thrown if this vector > 4 (see #39)
                        // Truncate the array, since we can deal with only the first four values
                        mTruncateVector = true;
                        // Do the truncation here the first time the exception occurs
                        getRotationMatrixFromTruncatedVector(event.values);
                    }
                } else {
                    // Truncate the array to avoid the exception on some devices (see #39)
                    getRotationMatrixFromTruncatedVector(event.values);
                }

                int rot = mWindowManager.getDefaultDisplay().getRotation();
                switch (rot) {
                    case Surface.ROTATION_0:
                        // No orientation change, use default coordinate system
                        SensorManager.getOrientation(mRotationMatrix, mValues);
                        // Log.d(MODE_MAP, "Rotation-0");
                        break;
                    case Surface.ROTATION_90:
                        // Log.d(MODE_MAP, "Rotation-90");
                        SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_Y,
                                SensorManager.AXIS_MINUS_X, mRemappedMatrix);
                        SensorManager.getOrientation(mRemappedMatrix, mValues);
                        break;
                    case Surface.ROTATION_180:
                        // Log.d(MODE_MAP, "Rotation-180");
                        SensorManager
                                .remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_X,
                                        SensorManager.AXIS_MINUS_Y, mRemappedMatrix);
                        SensorManager.getOrientation(mRemappedMatrix, mValues);
                        break;
                    case Surface.ROTATION_270:
                        // Log.d(MODE_MAP, "Rotation-270");
                        SensorManager
                                .remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_Y,
                                        SensorManager.AXIS_X, mRemappedMatrix);
                        SensorManager.getOrientation(mRemappedMatrix, mValues);
                        break;
                    default:
                        // This shouldn't happen - assume default orientation
                        SensorManager.getOrientation(mRotationMatrix, mValues);
                        // Log.d(MODE_MAP, "Rotation-Unknown");
                        break;
                }
                orientation = Math.toDegrees(mValues[0]);  // azimuth
                tilt = Math.toDegrees(mValues[1]);
                break;
            case Sensor.TYPE_ORIENTATION:
                // Legacy orientation sensors
                orientation = event.values[0];
                break;
            default:
                // A sensor we're not using, so return
                return;
        }

        // Correct for true north, if preference is set
        if (mFaceTrueNorth && mGeomagneticField != null) {
            orientation += mGeomagneticField.getDeclination();
            // Make sure value is between 0-360
            orientation = mod((float) orientation, 360.0f);
        }

        mOrientation = orientation;
        invalidate();
    }

    private static float mod(float a, float b) {
        return (a % b + b) % b;
    }

    private void getRotationMatrixFromTruncatedVector(float[] vector) {
        System.arraycopy(vector, 0, mTruncatedRotationVector, 0, 4);
        SensorManager.getRotationMatrixFromVector(mRotationMatrix, mTruncatedRotationVector);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private enum GnssType {
        NAVSTAR, GLONASS, GALILEO, QZSS, BEIDOU, IRNSS, SBAS, UNKNOWN;

        /**
         * Converts from the string representation of GnssType to the enum, or null if the input is
         * unknown
         *
         * @param gnssType string representation of GnssType
         * @return the GnssType enum, or null if the input is unknown
         */
        public static GnssType fromString(String gnssType) {
            switch (gnssType) {
                case "NAVSTAR":
                    return NAVSTAR;
                case "GLONASS":
                    return GLONASS;
                case "GALILEO":
                    return GALILEO;
                case "QZSS":
                    return QZSS;
                case "BEIDOU":
                    return BEIDOU;
                case "IRNSS":
                    return IRNSS;
                case "SBAS":
                    return SBAS;
                case "UNKNOWN":
                    return UNKNOWN;
                default:
                    return null;
            }
        }
    }

    private static GnssType getGnssConstellationType(int gnssConstellationType) {
        switch (gnssConstellationType) {
            case GnssStatus.CONSTELLATION_GPS:
                return GnssType.NAVSTAR;
            case GnssStatus.CONSTELLATION_GLONASS:
                return GnssType.GLONASS;
            case GnssStatus.CONSTELLATION_BEIDOU:
                return GnssType.BEIDOU;
            case GnssStatus.CONSTELLATION_QZSS:
                return GnssType.QZSS;
            case GnssStatus.CONSTELLATION_GALILEO:
                return GnssType.GALILEO;
            case GnssStatus.CONSTELLATION_IRNSS:
                return GnssType.IRNSS;
            case GnssStatus.CONSTELLATION_SBAS:
                return GnssType.SBAS;
            case GnssStatus.CONSTELLATION_UNKNOWN:
                return GnssType.UNKNOWN;
            default:
                return GnssType.UNKNOWN;
        }
    }

    private static int dpToPixels(Context context, float dp) {
        // Get the screen's density scale
        final float scale = context.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }
}
