package com.transcendence.wan.module.dama.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Joephone on 2020/4/18 14:50
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class DamaBean implements Serializable {

    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12928,"link":"https://www.jianshu.com/p/9aac53bb1098","niceDate":"2小时前","niceShareDate":"2小时前","origin":"","prefix":"","projectLink":"","publishTime":1587210946000,"selfVisible":0,"shareDate":1587210946000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android画板 半透明画笔 笔迹叠加效果 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12927,"link":"https://blog.csdn.net/Fantasy_Lin_/article/details/105455974","niceDate":"5小时前","niceShareDate":"5小时前","origin":"","prefix":"","projectLink":"","publishTime":1587198027000,"selfVisible":0,"shareDate":1587198027000,"shareUser":"FantasyLin","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android Studio如何连接第三方模拟器","type":0,"userId":17645,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12925,"link":"https://blog.csdn.net/Greathfs/article/details/105595921","niceDate":"9小时前","niceShareDate":"9小时前","origin":"","prefix":"","projectLink":"","publishTime":1587183703000,"selfVisible":0,"shareDate":1587183703000,"shareUser":"Greathfs","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"【Android】Jetpack全组件实战开发短视频应用App(四)","type":0,"userId":5325,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12924,"link":"https://juejin.im/post/5e893002f265da48094d8cd3","niceDate":"11小时前","niceShareDate":"11小时前","origin":"","prefix":"","projectLink":"","publishTime":1587176663000,"selfVisible":0,"shareDate":1587176663000,"shareUser":"goweii","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"看完这篇 Session、Cookie、Token，和面试官扯皮就没问题了","type":0,"userId":20382,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12923,"link":"https://juejin.im/post/5e8d6edfe51d4546fd481179","niceDate":"13小时前","niceShareDate":"13小时前","origin":"","prefix":"","projectLink":"","publishTime":1587170229000,"selfVisible":0,"shareDate":1587170229000,"shareUser":"AprilEyon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"AndroidX RecyclerView总结-Recycler","type":0,"userId":3559,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12921,"link":"https://www.jianshu.com/p/02e4327b7e02","niceDate":"21小时前","niceShareDate":"21小时前","origin":"","prefix":"","projectLink":"","publishTime":1587139814000,"selfVisible":0,"shareDate":1587139814000,"shareUser":"深红骑士","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"为什么Looper.loop()中的死循环不会导致ANR","type":0,"userId":29303,"visible":0,"zan":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12928,"link":"https://www.jianshu.com/p/9aac53bb1098","niceDate":"2小时前","niceShareDate":"2小时前","origin":"","prefix":"","projectLink":"","publishTime":1587210946000,"selfVisible":0,"shareDate":1587210946000,"shareUser":"鸿洋","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android画板 半透明画笔 笔迹叠加效果 ","type":0,"userId":2,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12927,"link":"https://blog.csdn.net/Fantasy_Lin_/article/details/105455974","niceDate":"5小时前","niceShareDate":"5小时前","origin":"","prefix":"","projectLink":"","publishTime":1587198027000,"selfVisible":0,"shareDate":1587198027000,"shareUser":"FantasyLin","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android Studio如何连接第三方模拟器","type":0,"userId":17645,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12925,"link":"https://blog.csdn.net/Greathfs/article/details/105595921","niceDate":"9小时前","niceShareDate":"9小时前","origin":"","prefix":"","projectLink":"","publishTime":1587183703000,"selfVisible":0,"shareDate":1587183703000,"shareUser":"Greathfs","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"【Android】Jetpack全组件实战开发短视频应用App(四)","type":0,"userId":5325,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12924,"link":"https://juejin.im/post/5e893002f265da48094d8cd3","niceDate":"11小时前","niceShareDate":"11小时前","origin":"","prefix":"","projectLink":"","publishTime":1587176663000,"selfVisible":0,"shareDate":1587176663000,"shareUser":"goweii","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"看完这篇 Session、Cookie、Token，和面试官扯皮就没问题了","type":0,"userId":20382,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12923,"link":"https://juejin.im/post/5e8d6edfe51d4546fd481179","niceDate":"13小时前","niceShareDate":"13小时前","origin":"","prefix":"","projectLink":"","publishTime":1587170229000,"selfVisible":0,"shareDate":1587170229000,"shareUser":"AprilEyon","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"AndroidX RecyclerView总结-Recycler","type":0,"userId":3559,"visible":0,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":true,"id":12921,"link":"https://www.jianshu.com/p/02e4327b7e02","niceDate":"21小时前","niceShareDate":"21小时前","origin":"","prefix":"","projectLink":"","publishTime":1587139814000,"selfVisible":0,"shareDate":1587139814000,"shareUser":"深红骑士","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"为什么Looper.loop()中的死循环不会导致ANR","type":0,"userId":29303,"visible":0,"zan":0}]
         */

        private int curPage;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public class DatasBean {
            /**
             * apkLink :
             * audit : 1
             * author :
             * canEdit : false
             * chapterId : 494
             * chapterName : 广场
             * collect : false
             * courseId : 13
             * desc :
             * descMd :
             * envelopePic :
             * fresh : true
             * id : 12928
             * link : https://www.jianshu.com/p/9aac53bb1098
             * niceDate : 2小时前
             * niceShareDate : 2小时前
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1587210946000
             * selfVisible : 0
             * shareDate : 1587210946000
             * shareUser : 鸿洋
             * superChapterId : 494
             * superChapterName : 广场Tab
             * tags : []
             * title : Android画板 半透明画笔 笔迹叠加效果
             * type : 0
             * userId : 2
             * visible : 0
             * zan : 0
             */

            private String apkLink;
            private int audit;
            private String author;
            private boolean canEdit;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String descMd;
            private String envelopePic;
            private boolean top;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }

            public boolean isTop() {
                return top;
            }

            public void setTop(boolean top) {
                this.top = top;
            }
        }
    }
}
