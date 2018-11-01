package friendgoods.vidic.com.generalframework.bean;

import java.util.List;

public class MyWallBean {

    /**
     * data : {"userPhoto":[{"score":36,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":26}],"id":93,"is_use":1,"userId":25,"axle":[{"xaxle":"1","giftId":3,"score":36,"high":"52","wide":"30","id":1089,"type":1,"yaxle":"1","url":"gift1-1.png"}]}
     * message : 请求成功
     * state : 1
     */

    private DataBean data;
    private String message;
    private int state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * userPhoto : [{"score":36,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":26}]
         * id : 93
         * is_use : 1
         * userId : 25
         * axle : [{"xaxle":"1","giftId":3,"score":36,"high":"52","wide":"30","id":1089,"type":1,"yaxle":"1","url":"gift1-1.png"}]
         */

        private int id;
        private int is_use;
        private int userId;
        private List<UserPhotoBean> userPhoto;
        private List<AxleBean> axle;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_use() {
            return is_use;
        }

        public void setIs_use(int is_use) {
            this.is_use = is_use;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<UserPhotoBean> getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(List<UserPhotoBean> userPhoto) {
            this.userPhoto = userPhoto;
        }

        public List<AxleBean> getAxle() {
            return axle;
        }

        public void setAxle(List<AxleBean> axle) {
            this.axle = axle;
        }

        public static class UserPhotoBean {
            /**
             * score : 36
             * photo : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132
             * id : 26
             */

            private int score;
            private String photo;
            private int id;

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
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
        }

        public static class AxleBean {
            /**
             * xaxle : 1
             * giftId : 3
             * score : 36
             * high : 52
             * wide : 30
             * id : 1089
             * type : 1
             * yaxle : 1
             * url : gift1-1.png
             */

            private String xaxle;
            private int giftId;
            private int score;
            private String high;
            private String wide;
            private int id;
            private int type;
            private String yaxle;
            private String url;

            public String getXaxle() {
                return xaxle;
            }

            public void setXaxle(String xaxle) {
                this.xaxle = xaxle;
            }

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getYaxle() {
                return yaxle;
            }

            public void setYaxle(String yaxle) {
                this.yaxle = yaxle;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
