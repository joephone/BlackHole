//package com.transcendence.structure.magazine.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Build;
//import android.support.annotation.RequiresApi;
//import android.util.Log;
//import android.view.View;
//import android.webkit.WebResourceRequest;
//import android.webkit.WebResourceResponse;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.example.ziyu.themostdemo.Adapter.CommentAdapter;
//import com.example.ziyu.themostdemo.Adapter.ProductAdapter;
//import com.example.ziyu.themostdemo.Base.SwipeBackActivity;
//import com.example.ziyu.themostdemo.Constants;
//import com.example.ziyu.themostdemo.DB.DataBaseUtil;
//import com.example.ziyu.themostdemo.Entity.MagazineInfoEntity;
//import com.example.ziyu.themostdemo.R;
//import com.example.ziyu.themostdemo.Util.RetrofitUtil;
//import com.example.ziyu.themostdemo.Weight.MyGridView;
//import com.example.ziyu.themostdemo.Weight.MyListView;
//import com.google.gson.Gson;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import butterknife.Bind;
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.onekeyshare.OnekeyShare;
//
///**
// * Created by Ziyu on 2016/11/1.
// */
//
//public class MagazineInfoActivity extends SwipeBackActivity implements RetrofitUtil.DownListener {
//
//    int id;
//
//    @Bind(R.id.webView)
//    WebView webView;
//    @Bind(R.id.avatar)
//    ImageView avatar;
//    @Bind(R.id.name)
//    TextView name;
//    @Bind(R.id.adress)
//    TextView adress;
//    @Bind(R.id.title)
//    TextView title;
//    @Bind(R.id.subTitle)
//    TextView subTitle;
//    @Bind(R.id.image)
//    ImageView image;
//    @Bind(R.id.author_name)
//    TextView author_name;
//    @Bind(R.id.author_sign)
//    TextView author_sign;
//    @Bind(R.id.author_avatar)
//    ImageView author_avatar;
//    @Bind(R.id.designer_tab)
//    RelativeLayout designerTab;
//    @Bind(R.id.frame)
//    LinearLayout frame;
//    @Bind(R.id.designer_info)
//    LinearLayout DInfo;
//    @Bind(R.id.designer_avatar)
//    ImageView dAvatar;
//    @Bind(R.id.designer_name)
//    TextView dName;
//    @Bind(R.id.designer_label)
//    TextView dLabel;
//    @Bind(R.id.designer_disc)
//    TextView dDisc;
//    @Bind(R.id.designerTitle)
//    TextView dTitle;
//    @Bind(R.id.commentTitle)
//    TextView cTitle;
//    @Bind(R.id.productList)
//    MyGridView gridView;
//    @Bind(R.id.commentList)
//    MyListView listView;
//    @Bind(R.id.look_more)
//    Button button;
//    @Bind(R.id.edit)
//    TextView edit;
//    @Bind(R.id.comment)
//    ImageView comment;
//    @Bind(R.id.allComment)
//    Button allComment;
//    @Bind(R.id.attention)
//    CheckBox attention;
//    @Bind(R.id.like)
//    CheckBox like;
//
//    private CommentAdapter commentAdapter;
//    private ProductAdapter productAdapter;
//
//    private MagazineInfoEntity entity;
//
//    private View.OnClickListener commentListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(MagazineInfoActivity.this, MagazineCommentActivity.class);
//            intent.putExtra("comment", (Serializable) entity.getData().getComments());
//            startActivity(intent);
//        }
//    };
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public int getContentId() {
//        return R.layout.activity_magazine;
//    }
//
//    @Override
//    public View getContentView() {
//        return null;
//    }
//
//    @Override
//    public void init() {
//        super.init();
//        Intent intent = getIntent();
//        id = intent.getIntExtra("id", 1);
//        Log.d("print", "init: " + id);
//
//        productAdapter = new ProductAdapter(this);
//        gridView.setAdapter(productAdapter);
//        commentAdapter = new CommentAdapter(this);
//        listView.setAdapter(commentAdapter);
//
//        initWebView(webView);
//
//        //评论跳转监听
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(MagazineInfoActivity.this, MagazineCommentActivity.class);
//                intent.putExtra("comment", (Serializable) entity.getData().getComments());
//                startActivity(intent);
//            }
//        });
//        allComment.setOnClickListener(commentListener);
//        comment.setOnClickListener(commentListener);
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MagazineInfoActivity.this, MagazineCommentActivity.class);
//                intent.putExtra("comment", (Serializable) entity.getData().getComments());
//                intent.putExtra("isEdit", true);
//                startActivity(intent);
//            }
//        });
//        //// TODO: 2016/11/13  attention Designer
//        //attention
//        attention.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    buttonView.setText("已关注");
//
//                    return;
//                }
//                buttonView.setText("+关注");
//
//            }
//        });
//
//        //like
//        like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    if (!new DataBaseUtil().getInstance(getApplicationContext()).isExist(entity.getData().getId(),"magazine","magazineId")){
//                     new DataBaseUtil().getInstance(getApplicationContext()).saveMagazine(entity.getData().getId());}
//                }else{
//                    new DataBaseUtil().getInstance(getApplicationContext()).removeMagazine(entity.getData().getId());
//                }
//
//            }
//        });
//
//
//
//
//    }
//
//    /**
//     * css属性参考
//     */
//    public final static String CSS_STYLE = "<style>* {font-size:16px;line-height:20px;} p {color:#333;} a {color:#3E62A6;} img {max-width:310px;} pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;}</style>";
//    /**
//     * 加载webView 优化显示效果
//     */
//    public void subWebView(WebView webView, String html) {
//        if (html != null) {
//            String imgStyle = "<style> p{font-size:16px;line-height:30px;} img{ max-width:100%;height:auto;} </style>";
//            String reg = "style=\"([^\"]+)\"";
//            Pattern pattern = Pattern.compile(reg);
//            Matcher matcher = pattern.matcher(html);
//            String str = matcher.replaceAll("") + imgStyle;
//            String h = str.replaceAll("<p><img", "<p align=center><img");
//            webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH); //极速渲染...
//            webView.loadDataWithBaseURL(null, h, "text/html", "UTF-8", null);
//        }
//    }
//
//    /**
//     * WeiView一些设置
//     */
//    public void initWebView(WebView webView) {
//        webView.setVerticalScrollBarEnabled(false); //关闭滚动条
//        // 设置缓存模式 首选本地缓存(静态网页)
//        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webView.getSettings().setJavaScriptEnabled(true);// JS支持
//        // 添加js交互接口类，并起别名 imageListener
//        webView.addJavascriptInterface(new JavascriptInterface(this), "imageListener");
//        webView.setWebViewClient(new MyWebViewClient());
//    }
//
//    // 注入js函数监听
//    private void addImageClickListener() {
//        // 这段js函数的功能是，遍历所有的img节点,并添加onclick函数,
//        // 函数的功能是在图片点击的时候调用本地java接口并传递url过去
//        webView.loadUrl("javascript:(function(){" +
//                "var objs = document.getElementsByTagName(\"img\"); " +
//                "for(var i=0;i<objs.length;i++)  " +
//                "{ "
//                + "    objs[i].onclick=function()  " +
//                "    {  "
//                + "        window.imageListener.openImage(this.src);  " +
//                "    }  " +
//                "}" +
//                "})()");
//    }
//
//    /**
//     *  JS通信接口
//     */
//    public class JavascriptInterface {
//        private Context context;
//        public JavascriptInterface(Context context) {
//            this.context = context;
//        }
//        /**
//         *添加注释 否则无法响应
//         * @param img
//         */
//        @android.webkit.JavascriptInterface
//        public void openImage(String img) {
//            Intent intent = new Intent(context, MagazinePhotoActivity.class);
//            intent.putExtra("url",img);
//            context.startActivity(intent);
//            overridePendingTransition(0, R.anim.slide_finish);
//            EventBus.getDefault().postSticky(photos);
//        }
//
//    }
//
//    List<String> photos = new ArrayList<>();
//    // 监听
//    private class MyWebViewClient extends WebViewClient {
//        /**
//         * 拦截jpeg后缀请求 使用Retrofit下载图片
//         * @param view
//         * @param request
//         * @return
//         */
//        @Override
//        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//            final String url = request.getUrl().toString();
//            photos.add(url);
//           /* if (url.endsWith(".jpeg")) {
//                photos.add(url);
//                Log.d("print", "shouldInterceptRequest: " + url);
//                final WebResourceResponse[] response = new WebResourceResponse[1];
//                new RetrofitUtil(MagazineInfoActivity.this)
//                        .downBitmap(url,0x001)
//                        .setBitmapListener(new RetrofitUtil.DownBitmapListener() {
//                            @Override
//                            public void downBitmapSucc(Bitmap bitmap, int requestCode) {
//                                Log.d("print", "downBitmapSucc: " + bitmap);
//                                //缓存图片
//                              //  DiskLruCacheUtil.setCache(url,bitmap);
//                                ByteArrayOutputStream bais = new ByteArrayOutputStream();
//                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bais);
//                                InputStream isBm = new ByteArrayInputStream(bais.toByteArray());
//                                 response[0] = new WebResourceResponse("image/jpeg","utf-8", isBm);
//                            }
//                        });
//                return response[0];
//              }*/
//            return super.shouldInterceptRequest(view, request);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            Log.d("print", "onPageFinished: ");
//            // html加载完成之后，添加监听图片的点击js函数
//            addImageClickListener();
//        }
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            super.onPageStarted(view, url, favicon);
//        }
//    }
//
//    @Override
//    public void loadData() {
//        String url = String.format(Constants.MAGAZINE_DETAIL, id);
//        new RetrofitUtil(this).setDownListener(this).downJson(url, 0x001);
//    }
//
//    @Override
//    public Object paresJson(String json, int requestCode) {
//        return new Gson().fromJson(json, MagazineInfoEntity.class);
//    }
//
//    @Override
//    public void downSucc(Object object, int requestCode) {
//        entity = (MagazineInfoEntity) object;
//        //图片优先
//        Glide.with(this).load(entity.getData().getAuthor().getAvatar_url()).into(author_avatar);
//        Glide.with(this).load(entity.getData().getImage_url()).into(image);
//        //开启image'的图片缓存 获取bitmap后关闭 磁盘缓存图片
//        image.setDrawingCacheEnabled(true);
//        photos.add(entity.getData().getImage_url());
//      //  DiskLruCacheUtil.setCache(entity.getData().getImage_url(),image.getDrawingCache());
//        image.setDrawingCacheEnabled(false);
//
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MagazineInfoActivity.this, MagazinePhotoActivity.class);
//                intent.putExtra("url",entity.getData().getImage_url());
//                startActivity(intent);
//
//                EventBus.getDefault().postSticky(photos);
//            }
//        });
//
//        //designerTAB
//        if (entity.getData().getDesigners().size() >= 1) {
//            Glide.with(this).load(entity.getData().getDesigners().get(0).getAvatar_url()).into(avatar);
//            Glide.with(this).load(entity.getData().getDesigners().get(0).getAvatar_url()).into(dAvatar);
//            name.setText(entity.getData().getDesigners().get(0).getName());
//            adress.setText(entity.getData().getDesigners().get(0).getCity());
//            dName.setText(entity.getData().getDesigners().get(0).getName());
//            dLabel.setText(entity.getData().getDesigners().get(0).getLabel());
//            dDisc.setText(entity.getData().getDesigners().get(0).getDescription());
//            dTitle.setText("设计师&作品");
//            //product
//            productAdapter.addDatas(entity.getData().getRefer_products());
//            if (entity.getData().getRefer_products().size() <= 10)
//                button.setVisibility(View.GONE);
//        } else {
//            designerTab.setVisibility(View.GONE);
//            DInfo.setVisibility(View.GONE);
//            gridView.setVisibility(View.GONE);
//            dTitle.setVisibility(View.GONE);
//            button.setVisibility(View.GONE);
//        }
//        //Conetnt
//        title.setText(entity.getData().getTitle());
//        subTitle.setText(entity.getData().getSub_title());
//
//        //webView.loadUrl(entity.getData().getWeb_url());
//        subWebView(webView, entity.getData().getContent());
//
//        //author
//        author_name.setText(entity.getData().getAuthor().getUsername());
//        author_sign.setText(entity.getData().getAuthor().getSign());
//
//        //comment
//        cTitle.setText("评论（" + entity.getData().getComment_num() + "）");
//        commentAdapter.addDatas(entity.getData().getComments());
//
//        //like
//        //Todo like
//        if (new DataBaseUtil().getInstance(this).isExist(entity.getData().getId(), "magazine", "magazineId")){
//            like.setChecked(true);
//        }
//
//        //收起遮障页
//        frame.setVisibility(View.GONE);
//    }
//
//
//    public void shareClick(View view){
//        ShareSDK.initSDK(this);
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//       // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle("有物画报");
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText(entity.getData().getTitle() + " : " + entity.getData().getWeb_url());
//        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl(entity.getData().getImage_url());
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl(entity.getData().getWeb_url());
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("ShareSDK");
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
//
//// 启动分享GUI
//        oks.show(this);
//    }
//
//}
