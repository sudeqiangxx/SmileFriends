package cn.com.sdq.smilefriends.net;

/**
 * Created by sdq on 2017/1/15.
 */

public interface IjakeData {
    /**
     * 获取按时间分配的笑话
     * @param page 请求的当前页
     */
    void getJake(int page) throws Exception;

    /**
     * 获取最新笑话
     * @param page 请求的当前页
     */
    void getNewJake(int page) throws Exception;

    /**
     * 获取按时间分配的趣图
     * @param page  当前页
     */
    void getJakePhoto(int page) throws Exception;

    /**
     * 获取最新趣图
     * @param page 当前页
     */
    void getNewJakePhoto(int page) throws Exception;
}
