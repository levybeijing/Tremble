package friendgoods.vidic.com.generalframework.activity.bean;

import java.util.List;

public class PKRecordBean {

    /**
     * data : [{"name":"大黑","shakeNum":82,"photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132","logo":"man1.png","userId":214},{"name":"levy","shakeNum":56,"photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"man1.png","userId":215}]
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
         * name : 大黑
         * shakeNum : 82
         * photo : http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132
         * logo : man1.png
         * userId : 214
         */

        private String name;
        private int shakeNum;
        private String photo;
        private String logo;
        private int userId;

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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
