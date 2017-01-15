package cn.com.sdq.smilefriends.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sdq on 2016/10/30.
 */

public class NetData {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY ="4038445a90fbbf1fd6e286307751d0b0";

    public static void main(String[] args) {

    }
    //1.按更新时间查询笑话
    public static String getRequestUpdateTime(String page) throws Exception {
        String result =null;
        Map params = new HashMap();//请求参数
        params.put("sort",NetApi.UPDATE_INDEX_KEY);//类型，desc:指定时间之前发布的，asc:指定时间之后发布的
        params.put("page",page);//当前页数,默认1
        params.put("pagesize",NetApi.REQUEST_NUMBER);//每次返回条数,默认1,最大20
        params.put("time",System.currentTimeMillis()/1000);//时间戳（10位），如：1418816972
        params.put("key",APPKEY);//您申请的key
        result =net(NetApi.API_NET_URI, params, "GET");
        return result;
    }

    //2.最新笑话
    public static String getRequestNewJake(String page) throws Exception {
        String result =null;
        Map params = new HashMap();//请求参数
        params.put("page",page);//当前页数,默认1
        params.put("pagesize",NetApi.REQUEST_NUMBER);//每次返回条数,默认1,最大20
        params.put("key",APPKEY);//您申请的key
        result =net(NetApi.API_NET_NEW_URI, params, "GET");
        return result;
    }

    //3.按更新时间查询趣图
    public static String getRequestPhoto(String page) throws Exception {
        String result =null;
        Map params = new HashMap();//请求参数
        params.put("sort",NetApi.UPDATE_INDEX_KEY);//类型，desc:指定时间之前发布的，asc:指定时间之后发布的
        params.put("page",page);//当前页数,默认1
        params.put("pagesize",NetApi.REQUEST_NUMBER);//每次返回条数,默认1,最大20
        params.put("time",System.currentTimeMillis()/1000);//时间戳（10位），如：1418816972
        params.put("key",APPKEY);//您申请的key
        result =net(NetApi.API_NET_PHOTO_URI, params, "GET");
        return result;
    }

    //4.最新趣图
    public static String getRequestNewPhoto(String page) throws Exception {
        String result =null;
        Map params = new HashMap();//请求参数
        params.put("page",page);//当前页数,默认1
        params.put("pagesize",NetApi.REQUEST_NUMBER);//每次返回条数,默认1,最大20
        params.put("key",APPKEY);//您申请的key
        result =net(NetApi.API_NET_NEWPHOTO_URI, params, "GET");
        return result;
    }


    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
