package friendgoods.vidic.com.generalframework.bean;

import android.os.Parcel;
import android.os.Parcelable;

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

    public static class DataBean implements Parcelable{
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

        protected DataBean(Parcel in) {
            site = in.readString();
            consignee = in.readString();
            createTime = in.readString();
            mobile = in.readString();
            id = in.readInt();
            is_use = in.readInt();
            userId = in.readInt();
            status = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(site);
            dest.writeString(consignee);
            dest.writeString(createTime);
            dest.writeString(mobile);
            dest.writeInt(id);
            dest.writeInt(is_use);
            dest.writeInt(userId);
            dest.writeInt(status);
        }
    }
}
