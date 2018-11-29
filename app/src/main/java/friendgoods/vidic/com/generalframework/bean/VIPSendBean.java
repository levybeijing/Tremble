package friendgoods.vidic.com.generalframework.bean;

import java.util.List;

public class VIPSendBean {

    /**
     * data : {"PageInfo":{"endRow":2,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"score":70,"name":"大黑","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132","logo":"man1.png","id":389,"userId":235},{"score":2400,"name":"大黑","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132","logo":"man1.png","id":392,"userId":235}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":2,"startRow":1,"total":2}}
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
         * PageInfo : {"endRow":2,"firstPage":1,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"lastPage":1,"list":[{"score":70,"name":"大黑","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132","logo":"man1.png","id":389,"userId":235},{"score":2400,"name":"大黑","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132","logo":"man1.png","id":392,"userId":235}],"navigatePages":1,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":2,"startRow":1,"total":2}
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
             * endRow : 2
             * firstPage : 1
             * hasNextPage : false
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : true
             * lastPage : 1
             * list : [{"score":70,"name":"大黑","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132","logo":"man1.png","id":389,"userId":235},{"score":2400,"name":"大黑","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132","logo":"man1.png","id":392,"userId":235}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 0
             * pageNum : 1
             * pageSize : 20
             * pages : 1
             * prePage : 0
             * size : 2
             * startRow : 1
             * total : 2
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
                 * name : 大黑
                 * photo : http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132
                 * logo : man1.png
                 * id : 389
                 * userId : 235
                 */

                private int score;
                private String name;
                private String photo;
                private String logo;
                private int id;
                private int userId;

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
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

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }
            }
        }
    }
}
