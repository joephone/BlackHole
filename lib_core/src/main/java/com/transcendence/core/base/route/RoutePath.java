package com.transcendence.core.base.route;

/**
 * @author joephone
 * @date 2023/2/2
 * @desc
 */
public class RoutePath {

    /**
     * Main组件
     */
    public static class App {
        private static final String MAIN = "/module_main";

        /**
         * 主页面
         */
        public static final String PAGER_MAIN = MAIN + "/Main";
    }

    /**
     * Main组件
     */
    public static class Music {
        private static final String MAIN = "/module_music";

        public static final String PAGER_MAIN = MAIN + "/Main";
    }

    public static class GreenStar {
        private static final String MAIN = "/module_greenstar";
        public static final String PAGER_MAIN = MAIN+"/Main";
        public static final String PAGER_SPLASH = MAIN+"/Splash";
    }

    public static class Map {
        private static final String MAIN = "/module_map";
        public static final String PAGER_MAIN = MAIN+"/Main";
        public static final String PAGER_SPLASH = MAIN+"/Splash";
    }

    /**
     * web 组件
     */
    public static class Web {
        public static final String WEB = "/module_web";
        public static final String PAGER_WEB = WEB + "/Web";

    }

    /**
     * image 组件
     */
    public static class Image {
        public static final String Main = "/module_image";

    }

    public static class Gallery {
        public static final String Main = "/lib_gallery";
        public static final String PAGER_MAIN = Main + "/Main";
        public static final String PAGER_LUBAN = Main + "/Luban";
    }

    public static class Demo {
        public static final String MAIN = "/module_main";
        public static final String PAGER_BLOCK_LIST = MAIN+"/BlockList";
    }
}
