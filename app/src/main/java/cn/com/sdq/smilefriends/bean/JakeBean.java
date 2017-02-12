package cn.com.sdq.smilefriends.bean;

/**
 * Created by Administrator on 2016/12/6.
 */

public class JakeBean {
//
//            "content": "有一天晚上我俩一起吃西瓜，老大把西瓜籽很整洁的吐在了一张纸上，\r\n过了几天，我从教室回但宿舍看到老大在磕瓜子，\r\n我就问他：老大，你什么时候买的瓜子？\r\n老大说：刚晒好，说着抓了一把要递给我……",
//                "hashId": "bcc5fdc2fb6efc6db33fa242474f108a",
//                "unixtime": 1418814837,
//                "updatetime": "2014-12-17 19:13:57"
//    "content": "二汪",
//            "hashId": "13AF55EB201FADD4DB8AD3C0FC053E72",
//            "unixtime": 1418954054,
//            "updatetime": "2014-12-19 09:54:14",
//            "url": "http://img.juhe.cn/joke/201412/19/13AF55EB201FADD4DB8AD3C0FC053E72.gif"
    private String content;
    private long unixtime;
    private String updatetime;
    private String hashId;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(long unixtime) {
        this.unixtime = unixtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    @Override
    public String toString() {
        return "JakeBean{" +
                "content='" + content + '\'' +
                ", unixtime=" + unixtime +
                ", updatetime='" + updatetime + '\'' +
                ", hashId='" + hashId + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
