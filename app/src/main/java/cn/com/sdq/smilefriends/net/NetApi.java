package cn.com.sdq.smilefriends.net;

/**
 * Created by sdq on 2017/1/15.
 */

public class NetApi {
    public static final String API_NET_URI="http://japi.juhe.cn/joke/content/list.from";//按照更新时间请求数据请求接口地址
    public static final String API_NET_NEW_URI="http://japi.juhe.cn/joke/content/text.from";//最新笑话请求接口地址
    public static final String API_NET_PHOTO_URI="http://japi.juhe.cn/joke/img/list.from";//更新时间趣图请求接口地址
    public static final String API_NET_NEWPHOTO_URI="http://japi.juhe.cn/joke/img/text.from";//最新趣图请求接口地址

    public static final String[] UPDATE_INDEX_KEY=new String[]{"desc","asc"};
    public static final String REQUEST_NUMBER="20";//请求页数
}
