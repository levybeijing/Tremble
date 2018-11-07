package friendgoods.vidic.com.generalframework.activity.bean;

import java.util.List;

public class SetTimeBean {

    /**
     * time : 10:10:00
     * type : 3
     */
    private List<TimeBean> list;

    public List<TimeBean> getList() {
        return list;
    }

    public void setList(List<TimeBean> list) {
        this.list = list;
    }

    public static class TimeBean {
        public String time;
        public int type;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
