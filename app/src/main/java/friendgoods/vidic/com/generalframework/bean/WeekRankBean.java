package friendgoods.vidic.com.generalframework.bean;

import java.util.List;

public class WeekRankBean {

    /**
     * data : {"PageInfo":{"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"rownum":1,"name":"。。。。。。","shakeNum":45,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKMLNTXbcau6agNfz76FAhtzPsGbicl9Aa0v7Ao2PhFX20dEIqVkEXVVPnwbUeYFeJT4Kk1EU5LPicw/132","time":"17","userId":55},{"rownum":2,"name":"what are words","shakeNum":38,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJbzkWgDI18A699AmcTrjKicDReF6WGv7PiacibIPjiaz4FR8ShYtE4qIZO7dWNKUtzfmHaUuKUr9GZ7A/132","time":"1:34","userId":52},{"rownum":3,"name":"张宝寅","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJaTVyeUl3jiao6DvAibSU9hTjZDiabCgxoSGib9Q7eib3EFFbwgPV6Guj93tvwAHewsmsxicwxhwLfibvDg/132","userId":46},{"rownum":4,"shakeNum":0,"userId":50},{"rownum":5,"name":"","shakeNum":0,"photo":"","userId":54},{"rownum":6,"name":"what are words","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","userId":28},{"rownum":7,"name":"L*^馨儿","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqvOnXot9DZ9cbfG3p66JOxxoVD6EKmLsjuNHer6EjicX65vjcsRakWRJlzPlz1g2zdvLmSUdGENDA/132","userId":41},{"rownum":8,"name":"乐于助人的红领巾","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIMY8TDasmFwLZhkvYfqFiaLEXdFj9xAaibs6vIKLWqYKb1fOR2yaaXorRyKR21ofC6nW4tNL5xbgjg/132","userId":45},{"rownum":9,"shakeNum":0,"userId":49},{"rownum":10,"name":"GoKu","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epwM712JhKOZ1Lhpu1YDH5fMnYLd9G18CeE6S8wlnZ860PuJ9JeZLxnORyJicPH25VutcxPJ6wGVWQ/132","userId":53}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":10,"pages":2,"prePage":0,"size":10,"startRow":1,"total":19}}
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
         * PageInfo : {"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":1,"list":[{"rownum":1,"name":"。。。。。。","shakeNum":45,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKMLNTXbcau6agNfz76FAhtzPsGbicl9Aa0v7Ao2PhFX20dEIqVkEXVVPnwbUeYFeJT4Kk1EU5LPicw/132","time":"17","userId":55},{"rownum":2,"name":"what are words","shakeNum":38,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJbzkWgDI18A699AmcTrjKicDReF6WGv7PiacibIPjiaz4FR8ShYtE4qIZO7dWNKUtzfmHaUuKUr9GZ7A/132","time":"1:34","userId":52},{"rownum":3,"name":"张宝寅","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJaTVyeUl3jiao6DvAibSU9hTjZDiabCgxoSGib9Q7eib3EFFbwgPV6Guj93tvwAHewsmsxicwxhwLfibvDg/132","userId":46},{"rownum":4,"shakeNum":0,"userId":50},{"rownum":5,"name":"","shakeNum":0,"photo":"","userId":54},{"rownum":6,"name":"what are words","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","userId":28},{"rownum":7,"name":"L*^馨儿","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqvOnXot9DZ9cbfG3p66JOxxoVD6EKmLsjuNHer6EjicX65vjcsRakWRJlzPlz1g2zdvLmSUdGENDA/132","userId":41},{"rownum":8,"name":"乐于助人的红领巾","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIMY8TDasmFwLZhkvYfqFiaLEXdFj9xAaibs6vIKLWqYKb1fOR2yaaXorRyKR21ofC6nW4tNL5xbgjg/132","userId":45},{"rownum":9,"shakeNum":0,"userId":49},{"rownum":10,"name":"GoKu","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epwM712JhKOZ1Lhpu1YDH5fMnYLd9G18CeE6S8wlnZ860PuJ9JeZLxnORyJicPH25VutcxPJ6wGVWQ/132","userId":53}],"navigatePages":1,"navigatepageNums":[1],"nextPage":2,"pageNum":1,"pageSize":10,"pages":2,"prePage":0,"size":10,"startRow":1,"total":19}
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
             * endRow : 10
             * firstPage : 1
             * hasNextPage : true
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : false
             * lastPage : 1
             * list : [{"rownum":1,"name":"。。。。。。","shakeNum":45,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKMLNTXbcau6agNfz76FAhtzPsGbicl9Aa0v7Ao2PhFX20dEIqVkEXVVPnwbUeYFeJT4Kk1EU5LPicw/132","time":"17","userId":55},{"rownum":2,"name":"what are words","shakeNum":38,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJbzkWgDI18A699AmcTrjKicDReF6WGv7PiacibIPjiaz4FR8ShYtE4qIZO7dWNKUtzfmHaUuKUr9GZ7A/132","time":"1:34","userId":52},{"rownum":3,"name":"张宝寅","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJaTVyeUl3jiao6DvAibSU9hTjZDiabCgxoSGib9Q7eib3EFFbwgPV6Guj93tvwAHewsmsxicwxhwLfibvDg/132","userId":46},{"rownum":4,"shakeNum":0,"userId":50},{"rownum":5,"name":"","shakeNum":0,"photo":"","userId":54},{"rownum":6,"name":"what are words","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLqwiaNu3PTicDQHmWmqfZd9aMACjkChJgicmjleG1znoMMbVzvFplVv0YG9OVQ70KZWchiaOTNYeWiavg/132","userId":28},{"rownum":7,"name":"L*^馨儿","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqvOnXot9DZ9cbfG3p66JOxxoVD6EKmLsjuNHer6EjicX65vjcsRakWRJlzPlz1g2zdvLmSUdGENDA/132","userId":41},{"rownum":8,"name":"乐于助人的红领巾","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIMY8TDasmFwLZhkvYfqFiaLEXdFj9xAaibs6vIKLWqYKb1fOR2yaaXorRyKR21ofC6nW4tNL5xbgjg/132","userId":45},{"rownum":9,"shakeNum":0,"userId":49},{"rownum":10,"name":"GoKu","shakeNum":0,"photo":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epwM712JhKOZ1Lhpu1YDH5fMnYLd9G18CeE6S8wlnZ860PuJ9JeZLxnORyJicPH25VutcxPJ6wGVWQ/132","userId":53}]
             * navigatePages : 1
             * navigatepageNums : [1]
             * nextPage : 2
             * pageNum : 1
             * pageSize : 10
             * pages : 2
             * prePage : 0
             * size : 10
             * startRow : 1
             * total : 19
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
                 * rownum : 1.0
                 * name : 。。。。。。
                 * shakeNum : 45
                 * photo : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKMLNTXbcau6agNfz76FAhtzPsGbicl9Aa0v7Ao2PhFX20dEIqVkEXVVPnwbUeYFeJT4Kk1EU5LPicw/132
                 * time : 17
                 * userId : 55
                 */

                private double rownum;
                private String name;
                private int shakeNum;
                private String photo;
                private String time;
                private int userId;

                public double getRownum() {
                    return rownum;
                }

                public void setRownum(double rownum) {
                    this.rownum = rownum;
                }

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

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
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
