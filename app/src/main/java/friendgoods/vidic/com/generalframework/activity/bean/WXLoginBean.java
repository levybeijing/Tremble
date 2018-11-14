package friendgoods.vidic.com.generalframework.activity.bean;

public class WXLoginBean {

    /**
     * data : {"voice":1,"shake":1,"weChatA":"o-CJL1KxMf3C_Bjw-EzlIG58823g","mobile":"13302168008","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132","updateTime":"2018-11-14 17:28:14","signDays":1,"createTime":"2018-11-14 15:36:43","integral":2.34,"name":"大黑","logo":"man1.png","id":213,"is_use":1,"status":0}
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
         * weChatA : o-CJL1KxMf3C_Bjw-EzlIG58823g
         * mobile : 13302168008
         * photo : http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132
         * updateTime : 2018-11-14 17:28:14
         * signDays : 1
         * createTime : 2018-11-14 15:36:43
         * integral : 2.34
         * name : 大黑
         * logo : man1.png
         * id : 213
         * is_use : 1
         * status : 0
         */

        private int voice;
        private int shake;
        private String weChatA;
        private String mobile;
        private String photo;
        private String updateTime;
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
