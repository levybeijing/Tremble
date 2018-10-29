package friendgoods.vidic.com.generalframework.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailOrderBean {

    /**
     * data : {"consignee":"哈哈哈","goodsId":29,"num":3,"mobile":"18666295435","photo":"1536041007.png","photo2":"1536041010.jpg","site":"内蒙古 通辽市 库伦旗 梅苑小区2-2-303","photo1":"[\"1536041004.jpg\"]","money":111,"createTime":"2018-09-28 11:59:15","integral":111,"integrals":333,"order_uuid":"201809281159153020","id":125,"moneys":"333.00","goodsName":"111","status":2}
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

    public static class DataBean implements Parcelable{
        /**
         * consignee : 哈哈哈
         * goodsId : 29
         * num : 3
         * mobile : 18666295435
         * photo : 1536041007.png
         * photo2 : 1536041010.jpg
         * site : 内蒙古 通辽市 库伦旗 梅苑小区2-2-303
         * photo1 : ["1536041004.jpg"]
         * money : 111.0
         * createTime : 2018-09-28 11:59:15
         * integral : 111
         * integrals : 333
         * order_uuid : 201809281159153020
         * id : 125
         * moneys : 333.00
         * goodsName : 111
         * status : 2
         */

        private String consignee;
        private int goodsId;
        private int num;
        private String mobile;
        private String photo;
        private String photo2;
        private String site;
        private String photo1;
        private double money;
        private String createTime;
        private int integral;
        private int integrals;
        private String order_uuid;
        private int id;
        private String moneys;
        private String goodsName;
        private int status;

        protected DataBean(Parcel in) {
            consignee = in.readString();
            goodsId = in.readInt();
            num = in.readInt();
            mobile = in.readString();
            photo = in.readString();
            photo2 = in.readString();
            site = in.readString();
            photo1 = in.readString();
            money = in.readDouble();
            createTime = in.readString();
            integral = in.readInt();
            integrals = in.readInt();
            order_uuid = in.readString();
            id = in.readInt();
            moneys = in.readString();
            goodsName = in.readString();
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

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPhoto2() {
            return photo2;
        }

        public void setPhoto2(String photo2) {
            this.photo2 = photo2;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIntegrals() {
            return integrals;
        }

        public void setIntegrals(int integrals) {
            this.integrals = integrals;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(consignee);
            dest.writeInt(goodsId);
            dest.writeInt(num);
            dest.writeString(mobile);
            dest.writeString(photo);
            dest.writeString(photo2);
            dest.writeString(site);
            dest.writeString(photo1);
            dest.writeDouble(money);
            dest.writeString(createTime);
            dest.writeInt(integral);
            dest.writeInt(integrals);
            dest.writeString(order_uuid);
            dest.writeInt(id);
            dest.writeString(moneys);
            dest.writeString(goodsName);
            dest.writeInt(status);
        }
    }
}
