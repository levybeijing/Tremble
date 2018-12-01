package friendgoods.vidic.com.generalframework.bean;

import java.util.List;

public class SingleWallBean {

    /**
     * data : {"name":"哈哈dadada","logo":"woman1.png","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/cZpmNicibSqJTJXxYnAQBDRdKjONawhFN9G2R8CPWuIJDqLWgqogIicZdTa2meD4NialtF2J9VW91tylmq4PwOUzMg/132","id":364,"list":[{"xaxle":"172","giftId":8,"high":"208","wide":"140","yaxle":"121","url":"gift2-1.png"},{"xaxle":"97","giftId":8,"high":"208","wide":"140","yaxle":"54","url":"gift2-1.png"},{"xaxle":"22","giftId":4,"high":"45","wide":"52","yaxle":"348","url":"gift1-2.png"},{"xaxle":"27","giftId":8,"high":"208","wide":"140","yaxle":"127","url":"gift2-1.png"},{"xaxle":"31","giftId":4,"high":"45","wide":"52","yaxle":"37","url":"gift1-2.png"},{"xaxle":"108","giftId":8,"high":"208","wide":"140","yaxle":"234","url":"gift2-1.png"},{"xaxle":"232","giftId":4,"high":"45","wide":"52","yaxle":"32","url":"gift1-2.png"}],"url":"201811211656129135.png"}
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
         * name : 哈哈dadada
         * logo : woman1.png
         * photo : http://thirdwx.qlogo.cn/mmopen/vi_32/cZpmNicibSqJTJXxYnAQBDRdKjONawhFN9G2R8CPWuIJDqLWgqogIicZdTa2meD4NialtF2J9VW91tylmq4PwOUzMg/132
         * id : 364
         * list : [{"xaxle":"172","giftId":8,"high":"208","wide":"140","yaxle":"121","url":"gift2-1.png"},{"xaxle":"97","giftId":8,"high":"208","wide":"140","yaxle":"54","url":"gift2-1.png"},{"xaxle":"22","giftId":4,"high":"45","wide":"52","yaxle":"348","url":"gift1-2.png"},{"xaxle":"27","giftId":8,"high":"208","wide":"140","yaxle":"127","url":"gift2-1.png"},{"xaxle":"31","giftId":4,"high":"45","wide":"52","yaxle":"37","url":"gift1-2.png"},{"xaxle":"108","giftId":8,"high":"208","wide":"140","yaxle":"234","url":"gift2-1.png"},{"xaxle":"232","giftId":4,"high":"45","wide":"52","yaxle":"32","url":"gift1-2.png"}]
         * url : 201811211656129135.png
         */

        private String name;
        private String logo;
        private String photo;
        private int id;
        private String url;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * xaxle : 172
             * giftId : 8
             * high : 208
             * wide : 140
             * yaxle : 121
             * url : gift2-1.png
             */

            private String xaxle;
            private int giftId;
            private String high;
            private String wide;
            private String yaxle;
            private String url;

            public String getXaxle() {
                return xaxle;
            }

            public void setXaxle(String xaxle) {
                this.xaxle = xaxle;
            }

            public int getGiftId() {
                return giftId;
            }

            public void setGiftId(int giftId) {
                this.giftId = giftId;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getWide() {
                return wide;
            }

            public void setWide(String wide) {
                this.wide = wide;
            }

            public String getYaxle() {
                return yaxle;
            }

            public void setYaxle(String yaxle) {
                this.yaxle = yaxle;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
