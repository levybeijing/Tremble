package friendgoods.vidic.com.generalframework.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailGoodsBean {


    /**
     * data : {"photo1":"[\"1536040307.jpg\",\"1536040309.png\",\"1536040312.jpg\"]","money":1231,"createTime":"2018-09-04 13:52:14","goodsId":25,"integral":23,"name":"123","photo":"1536040319.jpg","type":2,"is_use":1,"photo2":"1536040322.jpg","photo3":"[\"1536040307.jpg\",\"1536040309.png\",\"1536040312.jpg\"]"}
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
         * photo1 : ["1536040307.jpg","1536040309.png","1536040312.jpg"]
         * money : 1231.0
         * createTime : 2018-09-04 13:52:14
         * goodsId : 25
         * integral : 23
         * name : 123
         * photo : 1536040319.jpg
         * type : 2
         * is_use : 1
         * photo2 : 1536040322.jpg
         * photo3 : ["1536040307.jpg","1536040309.png","1536040312.jpg"]
         */

        private String photo1;
        private double money;
        private String createTime;
        private int goodsId;
        private int integral;
        private String name;
        private String photo;
        private int type;
        private int is_use;
        private String photo2;
        private String photo3;

        protected DataBean(Parcel in) {
            photo1 = in.readString();
            money = in.readDouble();
            createTime = in.readString();
            goodsId = in.readInt();
            integral = in.readInt();
            name = in.readString();
            photo = in.readString();
            type = in.readInt();
            is_use = in.readInt();
            photo2 = in.readString();
            photo3 = in.readString();
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIs_use() {
            return is_use;
        }

        public void setIs_use(int is_use) {
            this.is_use = is_use;
        }

        public String getPhoto2() {
            return photo2;
        }

        public void setPhoto2(String photo2) {
            this.photo2 = photo2;
        }

        public String getPhoto3() {
            return photo3;
        }

        public void setPhoto3(String photo3) {
            this.photo3 = photo3;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(photo1);
            dest.writeDouble(money);
            dest.writeString(createTime);
            dest.writeInt(goodsId);
            dest.writeInt(integral);
            dest.writeString(name);
            dest.writeString(photo);
            dest.writeInt(type);
            dest.writeInt(is_use);
            dest.writeString(photo2);
            dest.writeString(photo3);
        }
    }
}
