package friendgoods.vidic.com.generalframework.activity.bean;

import java.util.List;

public class PKSocketBean {
    private String type;
    private String roomId;
    private String userId;

    private String master;//房主master:1 组员 0
    private String num;
    private String status;
    private String isUse;

    private String message;//错误信息
    private String time;

    private List<GamerBean> user;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<GamerBean> getUser() {
        return user;
    }

    public void setUser(List<GamerBean> user) {
        this.user = user;
    }
}
