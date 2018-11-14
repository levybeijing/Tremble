package friendgoods.vidic.com.generalframework.activity.bean;

public class RegisterBean {

    /**
     * data : {"voice":1,"shake":1,"signDays":1,"createTime":"2018-11-14 13:57:17","integral":0,"mobile":"15210903179","id":212,"is_use":1,"status":0}
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
         * signDays : 1
         * createTime : 2018-11-14 13:57:17
         * integral : 0.0
         * mobile : 15210903179
         * id : 212
         * is_use : 1
         * status : 0
         */

        private int voice;
        private int shake;
        private int signDays;
        private String createTime;
        private double integral;
        private String mobile;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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
