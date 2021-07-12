package com.transcendence.core.permission;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import com.hjq.toast.ToastUtils;
import com.transcendence.core.R;
import com.transcendence.core.base.app.LibApplication;
import com.transcendence.permissions.OnPermissionCallback;
import com.transcendence.permissions.PermissionPool;
import com.transcendence.permissions.Permissions;
import com.transcendence.ui.dialog.hjq.layout.MessageDialog;

import java.util.ArrayList;
import java.util.List;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2020/10/24
 *    desc   : 权限申请回调封装
 */
public abstract class PermissionCallback implements OnPermissionCallback {

    @Override
    public void onDenied(List<String> permissions, boolean never) {
        if (never) {
            showPermissionDialog(permissions);
            return;
        }

        if (permissions.size() == 1 && PermissionPool.ACCESS_BACKGROUND_LOCATION.equals(permissions.get(0))) {
            ToastUtils.show(R.string.common_permission_fail_4);
            return;
        }

        ToastUtils.show(R.string.common_permission_fail_1);
    }

    /**
     * 显示授权对话框
     */
    protected void showPermissionDialog(List<String> permissions) {
//        Activity activity = ActivityManager.getInstance().getTopActivity();
//        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
//            return;
//        }
        new MessageDialog.Builder(LibApplication.getAppContext())
                .setTitle(R.string.common_permission_alert)
                .setMessage(getPermissionHint(LibApplication.getAppContext(), permissions))
                .setConfirm(R.string.common_permission_goto)
                .setCancel(null)
                .setCancelable(false)
                .setListener(dialog -> Permissions.startPermissionActivity(LibApplication.getAppContext(), permissions))
                .show();
    }

    /**
     * 根据权限获取提示
     */
    protected String getPermissionHint(Context context, List<String> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return context.getString(R.string.common_permission_fail_2);
        }

        List<String> hints = new ArrayList<>();
        for (String permission : permissions) {
            switch (permission) {
                case PermissionPool.READ_EXTERNAL_STORAGE:
                case PermissionPool.WRITE_EXTERNAL_STORAGE:
                case PermissionPool.MANAGE_EXTERNAL_STORAGE: {
                    String hint = context.getString(R.string.common_permission_storage);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.CAMERA: {
                    String hint = context.getString(R.string.common_permission_camera);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.RECORD_AUDIO: {
                    String hint = context.getString(R.string.common_permission_microphone);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.ACCESS_FINE_LOCATION:
                case PermissionPool.ACCESS_COARSE_LOCATION:
                case PermissionPool.ACCESS_BACKGROUND_LOCATION: {
                    String hint;
                    if (!permissions.contains(PermissionPool.ACCESS_FINE_LOCATION) &&
                            !permissions.contains(PermissionPool.ACCESS_COARSE_LOCATION)) {
                        hint = context.getString(R.string.common_permission_location_background);
                    } else {
                        hint = context.getString(R.string.common_permission_location);
                    }
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.READ_PHONE_STATE:
                case PermissionPool.CALL_PHONE:
                case PermissionPool.ADD_VOICEMAIL:
                case PermissionPool.USE_SIP:
                case PermissionPool.READ_PHONE_NUMBERS:
                case PermissionPool.ANSWER_PHONE_CALLS: {
                    String hint = context.getString(R.string.common_permission_phone);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.GET_ACCOUNTS:
                case PermissionPool.READ_CONTACTS:
                case PermissionPool.WRITE_CONTACTS: {
                    String hint = context.getString(R.string.common_permission_contacts);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.READ_CALENDAR:
                case PermissionPool.WRITE_CALENDAR: {
                    String hint = context.getString(R.string.common_permission_calendar);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.READ_CALL_LOG:
                case PermissionPool.WRITE_CALL_LOG:
                case PermissionPool.PROCESS_OUTGOING_CALLS: {
                    String hint = context.getString(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q ?
                            R.string.common_permission_call_log : R.string.common_permission_phone);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.BODY_SENSORS: {
                    String hint = context.getString(R.string.common_permission_sensors);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.ACTIVITY_RECOGNITION: {
                    String hint = context.getString(R.string.common_permission_activity_recognition);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.SEND_SMS:
                case PermissionPool.RECEIVE_SMS:
                case PermissionPool.READ_SMS:
                case PermissionPool.RECEIVE_WAP_PUSH:
                case PermissionPool.RECEIVE_MMS: {
                    String hint = context.getString(R.string.common_permission_sms);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.REQUEST_INSTALL_PACKAGES: {
                    String hint = context.getString(R.string.common_permission_install);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.NOTIFICATION_SERVICE: {
                    String hint = context.getString(R.string.common_permission_notification);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.SYSTEM_ALERT_WINDOW: {
                    String hint = context.getString(R.string.common_permission_window);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case PermissionPool.WRITE_SETTINGS: {
                    String hint = context.getString(R.string.common_permission_setting);
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        if (!hints.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String text : hints) {
                if (builder.length() == 0) {
                    builder.append(text);
                } else {
                    builder.append("、")
                            .append(text);
                }
            }
            builder.append(" ");
            return context.getString(R.string.common_permission_fail_3, builder.toString());
        }

        return context.getString(R.string.common_permission_fail_2);
    }
}
