package friendgoods.vidic.com.generalframework.bean;

import java.util.List;

public class VIPWallBean {

    /**
     * data : {"PageInfo":{"endRow":3,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"score":70,"fansId":236,"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman1.png","id":389,"url":"http://doutui.oss-cn-beijing.aliyuncs.com/null"},{"score":2400,"fansId":236,"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman1.png","id":392},{"score":5114,"fansId":236,"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman1.png","id":393,"url":"http://doutui.oss-cn-beijing.aliyuncs.com/null"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":3,"startRow":1,"total":3}}
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
         * PageInfo : {"endRow":3,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"score":70,"fansId":236,"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman1.png","id":389,"url":"http://doutui.oss-cn-beijing.aliyuncs.com/null"},{"score":2400,"fansId":236,"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman1.png","id":392},{"score":5114,"fansId":236,"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman1.png","id":393,"url":"http://doutui.oss-cn-beijing.aliyuncs.com/null"}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":3,"startRow":1,"total":3}
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
             * endRow : 3
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"score":70,"fansId":236,"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman1.png","id":389},{"score":2400,"fansId":236,"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman1.png","id":392},{"score":5114,"fansId":236,"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman1.png","id":393,"url":"http://doutui.oss-cn-beijing.aliyuncs.com/null"}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 20
             * pages : 1
             * prePage : 0
             * size : 3
             * startRow : 1
             * total : 3
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

            public static class ListBean {
                /**
                 * score : 70
                 * fansId : 236
                 * name : levy
                 * photo : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132
                 * logo : woman1.png
                 * id : 389
                 * url : http://doutui.oss-cn-beijing.aliyuncs.com/null
                 */

                private int score;
                private int fansId;
                private String name;
                private String photo;
                private String logo;
                private int id;
                private String url;

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
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

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
