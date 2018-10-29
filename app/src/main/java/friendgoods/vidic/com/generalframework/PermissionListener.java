package friendgoods.vidic.com.generalframework;

import java.util.List;

public interface PermissionListener {
    //全部授权成功
    void onGranted();
    //授权部分
    void  onGranted(List<String> grantedPermission);
    //拒绝授权
    void  onDenied(List<String> deniedPermission);
}
