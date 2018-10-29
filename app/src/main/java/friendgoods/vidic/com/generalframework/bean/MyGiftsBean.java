package friendgoods.vidic.com.generalframework.bean;

import java.util.List;

public class MyGiftsBean {


    /**
     * data : [{"giftId":4,"score":7,"high":"45","wide":"52","num":10,"name":"星星/月亮","photo":"gift1-2-s.png","id":204,"url":"gift1-2.png"},{"giftId":4,"score":7,"high":"45","wide":"52","num":1,"name":"星星/月亮","photo":"gift1-2-s.png","id":211,"url":"gift1-2.png"},{"giftId":3,"score":36,"high":"52","wide":"30","num":30,"name":"火烈鸟","photo":"gift1-1-s.png","id":203,"url":"gift1-1.png"},{"giftId":3,"score":36,"high":"52","wide":"30","num":1,"name":"火烈鸟","photo":"gift1-1-s.png","id":210,"url":"gift1-1.png"},{"giftId":16,"score":52,"high":"98","wide":"93","num":10,"name":"珍珠","photo":"gift4-2-s.png","id":209,"url":"gift4-2.png"},{"giftId":14,"score":111,"high":"110","wide":"117","num":5,"name":"皇冠/第一","photo":"gift3-3-s.png","id":208,"url":"gift3-3.png"},{"giftId":8,"score":520,"high":"208","wide":"140","num":10,"name":"520组合","photo":"gift2-1-s.png","id":206,"url":"gift2-1.png"},{"giftId":9,"score":999,"high":"138","wide":"195","num":10,"name":"钻石","photo":"gift2-2-s.png","id":207,"url":"gift2-2.png"},{"giftId":7,"score":1314,"high":"431","wide":"326","num":5,"name":"爱你一万年/金箍","photo":"gift1-5-s.png","id":205,"url":"gift1-5.png"}]
     * message : 请求成功
     * state : 1
     */

    private String message;
    private int state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * giftId : 4
         * score : 7
         * high : 45
         * wide : 52
         * num : 10
         * name : 星星/月亮
         * photo : gift1-2-s.png
         * id : 204
         * url : gift1-2.png
         */

        private int giftId;
        private int score;
        private String high;
        private String wide;
        private int num;
        private String name;
        private String photo;
        private int id;
        private String url;

        public int getGiftId() {
            return giftId;
        }

        public void setGiftId(int giftId) {
            this.giftId = giftId;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getWide() {
            return wide;
        }

        public void setWide(String wide) {
            this.wide = wide;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
