package friendgoods.vidic.com.generalframework.bean;

import java.util.List;

public class GetWallIdBean {

    /**
     * data : {"fansId":236,"logo":"man1.png","id":389,"userId":235,"axle":[]}
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
         * fansId : 236
         * logo : man1.png
         * id : 389
         * userId : 235
         * axle : []
         */

        private int fansId;
        private String logo;
        private int id;
        private int userId;
        private List<?> axle;

        public int getFansId() {
            return fansId;
        }

        public void setFansId(int fansId) {
            this.fansId = fansId;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<?> getAxle() {
            return axle;
        }

        public void setAxle(List<?> axle) {
            this.axle = axle;
        }
    }
}
