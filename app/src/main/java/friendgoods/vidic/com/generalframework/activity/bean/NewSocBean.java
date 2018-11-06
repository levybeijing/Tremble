package friendgoods.vidic.com.generalframework.activity.bean;

import java.util.List;

public class NewSocBean {

    /**
     * list : [{"name":"大黑","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/K6gwAt74kEjUC9F0aPeVicPbibx6wYJhg0yfreXK85dvMIia5WJgkpKgjTq1QUkEFtCzCC50qNScKkuoANYWu8edg/132","logo":"man2.png","id":1748,"is_use":1,"userId":193,"status":2},{"name":"levy","photo":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJsfB1qXEvdNSkKYIV3QqrWvM320PpAupPEjC8UBE7UzlWoDpzbZP6zmHBwPWpB9JlPSVBVhH6XTQ/132","logo":"woman2.png","id":1748,"is_use":1,"userId":192,"status":1}]
     * type : 0
     */

    private int type;
    private List<ListBean> list;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }
}
