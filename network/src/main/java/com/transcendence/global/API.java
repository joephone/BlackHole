package com.transcendence.global;

/**
 * @author Joephone on 2019/12/31 11:35
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class API {

    // wanandroid、gankio、时光网
    public final static String API_GANK_IO = "https://gank.io/api/";
    public final static String API_DOUBAN = "Https://api.douban.com/";
    public final static String API_TING = "https://tingapi.ting.baidu.com/v1/restserver/";
    public final static String API_FIR = "http://api.fir.im/apps/";
    public final static String API_WAN_ANDROID = "https://www.wanandroid.com/";
    public final static String API_QSBK = "http://m2.qiushibaike.com/";
    public final static String API_MTIME = "https://api-m.mtime.cn/";
    public final static String API_MTIME_TICKET = "https://ticket-api-m.mtime.cn/";
    /**
     * 分页数据，每页的数量
     */
    public static int per_page_more = 20;


    public class GANK_IO {
        /**
         * 分类数据 API
         https://gank.io/api/v2/data/category/<category>/type/<type>/page/<page>/count/<count>
         请求方式: GET
         注:

         category 可接受参数 All(所有分类) | Article | GanHuo | Girl
         type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
         count: [10, 50]
         page: >=1
         示例:

         获取妹子列表
         https://gank.io/api/v2/data/category/Girl/type/Girl/page/1/count/10
         获取Android干货列表
         https://gank.io/api/v2/data/category/GanHuo/type/Android/page/1/count/10
         */

        public final static String GIRLS = API_GANK_IO + "v2/data/category/Girl/type/Girl/page/1/count/2";
    }


    public static class WAN {


        public final static String WX_ARTICLE_LIST = API_WAN_ANDROID +"wxarticle/list/{id}/{page}/json";

        /**
         * 获取公众号列表
         * get
         */
        public final static String WX_ARTICLE_CHAPTER = API_WAN_ANDROID + "wxarticle/chapters/json";

        public final static String DAMA_ARTICLE_LIST  = API_WAN_ANDROID +  String.format("user_article/list/%d/json",0);   //{page}



        public static String shortDamaArticleList(int page){
            return "user_article/list/"+page+"/json";
        }

        /**
         * 5. 登录与注册
         * 5.1 登录
         * POST
         */
        public final static String LOGIN ="user/login";

        /**
         * 5.2 注册
         * 方法：POST
         * 参数username,password,repassword
         */
        public final static String REGISTER ="user/register";

        /**
         * 5.3 退出
         * 方法：GET
         */
        public final static String LOG_OUT ="user/logout/json";

        /**
         * 7. 搜索
         7.1 搜索
         https://www.wanandroid.com/article/query/0/json
         方法：POST
         参数：
         页码：拼接在链接上，从0开始。
         k ： 搜索关键词
         */
        public final static String SEARCH_ARTICLE_BY_KEYWORD ="article/query/0/json";

    }

}
