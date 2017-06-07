package cn.com.sdq.smilefriends.interf;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public interface PermissionListener {
    //所有权限都同意
    void geanted();
    //未同意的权限
    void denied(List<String> deniedPermission);

}
