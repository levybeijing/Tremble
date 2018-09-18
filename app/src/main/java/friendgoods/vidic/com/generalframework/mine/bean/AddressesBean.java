package friendgoods.vidic.com.generalframework.mine.bean;

import java.util.List;

public class AddressesBean {

    /**
     * data : [{"site":"天津市塘沽区洞庭路融汇大厦808","consignee":"申金梦","createTime":"2018-08-15 11:00:03","mobile":"13752672276","id":1,"is_use":1,"userId":8,"status":1}]
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
         * site : 天津市塘沽区洞庭路融汇大厦808
         * consignee : 申金梦
         * createTime : 2018-08-15 11:00:03
         * mobile : 13752672276
         * id : 1
         * is_use : 1
         * userId : 8
         * status : 1
         */

        private String site;
        private String consignee;
        private String createTime;
        private String mobile;
        private int id;
        private int is_use;
        private int userId;
        private int status;

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_use() {
            return is_use;
        }

        public void setIs_use(int is_use) {
            this.is_use = is_use;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
