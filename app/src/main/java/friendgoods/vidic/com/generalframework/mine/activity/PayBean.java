package friendgoods.vidic.com.generalframework.mine.activity;

import com.google.gson.annotations.SerializedName;

public class PayBean {

    /**
     * data : {"timeStamp":"1537184833","package":"prepay_id=null","paySign":"E5337671C5BE4C5B2061A65CF4C88E6F","appid":"wx01114b3ed5f6ed34","signType":"MD5","nonceStr":"3knpcfkhb6pk0lwi6jhfrxc7x9crf5da"}
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
         * timeStamp : 1537184833
         * package : prepay_id=null
         * paySign : E5337671C5BE4C5B2061A65CF4C88E6F
         * appid : wx01114b3ed5f6ed34
         * signType : MD5
         * nonceStr : 3knpcfkhb6pk0lwi6jhfrxc7x9crf5da
         */

        private String timeStamp;
        @SerializedName("package")
        private String packageX;
        private String paySign;
        private String appid;
        private String signType;
        private String nonceStr;

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }
    }
}
