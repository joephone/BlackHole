package com.transcendence.wan.module.mine.model;

import com.transcendence.wan.core.bean.WanBaseBean;

import java.util.List;

/**
 * @Author Joephone on 2020/4/27 10:03
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class MyCoinListModel extends WanBaseBean {

    /**
     * data : {"curPage":1,"datas":[{"coinCount":13,"date":1587922278000,"desc":"2020-04-27 01:31:18 签到 , 积分：10 + 3","id":198298,"reason":"签到","type":1,"userId":60527,"userName":"15171484007"},{"coinCount":12,"date":1587831224000,"desc":"2020-04-26 00:13:44 签到 , 积分：10 + 2","id":197444,"reason":"签到","type":1,"userId":60527,"userName":"15171484007"},{"coinCount":11,"date":1587744176000,"desc":"2020-04-25 00:02:56 签到 , 积分：10 + 1","id":196858,"reason":"签到","type":1,"userId":60527,"userName":"15171484007"},{"coinCount":10,"date":1587715941000,"desc":"2020-04-24 16:12:21 签到 , 积分：10 + 0","id":196646,"reason":"签到","type":1,"userId":60527,"userName":"15171484007"}],"offset":0,"over":true,"pageCount":1,"size":20,"total":4}
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
         * datas : [{"coinCount":13,"date":1587922278000,"desc":"2020-04-27 01:31:18 签到 , 积分：10 + 3","id":198298,"reason":"签到","type":1,"userId":60527,"userName":"15171484007"},{"coinCount":12,"date":1587831224000,"desc":"2020-04-26 00:13:44 签到 , 积分：10 + 2","id":197444,"reason":"签到","type":1,"userId":60527,"userName":"15171484007"},{"coinCount":11,"date":1587744176000,"desc":"2020-04-25 00:02:56 签到 , 积分：10 + 1","id":196858,"reason":"签到","type":1,"userId":60527,"userName":"15171484007"},{"coinCount":10,"date":1587715941000,"desc":"2020-04-24 16:12:21 签到 , 积分：10 + 0","id":196646,"reason":"签到","type":1,"userId":60527,"userName":"15171484007"}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 4
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<MyCoinListBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<MyCoinListBean> getDatas() {
            return datas;
        }

        public void setDatas(List<MyCoinListBean> datas) {
            this.datas = datas;
        }


    }
}
