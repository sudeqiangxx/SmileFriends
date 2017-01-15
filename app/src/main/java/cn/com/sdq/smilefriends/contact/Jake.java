package cn.com.sdq.smilefriends.contact;

import java.util.List;

import cn.com.sdq.smilefriends.base.BasePresenter;
import cn.com.sdq.smilefriends.base.BaseView;
import cn.com.sdq.smilefriends.bean.JakeBean;

/**
 * Created by sdq on 2016/12/6.
 */

public class Jake {
    public interface View extends BaseView{
        void showContent(List<JakeBean> data);

        void showMorejake(List<JakeBean> data);
    }
    public interface Prestener extends BasePresenter{
        void getJakeList();

        void getMoreJake();

    }
}
