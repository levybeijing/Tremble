package friendgoods.vidic.com.generalframework.bean;

import java.util.List;

public class MyGiftsListBean {


    /**
     * data : [{"giftId":6,"score":5,"high":"50","wide":"52","num":94,"name":"棒棒糖","photo":"gift1-4-s.png","id":78,"url":"gift1-4.png"},{"giftId":3,"score":36,"high":"52","wide":"30","num":1,"name":"火烈鸟","photo":"gift1-1-s.png","id":118,"url":"gift1-1.png"},{"giftId":17,"score":66,"high":"82","wide":"95","num":5,"name":"666","photo":"gift4-3-s.png","id":89,"url":"gift4-3.png"},{"giftId":18,"score":99,"high":"89","wide":"96","num":1,"name":"赞","photo":"gift4-4-s.png","id":90,"url":"gift4-4.png"},{"giftId":8,"score":520,"high":"208","wide":"140","num":25,"name":"520组合","photo":"gift2-1-s.png","id":80,"url":"gift2-1.png"},{"giftId":10,"score":1200,"high":"106","wide":"206","num":1,"name":"超级游艇","photo":"gift2-3-s.png","id":82,"url":"gift2-3.png"},{"giftId":7,"score":1314,"high":"431","wide":"326","num":4,"name":"爱你一万年/金箍","photo":"gift1-5-s.png","id":79,"url":"gift1-5.png"}]
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
         * giftId : 6
         * score : 5
         * high : 50
         * wide : 52
         * num : 94
         * name : 棒棒糖
         * photo : gift1-4-s.png
         * id : 78
         * url : gift1-4.png
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
