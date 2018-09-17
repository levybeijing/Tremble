package friendgoods.vidic.com.generalframework.mine.bean;

public class DetailGoodsBean {

    /**
     * data : {"photo1":"avatar_1.png,avatar_1.png,avatar_1.png,avatar_1.png","money":5,"createTime":"2018-08-22 10:29:44","integral":100,"intro":"这是一次测试2","name":"测试商品","photo":"avatar_1.png","is_use":1,"photo2":"avatar_1.png"}
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
         * photo1 : avatar_1.png,avatar_1.png,avatar_1.png,avatar_1.png
         * money : 5.0
         * createTime : 2018-08-22 10:29:44
         * integral : 100
         * intro : 这是一次测试2
         * name : 测试商品
         * photo : avatar_1.png
         * is_use : 1
         * photo2 : avatar_1.png
         */

        private String photo1;
        private double money;
        private String createTime;
        private int integral;
        private String intro;
        private String name;
        private String photo;
        private int is_use;
        private String photo2;

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
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

        public int getIs_use() {
            return is_use;
        }

        public void setIs_use(int is_use) {
            this.is_use = is_use;
        }

        public String getPhoto2() {
            return photo2;
        }

        public void setPhoto2(String photo2) {
            this.photo2 = photo2;
        }
    }
}
