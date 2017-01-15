package cn.com.sdq.smilefriends.bean;

import java.util.List;

/**
 * Created by sudeqiang on 2016/12/17.
 */

public class HttpResult {
    /**
     * 服务器返回的错误码
     * */
    private int error_code;
    /**
     * 返回说明
     * */
    private String reason;
    /**
     * 返回服务器返回内容
     * */
    private List<JakeBean> jakeBeanList;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<JakeBean> getJakeBeanList() {
        return jakeBeanList;
    }

    public void setJakeBeanList(List<JakeBean> jakeBeanList) {
        this.jakeBeanList = jakeBeanList;
    }
}
