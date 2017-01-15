package cn.com.sdq.smilefriends.net;

import java.util.List;

import cn.com.sdq.smilefriends.bean.JakeBean;

/**
 * Created by sdq on 2017/1/15.
 */

public class JakeQuestData implements IjakeData {
    private RequestListener requestListener;
    private JakeQuestData jakeQuestData;
    public JakeQuestData(RequestListener requestListener){
        this.requestListener=requestListener;
    }
    public JakeQuestData getInstance(RequestListener requestListener){
        if (this.jakeQuestData==null){
            synchronized (JakeQuestData.class){
                if (jakeQuestData==null){
                    jakeQuestData=new JakeQuestData(requestListener);
                }

            }
        }
        return jakeQuestData;
    }
    @Override
    public void getJake(int page) throws Exception {
        String pages=Integer.toString(page);
        String result=NetData.getRequestUpdateTime(pages);
        jsonToBean(result);
    }

    @Override
    public void getNewJake(int page) throws Exception {
        String pages=Integer.toString(page);
        String result=NetData.getRequestNewJake(pages);
        jsonToBean(result);

    }

    @Override
    public void getJakePhoto(int page) throws Exception {
        String pages=Integer.toString(page);
        String result=NetData.getRequestPhoto(pages);
        jsonToBean(result);
    }

    @Override
    public void getNewJakePhoto(int page) throws Exception {
        String pages=Integer.toString(page);
        String result=NetData.getRequestNewPhoto(pages);
        jsonToBean(result);
    }

    private void jsonToBean(String result){
        if (result!=null){
            List<JakeBean> resultlist=JsonToBeanUtils.getResult(result);
            if (resultlist!=null){
                requestListener.requestSuccess(resultlist);
            }else {
                requestListener.requestFailed("没有更多数据");
            }

        }else {
            requestListener.requestFailed("no data");
        }
    }

    public static void main(String[] args) {
        RequestListener requestListener=new RequestListener() {
            @Override
            public void requestSuccess(List<JakeBean> jakeBeanList) {
                System.out.print("解析后的结果是"+jakeBeanList.toString());
            }

            @Override
            public void requestFailed(String requestContent) {
                System.out.print(requestContent);
            }
        };
       JakeQuestData jakeQuestData=new JakeQuestData(requestListener);
        try {
            jakeQuestData.getJake(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
