package friendgoods.vidic.com.generalframework.bean;

public class MyrankBean {

    /**
     * data : {"rownum":17,"name":"levy","shakeNum":0,"photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","time":"00:0:0","userId":236}
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
         * rownum : 17.0
         * name : levy
         * shakeNum : 0
         * photo : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132
         * time : 00:0:0
         * userId : 236
         */

        private double rownum;
        private String name;
        private int shakeNum;
        private String photo;
        private String time;
        private int userId;

        public double getRownum() {
            return rownum;
        }

        public void setRownum(double rownum) {
            this.rownum = rownum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getShakeNum() {
            return shakeNum;
        }

        public void setShakeNum(int shakeNum) {
            this.shakeNum = shakeNum;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
