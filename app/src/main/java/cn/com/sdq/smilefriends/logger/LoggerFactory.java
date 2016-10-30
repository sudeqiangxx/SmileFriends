package cn.com.sdq.smilefriends.logger;

/**
 * Created by Administrator on 2016/10/30.
 */

public class LoggerFactory {

    /**
     * 获取日志
     *
     * @param
     * @return
     */
    public static LoggerManager getLogger(String name) {
        // 获取slf4 logger
        org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(name);
        LoggerManager logger = new LoggerManager(log);
        return logger;
    }

    /**
     * 获取日志
     *
     * @param clazz
     * @return
     */
    public static LoggerManager getLogger(Class<?> clazz) {
        // 获取slf4 logger
        org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(clazz);
        LoggerManager logger = new LoggerManager(log);
        return logger;
    }
}
