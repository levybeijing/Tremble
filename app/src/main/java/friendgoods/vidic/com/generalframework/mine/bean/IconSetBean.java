package friendgoods.vidic.com.generalframework.mine.bean;

import java.util.List;

public class IconSetBean {

    /**
     * data : [{"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLSOR5dff1ibic8u1stdGgZXVuib3I9dEmGJVW7pY3uvVauZzarP3IblImS7rp5G4cUnUJX14zhbeyJw/132"}]
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
         * photo : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLSOR5dff1ibic8u1stdGgZXVuib3I9dEmGJVW7pY3uvVauZzarP3IblImS7rp5G4cUnUJX14zhbeyJw/132
         */

        private String photo;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
