package friendgoods.vidic.com.generalframework.activity;

import java.util.List;

public class ChallengeModelBean  {

    /**
     * data : [{"giftId":3,"createTime":"2018-10-15 18:04:35","num":50,"time":"01:00"}]
     * message : 请求成功
     * state : 1
     */

    private String message;
    private int state;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * giftId : 3
         * createTime : 2018-10-15 18:04:35
         * num : 50
         * time : 01:00
         */

        private int giftId;
        private String createTime;
        private int num;
        private String time;

        public int getGiftId() {
            return giftId;
        }

        public void setGiftId(int giftId) {
            this.giftId = giftId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
