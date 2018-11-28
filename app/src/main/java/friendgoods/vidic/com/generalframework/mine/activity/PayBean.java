package friendgoods.vidic.com.generalframework.mine.activity;

import com.google.gson.annotations.SerializedName;

public class PayBean {

    /**
     * data : {"timeStamp":"1543304154","package":"prepay_id=wx27153554412242c3737519fb0996094654","paySign":"CB2435E40CF57AE31F9479B6ED6D1D41","appid":"wx01114b3ed5f6ed34","signType":"MD5","nonceStr":"hoig7zbjbls5cg2dzdkzig6qzucxz5jk","prepay_id":"wx27153554412242c3737519fb0996094654"}
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
         * timeStamp : 1543304154
         * package : prepay_id=wx27153554412242c3737519fb0996094654
         * paySign : CB2435E40CF57AE31F9479B6ED6D1D41
         * appid : wx01114b3ed5f6ed34
         * signType : MD5
         * nonceStr : hoig7zbjbls5cg2dzdkzig6qzucxz5jk
         * prepay_id : wx27153554412242c3737519fb0996094654
         */

        private String timeStamp;
        @SerializedName("package")
        private String packageX;
        private String paySign;
        private String appid;
        private String signType;
        private String nonceStr;
        private String prepay_id;

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

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }
    }
}
