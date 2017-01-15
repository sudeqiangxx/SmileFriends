package cn.com.sdq.smilefriends.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import cn.com.sdq.smilefriends.R;

/**
 * Created by sdq on 2016/10/15.
 */

public class DialogUitl {
    public static void showProgressDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(view);
        builder.create().show();
    }
}
