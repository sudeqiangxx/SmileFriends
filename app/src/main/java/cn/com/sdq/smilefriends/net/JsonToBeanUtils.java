package cn.com.sdq.smilefriends.net;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.com.sdq.smilefriends.bean.JakeBean;
import cn.com.sdq.smilefriends.util.utils.L;

/**
 * Created by Administrator on 2017/1/15.
 */

public class JsonToBeanUtils {

    public static List<JakeBean> getResult(String result) {
        L.i("json数据："+result);
        List<JakeBean> results = null;
        if (result != null) {
            results = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.has("result")) {

                    JSONObject js = jsonObject.getJSONObject("result");
                    JSONArray array=js.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsb= (JSONObject) array.get(i);
                        JakeBean jake = new JakeBean();
                        jake.setContent(jsb.getString("content"));
                        jake.setHashId(jsb.getString("hashId"));
                        jake.setUnixtime(jsb.getLong("unixtime"));
                        jake.setUpdatetime(jsb.getString("updatetime"));
                        if (jsb.has("url")){
                            jake.setUrl(jsb.getString("url"));
                        }
                        results.add(jake);
                    }
                    return results;
                } else {
                    return results;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

//    public static List<JakeBean> getResult(String result){
//        List<JakeBean> results=null;
//
//        if (result!=null){
//            results = new ArrayList<>();
//            Gson gson=new Gson();
//            JsonObject jsonObject=gson.fromJson(result,JsonObject.class);
//
//            JsonObject json=jsonObject.getAsJsonObject("result");
//            if (json!=null){
//                JsonObject jsonResult=json.getAsJsonObject("result");
//                JsonArray jsonArray=json.getAsJsonArray("data");
//
//                for (int i = 0; i < jsonArray.size(); i++) {
//                    JsonElement element=jsonArray.get(i);
//                    JakeBean jb=gson.fromJson(element,JakeBean.class);
//                    if (jb!=null){
//                        results.add(jb);
//                    }
//                }
//                return results;
//
//            }else {
//                return null;
//            }
//        }else {
//            return null;
//        }
//    }
}
