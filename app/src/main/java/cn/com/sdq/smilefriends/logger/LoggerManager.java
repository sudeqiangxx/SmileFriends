package cn.com.sdq.smilefriends.logger;

/**
 * Created by Administrator on 2016/10/30.
 */

public class LoggerManager {

    /**
     * log
     */
    private org.slf4j.Logger log;

    /**
     * 构造函数
     *
     * @param
     */
    public LoggerManager(org.slf4j.Logger log) {
        this.log = log;
    }

    /**
     * 调试模式是否开启
     *
     * @return
     */
    public boolean isDebugEnabled() {
        //return true;
        return log.isDebugEnabled();
    }

    /**
     * INFO模式是否开启
     *
     * @return
     */
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    /**
     * 错误模式是否开启
     *
     * @return
     */
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    /**
     * 打印错误日志
     *
     * @param message
     */
    public void error(String message) {
        log.error(message);
    }

    /**
     * 打印错误日志
     *
     * @param message
     */
    public void error(String message, Throwable exception) {
        log.error(message, exception);
    }

    /**
     * 打印警告日志
     * @param message
     */
    public void warn(String message) {
        log.warn(message);
    }

    /**
     * 打印警告日志
     *
     * @param message
     */
    public void warn(String message, Throwable exception) {
        log.warn(message, exception);
    }

    /**
     * 打印信息日志
     *
     * @param message
     */
    public void info(String message) {
        log.info(message);
    }

    /**
     * 打印信息日志
     *
     * @param message
     */
    public void info(String message, Throwable exception) {
        log.info(message, exception);
    }

    /**
     * 打印调试日志
     *
     * @param message
     */

    public void debug(int message) {
        this.debug(String.valueOf(message));
    }
    public void debug(long message) {
        this.debug(String.valueOf(message));
    }
    public void debug(double message) {
        this.debug(String.valueOf(message));
    }
    public void debug(Boolean message) {this.debug(String.valueOf(message));}
    /**
     * 打印调试日志
     *
     * @param message
     */
    public void debug(String message) {
        log.debug(message);
    }
    /**
     * 打印调试日志
     *
     * @param message
     *
     */
    public void debug(String message, Throwable exception) {
        log.debug(message, exception);
    }
}
