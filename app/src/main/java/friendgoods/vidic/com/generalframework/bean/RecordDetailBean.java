package friendgoods.vidic.com.generalframework.bean;

public class RecordDetailBean {

    /**
     * data : {"rownum":1,"jShakeNum":123,"sShakeNum":104,"integral":170.01,"time":"127 22:58"}
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
         * rownum : 1.0
         * jShakeNum : 123
         * sShakeNum : 104
         * integral : 170.01
         * time : 127 22:58
         */

        private double rownum;
        private int jShakeNum;
        private int sShakeNum;
        private double integral;
        private String time;

        public double getRownum() {
            return rownum;
        }

        public void setRownum(double rownum) {
            this.rownum = rownum;
        }

        public int getJShakeNum() {
            return jShakeNum;
        }

        public void setJShakeNum(int jShakeNum) {
            this.jShakeNum = jShakeNum;
        }

        public int getSShakeNum() {
            return sShakeNum;
        }

        public void setSShakeNum(int sShakeNum) {
            this.sShakeNum = sShakeNum;
        }

        public double getIntegral() {
            return integral;
        }

        public void setIntegral(double integral) {
            this.integral = integral;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
