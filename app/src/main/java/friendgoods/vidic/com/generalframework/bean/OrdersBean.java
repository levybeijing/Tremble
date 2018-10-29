package friendgoods.vidic.com.generalframework.bean;


import java.util.List;

public class OrdersBean {


    /**
     * data : {"PageInfo":{"endRow":10,"firstPage":0,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":0,"list":[{"money":11,"goodsId":5,"integral":11,"num":1,"integrals":11,"photo":"1535010723.png","order_uuid":"201809030934335550","id":54,"moneys":"5.00","goodsName":"测试商品1","status":3},{"money":1,"goodsId":16,"integral":2,"num":1,"integrals":2,"photo":"1535091612.jpg","order_uuid":"201809030934413040","id":55,"moneys":"16.00","goodsName":"小程序测试","status":3},{"money":1,"goodsId":16,"integral":2,"num":1,"integrals":2,"photo":"1535091612.jpg","order_uuid":"201809030936567710","id":56,"moneys":"16.00","goodsName":"小程序测试","status":3},{"money":123,"goodsId":17,"integral":123,"num":1,"integrals":123,"photo":"/doutui/1535093174.jpg","order_uuid":"201809030937057450","id":57,"moneys":"17.00","goodsName":"123","status":3},{"money":11,"goodsId":6,"integral":11,"num":1,"integrals":11,"photo":"1535010723.png","order_uuid":"201809030941198660","id":58,"moneys":"6.00","goodsName":"测试商品2","status":2},{"money":11,"goodsId":8,"integral":11,"num":3,"integrals":33,"photo":"1535010723.png","order_uuid":"201809030941277510","id":59,"moneys":"8.00","goodsName":"测试商品4","status":3},{"money":1,"goodsId":16,"integral":2,"num":9,"integrals":18,"photo":"1535091612.jpg","order_uuid":"201809030941382220","id":60,"moneys":"16.00","goodsName":"小程序测试","status":2},{"money":11,"goodsId":7,"integral":11,"num":1,"integrals":11,"photo":"1535010723.png","order_uuid":"201809030941478230","id":61,"moneys":"7.00","goodsName":"测试商品3","status":3},{"money":11,"goodsId":5,"integral":11,"num":8,"integrals":88,"photo":"1535010723.png","order_uuid":"201809030941593120","id":62,"moneys":"5.00","goodsName":"测试商品1","status":2},{"money":1231,"goodsId":25,"integral":23,"num":2,"integrals":46,"photo":"1536040319.jpg","order_uuid":"201809051547555330","id":63,"moneys":"25.00","goodsName":"123","status":1}],"navigatePages":0,"navigatepageNums":[],"nextPage":0,"pageNum":1,"pageSize":10,"pages":3,"prePage":0,"size":10,"startRow":1,"total":26}}
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
         * PageInfo : {"endRow":10,"firstPage":0,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":0,"list":[{"money":11,"goodsId":5,"integral":11,"num":1,"integrals":11,"photo":"1535010723.png","order_uuid":"201809030934335550","id":54,"moneys":"5.00","goodsName":"测试商品1","status":3},{"money":1,"goodsId":16,"integral":2,"num":1,"integrals":2,"photo":"1535091612.jpg","order_uuid":"201809030934413040","id":55,"moneys":"16.00","goodsName":"小程序测试","status":3},{"money":1,"goodsId":16,"integral":2,"num":1,"integrals":2,"photo":"1535091612.jpg","order_uuid":"201809030936567710","id":56,"moneys":"16.00","goodsName":"小程序测试","status":3},{"money":123,"goodsId":17,"integral":123,"num":1,"integrals":123,"photo":"/doutui/1535093174.jpg","order_uuid":"201809030937057450","id":57,"moneys":"17.00","goodsName":"123","status":3},{"money":11,"goodsId":6,"integral":11,"num":1,"integrals":11,"photo":"1535010723.png","order_uuid":"201809030941198660","id":58,"moneys":"6.00","goodsName":"测试商品2","status":2},{"money":11,"goodsId":8,"integral":11,"num":3,"integrals":33,"photo":"1535010723.png","order_uuid":"201809030941277510","id":59,"moneys":"8.00","goodsName":"测试商品4","status":3},{"money":1,"goodsId":16,"integral":2,"num":9,"integrals":18,"photo":"1535091612.jpg","order_uuid":"201809030941382220","id":60,"moneys":"16.00","goodsName":"小程序测试","status":2},{"money":11,"goodsId":7,"integral":11,"num":1,"integrals":11,"photo":"1535010723.png","order_uuid":"201809030941478230","id":61,"moneys":"7.00","goodsName":"测试商品3","status":3},{"money":11,"goodsId":5,"integral":11,"num":8,"integrals":88,"photo":"1535010723.png","order_uuid":"201809030941593120","id":62,"moneys":"5.00","goodsName":"测试商品1","status":2},{"money":1231,"goodsId":25,"integral":23,"num":2,"integrals":46,"photo":"1536040319.jpg","order_uuid":"201809051547555330","id":63,"moneys":"25.00","goodsName":"123","status":1}],"navigatePages":0,"navigatepageNums":[],"nextPage":0,"pageNum":1,"pageSize":10,"pages":3,"prePage":0,"size":10,"startRow":1,"total":26}
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
             * firstPage : 0
             * hasNextPage : true
             * hasPreviousPage : false
             * isFirstPage : true
             * isLastPage : false
             * lastPage : 0
             * list : [{"money":11,"goodsId":5,"integral":11,"num":1,"integrals":11,"photo":"1535010723.png","order_uuid":"201809030934335550","id":54,"moneys":"5.00","goodsName":"测试商品1","status":3},{"money":1,"goodsId":16,"integral":2,"num":1,"integrals":2,"photo":"1535091612.jpg","order_uuid":"201809030934413040","id":55,"moneys":"16.00","goodsName":"小程序测试","status":3},{"money":1,"goodsId":16,"integral":2,"num":1,"integrals":2,"photo":"1535091612.jpg","order_uuid":"201809030936567710","id":56,"moneys":"16.00","goodsName":"小程序测试","status":3},{"money":123,"goodsId":17,"integral":123,"num":1,"integrals":123,"photo":"/doutui/1535093174.jpg","order_uuid":"201809030937057450","id":57,"moneys":"17.00","goodsName":"123","status":3},{"money":11,"goodsId":6,"integral":11,"num":1,"integrals":11,"photo":"1535010723.png","order_uuid":"201809030941198660","id":58,"moneys":"6.00","goodsName":"测试商品2","status":2},{"money":11,"goodsId":8,"integral":11,"num":3,"integrals":33,"photo":"1535010723.png","order_uuid":"201809030941277510","id":59,"moneys":"8.00","goodsName":"测试商品4","status":3},{"money":1,"goodsId":16,"integral":2,"num":9,"integrals":18,"photo":"1535091612.jpg","order_uuid":"201809030941382220","id":60,"moneys":"16.00","goodsName":"小程序测试","status":2},{"money":11,"goodsId":7,"integral":11,"num":1,"integrals":11,"photo":"1535010723.png","order_uuid":"201809030941478230","id":61,"moneys":"7.00","goodsName":"测试商品3","status":3},{"money":11,"goodsId":5,"integral":11,"num":8,"integrals":88,"photo":"1535010723.png","order_uuid":"201809030941593120","id":62,"moneys":"5.00","goodsName":"测试商品1","status":2},{"money":1231,"goodsId":25,"integral":23,"num":2,"integrals":46,"photo":"1536040319.jpg","order_uuid":"201809051547555330","id":63,"moneys":"25.00","goodsName":"123","status":1}]
             * navigatePages : 0
             * navigatepageNums : []
             * nextPage : 0
             * pageNum : 1
             * pageSize : 10
             * pages : 3
             * prePage : 0
             * size : 10
             * startRow : 1
             * total : 26
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
            private List<?> navigatepageNums;

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

            public List<?> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<?> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }

            public static class ListBean {
                /**
                 * money : 11.0
                 * goodsId : 5
                 * integral : 11
                 * num : 1
                 * integrals : 11
                 * photo : 1535010723.png
                 * order_uuid : 201809030934335550
                 * id : 54
                 * moneys : 5.00
                 * goodsName : 测试商品1
                 * status : 3
                 */

                private double money;
                private int goodsId;
                private int integral;
                private int num;
                private int integrals;
                private String photo;
                private String order_uuid;
                private int id;
                private String moneys;
                private String goodsName;
                private int status;

                public double getMoney() {
                    return money;
                }

                public void setMoney(double money) {
                    this.money = money;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

                public int getIntegral() {
                    return integral;
                }

                public void setIntegral(int integral) {
                    this.integral = integral;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public int getIntegrals() {
                    return integrals;
                }

                public void setIntegrals(int integrals) {
                    this.integrals = integrals;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public String getOrder_uuid() {
                    return order_uuid;
                }

                public void setOrder_uuid(String order_uuid) {
                    this.order_uuid = order_uuid;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getMoneys() {
                    return moneys;
                }

                public void setMoneys(String moneys) {
                    this.moneys = moneys;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}
