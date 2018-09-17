package friendgoods.vidic.com.generalframework.mine.bean;

public class UserInfoBean {


    /**
     * data : {"voice":1,"shake":1,"integral":9.9993414699E8,"sex":"1","name":"111111","mobile":"18666409792","photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erEibGWxFCNRZm68yZNOeUIOjDe4511sdbFrPibqRSgGwc9tv6IlrbF0sMtzUcCYmeC5w4Tr3zoFpzQ/132","logo":"woman2.png","id":27}
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
         * voice : 1
         * shake : 1
         * integral : 9.9993414699E8
         * sex : 1
         * name : 111111
         * mobile : 18666409792
         * photo : https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erEibGWxFCNRZm68yZNOeUIOjDe4511sdbFrPibqRSgGwc9tv6IlrbF0sMtzUcCYmeC5w4Tr3zoFpzQ/132
         * logo : woman2.png
         * id : 27
         */

        private int voice;
        private int shake;
        private double integral;
        private String sex;
        private String name;
        private String mobile;
        private String photo;
        private String logo;
        private int id;

        public int getVoice() {
            return voice;
        }

        public void setVoice(int voice) {
            this.voice = voice;
        }

        public int getShake() {
            return shake;
        }

        public void setShake(int shake) {
            this.shake = shake;
        }

        public double getIntegral() {
            return integral;
        }

        public void setIntegral(double integral) {
            this.integral = integral;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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
    }
}
