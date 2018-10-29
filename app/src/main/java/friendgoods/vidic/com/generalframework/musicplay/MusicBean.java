package friendgoods.vidic.com.generalframework.musicplay;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MusicBean {

    /**
     * data : [{"titel":"MKJ - Time","rank":1,"url":"MKJ - Time.mp3"}]
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

    public static class DataBean implements Parcelable {
        /**
         * titel : MKJ - Time
         * rank : 1
         * url : MKJ - Time.mp3
         */

        private String titel;
        private int rank;
        private String url;

        protected DataBean(Parcel in) {
            titel = in.readString();
            rank = in.readInt();
            url = in.readString();
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

        public String getTitel() {
            return titel;
        }

        public void setTitel(String titel) {
            this.titel = titel;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(titel);
            dest.writeInt(rank);
            dest.writeString(url);
        }
    }
}
