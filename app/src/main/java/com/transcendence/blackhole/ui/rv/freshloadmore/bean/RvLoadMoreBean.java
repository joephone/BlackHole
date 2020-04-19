package com.transcendence.blackhole.ui.rv.freshloadmore.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Joephone on 2020/4/17 0:27
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class RvLoadMoreBean implements Serializable {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public class DataBean {
        /**
         * _id : 5e958ef60bd5529b54e7129d
         * author : 鸢媛
         * category : Girl
         * createdAt : 2020-04-16 08:00:00
         * desc : 愿你一生有山可靠 有树可栖
         与心爱之人 春赏花
         夏纳凉 秋登山 冬扫雪 ​​​​
         * images : ["http://gank.io/images/e941fa5d2cfb4a8297128178c371908c"]
         * likeCounts : 0
         * publishedAt : 2020-04-16 08:00:00
         * stars : 1
         * title : 第57期
         * type : Girl
         * url : http://gank.io/images/e941fa5d2cfb4a8297128178c371908c
         * views : 99
         */

        private String _id;
        private String author;
        private String category;
        private String createdAt;
        private String desc;
        private int likeCounts;
        private String publishedAt;
        private int stars;
        private String title;
        private String type;
        private String url;
        private int views;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getLikeCounts() {
            return likeCounts;
        }

        public void setLikeCounts(int likeCounts) {
            this.likeCounts = likeCounts;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
