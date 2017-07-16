package cn.com.sdq.smilefriends.base;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.com.sdq.smilefriends.R;
import cn.com.sdq.smilefriends.interf.PermissionListener;
import cn.com.sdq.smilefriends.manager.AppManager;
import cn.com.sdq.smilefriends.util.StringUtils;

/**
 *  Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements
        View.OnClickListener{
    private static final int PERMISSION_QUEST_CODE = 0x112;
    private boolean isVisible;
    protected LayoutInflater mInflater;
    protected ActionBar mActionBar;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        mActionBar = getSupportActionBar();
        mInflater = getLayoutInflater();
        if (hasActionBar()) {
            initActionBar(mActionBar);
        }

        // 通过注解绑定控件
        ButterKnife.bind(this);

        init(savedInstanceState);
    }

    protected void onBeforeSetContentLayout() {
    }

    protected boolean hasActionBar() {
        return getSupportActionBar() != null;
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected int getActionBarTitle() {
        return R.string.app_name;
    }

    protected boolean hasBackButton() {
        return false;
    }

    protected void init(Bundle savedInstanceState) {
    }

    protected void initActionBar(ActionBar actionBar) {
        if (actionBar == null)
            return;
        if (hasBackButton()) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeButtonEnabled(true);
        } else {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            actionBar.setDisplayUseLogoEnabled(false);
            int titleRes = getActionBarTitle();
            if (titleRes != 0) {
                actionBar.setTitle(titleRes);
            }
        }
    }


    public void setActionBarTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            title = getString(R.string.app_name);
        }
        if (hasActionBar() && mActionBar != null) {
            mActionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private PermissionListener permissionListener;
    public void requestRuntimePermission(String[] permissions, PermissionListener listener) {
        permissionListener=listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissionList.toArray(new String[permissionList.size()]), PERMISSION_QUEST_CODE);
        }else {
            permissionListener.geanted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_QUEST_CODE:
                if (grantResults.length>0){
                    List<String> deniedPermissionList=new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult=grantResults[i];
                        String permission=permissions[i];
                        if (grantResult!=PackageManager.PERMISSION_GRANTED){
                            deniedPermissionList.add(permission);
                        }
                    }
                    if (deniedPermissionList.isEmpty()){
                        permissionListener.geanted();
                    }else {
                        permissionListener.denied(deniedPermissionList);
                    }
                }
                break;
            default:break;
        }
    }
    class NetTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }
    }



}
