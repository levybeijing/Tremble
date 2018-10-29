package friendgoods.vidic.com.generalframework.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FansBangBean {

    /**
     * data : {"PageInfo":{"endRow":5,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"score":3644,"giftId":8,"fansId":25,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":111,"type":1},{"score":1494,"giftId":3,"fansId":25,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":112,"type":1},{"fansId":41,"name":"L*^馨儿","photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqvOnXot9DZ9cbfG3p66JOxxoVD6EKmLsjuNHer6EjicX65vjcsRakWRJlzPlz1g2zdvLmSUdGENDA/132","id":134,"type":1},{"fansId":25,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":114,"type":1},{"fansId":26,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":97,"type":1}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":5,"startRow":1,"total":5}}
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
         * PageInfo : {"endRow":5,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"score":3644,"giftId":8,"fansId":25,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":111,"type":1},{"score":1494,"giftId":3,"fansId":25,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":112,"type":1},{"fansId":41,"name":"L*^馨儿","photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqvOnXot9DZ9cbfG3p66JOxxoVD6EKmLsjuNHer6EjicX65vjcsRakWRJlzPlz1g2zdvLmSUdGENDA/132","id":134,"type":1},{"fansId":25,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":114,"type":1},{"fansId":26,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":97,"type":1}],"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":5,"startRow":1,"total":5}
         */

        private PageInfoBean PageInfo;

        public PageInfoBean getPageInfo() {
            return PageInfo;
        }

        public void setPageInfo(PageInfoBean PageInfo) {
            this.PageInfo = PageInfo;
        }

        public static class PageInfoBean {
            /**
             * endRow : 5
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"score":3644,"giftId":8,"fansId":25,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":111,"type":1},{"score":1494,"giftId":3,"fansId":25,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":112,"type":1},{"fansId":41,"name":"L*^馨儿","photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqvOnXot9DZ9cbfG3p66JOxxoVD6EKmLsjuNHer6EjicX65vjcsRakWRJlzPlz1g2zdvLmSUdGENDA/132","id":134,"type":1},{"fansId":25,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":114,"type":1},{"fansId":26,"name":"what are words","photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","id":97,"type":1}]
             * navigatePages : 8
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 10
             * pages : 1
             * prePage : 0
             * size : 5
             * startRow : 1
             * total : 5
             */

            private int endRow;
            private int firstPage;
            private boolean hasNextPage;
            private boolean hasPreviousPage;
            private boolean isFirstPage;
            private boolean isLastPage;
            private int lastPage;
            private int navigatePages;
            private int nextPage;
            private int pageNum;
            private int pageSize;
            private int pages;
            private int prePage;
            private int size;
            private int startRow;
            private int total;
            private List<ListBean> list;
            private List<Integer> navigatepageNums;

            public int getEndRow() {
                return endRow;
            }

            public void setEndRow(int endRow) {
                this.endRow = endRow;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
            }

            public boolean isHasNextPage() {
                return hasNextPage;
            }

            public void setHasNextPage(boolean hasNextPage) {
                this.hasNextPage = hasNextPage;
            }

            public boolean isHasPreviousPage() {
                return hasPreviousPage;
            }

            public void setHasPreviousPage(boolean hasPreviousPage) {
                this.hasPreviousPage = hasPreviousPage;
            }

            public boolean isIsFirstPage() {
                return isFirstPage;
            }

            public void setIsFirstPage(boolean isFirstPage) {
                this.isFirstPage = isFirstPage;
            }

            public boolean isIsLastPage() {
                return isLastPage;
            }

            public void setIsLastPage(boolean isLastPage) {
                this.isLastPage = isLastPage;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public int getNavigatePages() {
                return navigatePages;
            }

            public void setNavigatePages(int navigatePages) {
                this.navigatePages = navigatePages;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getPrePage() {
                return prePage;
            }

            public void setPrePage(int prePage) {
                this.prePage = prePage;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getStartRow() {
                return startRow;
            }

            public void setStartRow(int startRow) {
                this.startRow = startRow;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public List<Integer> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<Integer> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }

            public static class ListBean implements Parcelable{
                /**
                 * score : 3644
                 * giftId : 8
                 * fansId : 25
                 * name : what are words
                 * photo : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132
                 * id : 111
                 * type : 1
                 */

                private int score;
                private int giftId;
                private int fansId;
                private String name;
                private String photo;
                private int id;
                private int type;

                protected ListBean(Parcel in) {
                    score = in.readInt();
                    giftId = in.readInt();
                    fansId = in.readInt();
                    name = in.readString();
                    photo = in.readString();
                    id = in.readInt();
                    type = in.readInt();
                }

                public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                    @Override
                    public ListBean createFromParcel(Parcel in) {
                        return new ListBean(in);
                    }

                    @Override
                    public ListBean[] newArray(int size) {
                        return new ListBean[size];
                    }
                };

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public int getGiftId() {
                    return giftId;
                }

                public void setGiftId(int giftId) {
                    this.giftId = giftId;
                }

                public int getFansId() {
                    return fansId;
                }

                public void setFansId(int fansId) {
                    this.fansId = fansId;
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

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(score);
                    dest.writeInt(giftId);
                    dest.writeInt(fansId);
                    dest.writeString(name);
                    dest.writeString(photo);
                    dest.writeInt(id);
                    dest.writeInt(type);
                }
            }
        }
    }
}
