package com.healthmall.sail.cat_doctor.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.healthmall.sail.cat_doctor.MyApplication;
import com.healthmall.sail.cat_doctor.R;
import com.healthmall.sail.cat_doctor.base.BaseActivity;
import com.healthmall.sail.cat_doctor.base.MyThrowable;
import com.healthmall.sail.cat_doctor.bean.User;
import com.healthmall.sail.cat_doctor.delegate.IndexDelegate;
import com.healthmall.sail.cat_doctor.serialport.SerialPortCmd;
import com.healthmall.sail.cat_doctor.utils.VoiceMamanger;

import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class IndexActivity extends BaseActivity<IndexDelegate> {

    Subscription sbTimer;

    boolean isPause = false;

    private final long delay = 60000;

    @OnClick(R.id.iv_video)
    public void iv_videoClick() {
        startActivity(VideoActivity.class, false);
    }

    @OnClick(R.id.iv_manager)
    public void iv_managerClick() {
        startActivity(ManagerLoginActivity.class, false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        startActivity(VideoActivity.class, false);

    }

    @Override
    public void loginSucc() {

        SerialPortCmd.scanSucc();

        if (!isPause) {
            log("MyApplication.get().getCurrUser().getMemberAge()==>" + MyApplication.get().getCurrUser().getMemberAge());
            log("MyApplication.get().getCurrUser().getMemberSex()==>" + MyApplication.get().getCurrUser().getMemberSex());
            if (MyApplication.get().getCurrUser().getMemberAge() != null
                    && MyApplication.get().getCurrUser().getMemberSex() != null) {
                startActivity(TipActivity.class, false);
            } else {
                startActivity(InfoActivity.class, false);
            }
        }
    }

    @OnClick(R.id.rl_root)
    public void rl_rootClick(){
//        private String mallId;
//        private String memberName;
//        private Integer memberSex;
//        private String memHeadImg;
//        private String accessToken;
//        private String questionAnswerId;
//        private String questionResultName;
//        private String birthday;
//        private boolean authentication;
//        private boolean talent;
//        private boolean isUsed;
//        User user = new User("test", "测试用户", 0, "", "testtoken", "answerId", "", 20);
//        MyApplication.get().loginSucc(user);

//        MyApplication.get().loginSucc(new Gson().fromJson("{\"isPhysical\":[{\"type\":\"气虚质\",\"score\":59,\"rank\":1},{\"type\":\"痰湿质\",\"score\":65,\"rank\":1},{\"type\":\"阳虚质\",\"score\":50,\"rank\":1},{\"type\":\"特禀质\",\"score\":64,\"rank\":1},{\"type\":\"阴虚质\",\"score\":50,\"rank\":1}],\"memberAge\":18,\"isFcImg\":\"https://panda-1257270219.cos.ap-guangzhou.myqcloud.com/wxuser-1/wxf6b91ba0966277bd.o6zAJs0Y7qMlYcOF7fTkmchaCqK4.z5gIhzaZA1tG754336c35612032f06385abb48fa0a4a.png\",\"memberName\":\"朱耘籍的爸爸\",\"memHeadImg\":\"https://wx.qlogo.cn/mmopen/vi_32/PHlRqre8XRS8qEFThHR4JCLeVxOtaS2p9GpPZ2UpODOguL5QiaReLxiaQsdoOSUlMTZ6QJ0f3MeEjpibibYfWBZyew/132\",\"message\":\"unlock\",\"type\":\"1\",\"memberSex\":\"0\",\"isInfo\":true,\"accessToken\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkZXZpY2VOdW0iOiJCQzAwMDAwMSIsImFwcGxldFVzZXJJZCI6IjExODExODU4NDk2MDgzOTI3MTEiLCJleHAiOjE1NzA3ODk5NzEsImNoZWNrSWQiOiIxMTgxNTA4NTA3NjE3MjIyNjg0In0.aPPtQD0duksQ_mivG2T5ZjHTjYzd70dy55vw_MMURvQ\",\"userId\":\"1181185849608392711\",\"isTgImg\":\"https://panda-1257270219.cos.ap-guangzhou.myqcloud.com/wxuser-1/wxf6b91ba0966277bd.o6zAJs0Y7qMlYcOF7fTkmchaCqK4.z5gIhzaZA1tG754336c35612032f06385abb48fa0a4a.png\"}", User.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        SerialPortCmd.face0();
        sbTimer = Observable.timer(delay, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                startActivity(VideoActivity.class, false);
            }
        }, new MyThrowable());
        isPause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (sbTimer != null)
            sbTimer.unsubscribe();
        isPause = true;
    }
}


