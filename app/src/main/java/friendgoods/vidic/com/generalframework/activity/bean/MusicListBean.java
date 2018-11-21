package friendgoods.vidic.com.generalframework.activity.bean;

import java.util.List;

public class MusicListBean {
    /**
     * data : [{"rank":2,"id":12,"title":"音乐2","url":"1541663240.mp3"},{"rank":3,"id":13,"title":"音乐3","url":"1541663252.mp3"},{"rank":11,"id":11,"title":"音乐1","url":"1541663228.mp3"}]
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
         * rank : 2
         * id : 12
         * title : 音乐2
         * url : 1541663240.mp3
         */

        private int rank;
        private int id;
        private String title;
        private String url;

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
