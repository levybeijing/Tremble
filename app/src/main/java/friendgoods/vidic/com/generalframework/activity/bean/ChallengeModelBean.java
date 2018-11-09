package friendgoods.vidic.com.generalframework.activity.bean;

import java.util.List;

public class ChallengeModelBean  {

    /**
     * data : {"challengeMode":[{"giftId":3,"createTime":"2018-10-15 18:04:35","num":50,"width":120,"name":"火烈鸟","photo":"gift1-1-s.png","id":1,"time":"01:00","height":120},{"giftId":4,"createTime":"2018-11-05 12:16:08","num":100,"width":120,"name":"星星/月亮","photo":"gift1-2-s.png","id":2,"time":"01:00","height":120},{"giftId":5,"createTime":"2018-11-05 12:16:30","num":150,"width":120,"name":"卡通形象/秋田","photo":"gift1-3-s.png","id":3,"time":"01:00","height":120},{"giftId":10,"createTime":"2018-11-08 19:51:17","num":300,"name":"超级游艇","photo":"gift2-3-s.png","id":5,"time":"15:11"}],"time":"01:00"}
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
         * challengeMode : [{"giftId":3,"createTime":"2018-10-15 18:04:35","num":50,"width":120,"name":"火烈鸟","photo":"gift1-1-s.png","id":1,"time":"01:00","height":120},{"giftId":4,"createTime":"2018-11-05 12:16:08","num":100,"width":120,"name":"星星/月亮","photo":"gift1-2-s.png","id":2,"time":"01:00","height":120},{"giftId":5,"createTime":"2018-11-05 12:16:30","num":150,"width":120,"name":"卡通形象/秋田","photo":"gift1-3-s.png","id":3,"time":"01:00","height":120},{"giftId":10,"createTime":"2018-11-08 19:51:17","num":300,"name":"超级游艇","photo":"gift2-3-s.png","id":5,"time":"15:11"}]
         * time : 01:00
         */

        private String time;
        private List<ChallengeModeBean> challengeMode;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<ChallengeModeBean> getChallengeMode() {
            return challengeMode;
        }

        public void setChallengeMode(List<ChallengeModeBean> challengeMode) {
            this.challengeMode = challengeMode;
        }

        public static class ChallengeModeBean {
            /**
             * giftId : 3
             * createTime : 2018-10-15 18:04:35
             * num : 50
             * width : 120
             * name : 火烈鸟
             * photo : gift1-1-s.png
             * id : 1
             * time : 01:00
             * height : 120
             */

            private int giftId;
            private String createTime;
            private int num;
            private int width;
            private String name;
            private String photo;
            private int id;
            private String time;
            private int height;

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

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
