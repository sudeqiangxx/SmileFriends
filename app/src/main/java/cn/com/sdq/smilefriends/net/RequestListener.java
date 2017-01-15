package cn.com.sdq.smilefriends.net;

import java.util.List;

import cn.com.sdq.smilefriends.bean.JakeBean;

/**
 * Created by sdq on 2017/1/15.
 */

public interface RequestListener {


    /**
     * //请求成功，返回结果
     * @param jakeBeanList
     */
    void requestSuccess(List<JakeBean> jakeBeanList);

    /**
     * 请求失败，返回失败原因
     * @param requestContent
     */
    void requestFailed(String requestContent);
}
