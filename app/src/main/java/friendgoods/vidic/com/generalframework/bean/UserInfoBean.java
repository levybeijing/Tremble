package friendgoods.vidic.com.generalframework.bean;

public class UserInfoBean {

    /**
     * data : {"voice":1,"shake":1,"integral":0,"sex":"0","name":"。。。。。。","weChat":"o5BgB5ZpkDLnaD-mi0FR6VSil20E","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLSOR5dff1ibic8u1stdGgZXVuib3I9dEmGJVW7pY3uvVauZzarP3IblImS7rp5G4cUnUJX14zhbeyJw/132","logo":"man1"}
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
         * integral : 0.0
         * sex : 0
         * name : 。。。。。。
         * weChat : o5BgB5ZpkDLnaD-mi0FR6VSil20E
         * photo : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLSOR5dff1ibic8u1stdGgZXVuib3I9dEmGJVW7pY3uvVauZzarP3IblImS7rp5G4cUnUJX14zhbeyJw/132
         * logo : man1
         */

        private int voice;
        private int shake;
        private double integral;
        private String sex;
        private String name;
        private String weChat;
        private String photo;
        private String logo;

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

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
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
    }
}
