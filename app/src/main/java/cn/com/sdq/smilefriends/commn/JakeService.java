package cn.com.sdq.smilefriends.commn;

import java.util.List;

import cn.com.sdq.smilefriends.bean.JakeBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sudeqiang on 2016/12/10.
 */

public interface JakeService {

    //http://japi.juhe.cn/joke/content/list.from
    // ?key=您申请的KEY&page=2&pagesize=10&sort=asc&time=1418745237
    //"http://japi.juhe.cn/joke/content/text.from";
    public static final String PATH_CACHE = "http-cache";
    //按指定时间查找笑话
    @GET("/joke/content/list.from")
    Call<String>
    getJakelistResult(@Header("key") String key,
                      @Query("page") int page,
                      @Query("sort") String sort,
                      @Query("time") long time);
    //最新笑话
    @GET("/joke/content/text.from")
    Observable<JakeHttppResponse<List<JakeBean>>>
    getJakeNewResult(@Header("key") String key,
                     @Query("page") int page,
                     @Query("sort") int sort,
                     @Query("time") long time);
    //按指定时间查找笑话
    @GET("/joke/img/list.from")
    Observable<JakeHttppResponse<List<JakeBean>>>
    getJakeimgResult(@Header("key") String key,
                     @Query("page") int page,
                     @Query("sort") String sort,
                     @Query("time") long time);
    //最新趣图
    @GET("/joke/img/text.from")
    Observable<JakeHttppResponse<List<JakeBean>>>
    getNewJakeimgResult(@Header("key") String key,
                        @Query("page") int page,
                        @Query("sort") String sort,
                        @Query("time") long time);


}
