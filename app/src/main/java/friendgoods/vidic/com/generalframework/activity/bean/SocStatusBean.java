package friendgoods.vidic.com.generalframework.activity.bean;

public class SocStatusBean {
    private String name;
    private String phone;
    private String logo;
    private int is_use;

    private int id;
    private int userId;
    private int status;

    private int type;
//const ty0 = 0; // 刚进去房间
//const ty1 = 1; // 组员退出
//const ty2 = 2; // 房主退出
//const ty3 = 3; // 游戏时间
//const ty4 = 4; // 修改状态(准备/未准备)
//const ty5 = 5; // 开始游戏
//const ty6 = 6; // 同步计数
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
