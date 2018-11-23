package friendgoods.vidic.com.generalframework.bean;

import java.util.List;

public class MyWallBean {

    /**
     * data : {"score":614,"userPhoto":[{"score":571,"photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/cZpmNicibSqJTJXxYnAQBDRdKjONawhFN9G2R8CPWuIJDqLWgqogIicZdTa2meD4NialtF2J9VW91tylmq4PwOUzMg/132","id":206},{"score":43,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJbzkWgDI18A699AmcTrjKicDReF6WGv7PiacibIPjiaz4FR8ShYtE4qIZO7dWNKUtzfmHaUuKUr9GZ7A/132","id":173}],"logo":"woman1.png","id":361,"is_use":1,"userId":231,"axle":[{"xaxle":"25","giftId":5,"score":20,"high":"50","wide":"52","id":2500,"type":1,"yaxle":"371","url":"gift1-3.png"},{"xaxle":"42","giftId":4,"score":7,"high":"45","wide":"52","id":2501,"type":1,"yaxle":"21","url":"gift1-2.png"},{"xaxle":"73","giftId":4,"score":7,"high":"45","wide":"52","id":2509,"type":1,"yaxle":"71","url":"gift1-2.png"},{"xaxle":"180","giftId":6,"score":5,"high":"50","wide":"52","id":2510,"type":1,"yaxle":"206","url":"gift1-4.png"},{"xaxle":"129","giftId":6,"score":5,"high":"50","wide":"52","id":2511,"type":1,"yaxle":"156","url":"gift1-4.png"},{"xaxle":"58","giftId":4,"score":7,"high":"45","wide":"52","id":2512,"type":1,"yaxle":"303","url":"gift1-2.png"},{"xaxle":"190","giftId":8,"score":520,"high":"208","wide":"140","id":2513,"type":1,"yaxle":"60","url":"gift2-1.png"},{"xaxle":"255","giftId":3,"score":36,"high":"52","wide":"30","id":2753,"type":1,"yaxle":"42","url":"gift1-1.png"},{"xaxle":"68","giftId":4,"score":7,"high":"45","wide":"52","id":2754,"type":1,"yaxle":"218","url":"gift1-2.png"}]}
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
         * score : 614
         * userPhoto : [{"score":571,"photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/cZpmNicibSqJTJXxYnAQBDRdKjONawhFN9G2R8CPWuIJDqLWgqogIicZdTa2meD4NialtF2J9VW91tylmq4PwOUzMg/132","id":206},{"score":43,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJbzkWgDI18A699AmcTrjKicDReF6WGv7PiacibIPjiaz4FR8ShYtE4qIZO7dWNKUtzfmHaUuKUr9GZ7A/132","id":173}]
         * logo : woman1.png
         * id : 361
         * is_use : 1
         * userId : 231
         * axle : [{"xaxle":"25","giftId":5,"score":20,"high":"50","wide":"52","id":2500,"type":1,"yaxle":"371","url":"gift1-3.png"},{"xaxle":"42","giftId":4,"score":7,"high":"45","wide":"52","id":2501,"type":1,"yaxle":"21","url":"gift1-2.png"},{"xaxle":"73","giftId":4,"score":7,"high":"45","wide":"52","id":2509,"type":1,"yaxle":"71","url":"gift1-2.png"},{"xaxle":"180","giftId":6,"score":5,"high":"50","wide":"52","id":2510,"type":1,"yaxle":"206","url":"gift1-4.png"},{"xaxle":"129","giftId":6,"score":5,"high":"50","wide":"52","id":2511,"type":1,"yaxle":"156","url":"gift1-4.png"},{"xaxle":"58","giftId":4,"score":7,"high":"45","wide":"52","id":2512,"type":1,"yaxle":"303","url":"gift1-2.png"},{"xaxle":"190","giftId":8,"score":520,"high":"208","wide":"140","id":2513,"type":1,"yaxle":"60","url":"gift2-1.png"},{"xaxle":"255","giftId":3,"score":36,"high":"52","wide":"30","id":2753,"type":1,"yaxle":"42","url":"gift1-1.png"},{"xaxle":"68","giftId":4,"score":7,"high":"45","wide":"52","id":2754,"type":1,"yaxle":"218","url":"gift1-2.png"}]
         */

        private int score;
        private String logo;
        private int id;
        private int is_use;
        private int userId;
        private List<UserPhotoBean> userPhoto;
        private List<AxleBean> axle;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

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
             * score : 571
             * photo : http://thirdwx.qlogo.cn/mmopen/vi_32/cZpmNicibSqJTJXxYnAQBDRdKjONawhFN9G2R8CPWuIJDqLWgqogIicZdTa2meD4NialtF2J9VW91tylmq4PwOUzMg/132
             * id : 206
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
             * xaxle : 25
             * giftId : 5
             * score : 20
             * high : 50
             * wide : 52
             * id : 2500
             * type : 1
             * yaxle : 371
             * url : gift1-3.png
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
