package friendgoods.vidic.com.generalframework.activity.bean;

public class LoginBean {

    /**
     * data : {"voice":1,"spNum":0,"shake":1,"weChatA":"o-CJL1IxT1aYtdIpxBggH1I727Hg","mobile":"15210903179","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","updateTime":"2018-11-15 13:33:26","token":"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMTUifQ._xmm6A5v1zL-fvmIPSQJ78h21Tq8crHk_heUeONVGNg","signDays":1,"createTime":"2018-11-15 11:48:33","integral":0.19,"name":"levy","logo":"man1.png","id":215,"is_use":1,"status":0}
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
         * spNum : 0
         * shake : 1
         * weChatA : o-CJL1IxT1aYtdIpxBggH1I727Hg
         * mobile : 15210903179
         * photo : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132
         * updateTime : 2018-11-15 13:33:26
         * token : eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMTUifQ._xmm6A5v1zL-fvmIPSQJ78h21Tq8crHk_heUeONVGNg
         * signDays : 1
         * createTime : 2018-11-15 11:48:33
         * integral : 0.19
         * name : levy
         * logo : man1.png
         * id : 215
         * is_use : 1
         * status : 0
         */

        private int voice;
        private int spNum;
        private int shake;
        private String weChatA;
        private String mobile;
        private String photo;
        private String updateTime;
        private String token;
        private int signDays;
        private String createTime;
        private double integral;
        private String name;
        private String logo;
        private int id;
        private int is_use;
        private int status;

        public int getVoice() {
            return voice;
        }

        public void setVoice(int voice) {
            this.voice = voice;
        }

        public int getSpNum() {
            return spNum;
        }

        public void setSpNum(int spNum) {
            this.spNum = spNum;
        }

        public int getShake() {
            return shake;
        }

        public void setShake(int shake) {
            this.shake = shake;
        }

        public String getWeChatA() {
            return weChatA;
        }

        public void setWeChatA(String weChatA) {
            this.weChatA = weChatA;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getSignDays() {
            return signDays;
        }

        public void setSignDays(int signDays) {
            this.signDays = signDays;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public double getIntegral() {
            return integral;
        }

        public void setIntegral(double integral) {
            this.integral = integral;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
