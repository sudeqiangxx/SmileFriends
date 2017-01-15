package cn.com.sdq.smilefriends.net;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import cn.com.sdq.smilefriends.bean.JakeBean;

/**
 * Created by Administrator on 2017/1/15.
 */

public class JsonToBeanUtils {
    public static List<JakeBean> getResult(String result){
        List<JakeBean> results=null;

        if (result!=null){
            results = new ArrayList<>();
            Gson gson=new Gson();
            JsonObject jsonObject=gson.fromJson(result,JsonObject.class);

            JsonObject json=jsonObject.getAsJsonObject("result");
            if (json!=null){
                JsonObject jsonResult=json.getAsJsonObject("result");
                JsonArray jsonArray=json.getAsJsonArray("data");

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonElement element=jsonArray.get(i);
                    JakeBean jb=gson.fromJson(element,JakeBean.class);
                    if (jb!=null){
                        results.add(jb);
                    }
                }
                return results;

            }else {
                return null;
            }
        }else {
            return null;
        }
    }
}
