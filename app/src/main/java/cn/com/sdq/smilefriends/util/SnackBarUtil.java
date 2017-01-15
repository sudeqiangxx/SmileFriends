package cn.com.sdq.smilefriends.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by sdq on 2016/10/15.
 */

public class SnackBarUtil {
    public static void showShort(View view,String msg){
        Snackbar.make(view,msg, Snackbar.LENGTH_SHORT).show();
    }
    public static void showLong(View view,String msg){
        Snackbar.make(view,msg, Snackbar.LENGTH_LONG).show();
    }
}
