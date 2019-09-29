package com.healthmall.sail.cat_doctor.bean;

import android.text.TextUtils;

import com.healthmall.sail.cat_doctor.MyApplication;
import com.healthmall.sail.cat_doctor.R;
import com.mai.xmai_fast_lib.utils.MLog;

import java.util.Calendar;

/**
 * Created by mai on 2017/11/27.
 */
public class User {

    private String mallId;
    private String memberName;
    private Integer memberSex;
    private String memHeadImg;
    private String accessToken;
    private String questionAnswerId;
    private String questionResultName;
    private Integer memberAge;
    private boolean authentication;
    private boolean talent;
    private boolean isUsed;

    private boolean isVoice;

    public User(String mallId, String memberName, Integer memberSex, String memHeadImg, String accessToken, String questionAnswerId, String questionResultName, int age) {
        this.mallId = mallId;
        this.memberName = memberName;
        this.memberSex = memberSex;
        this.memHeadImg = memHeadImg;
        this.accessToken = accessToken;
        this.questionAnswerId = questionAnswerId;
        this.questionResultName = questionResultName;
        this.memberAge = age;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(String questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemHeadImg() {
        return memHeadImg;
    }

    public void setMemHeadImg(String memHeadImg) {
        this.memHeadImg = memHeadImg;
    }

    public Integer getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(Integer memberSex) {
        this.memberSex = memberSex;
    }

    public Integer getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(Integer memberAge) {
        this.memberAge = memberAge;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    public String getQuestionResultName() {
        return questionResultName;
    }

    public void setQuestionResultName(String questionResultName) {
        this.questionResultName = questionResultName;
    }

    public boolean isTalent() {
        return talent;
    }

    public void setTalent(boolean talent) {
        this.talent = talent;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }


    public void setBirthday(String birthday) {

        int year = Integer.parseInt(birthday.split("-")[0]);
        this.memberAge = Calendar.getInstance().get(Calendar.YEAR) - year;
    }

    public boolean isVoice() {
        return isVoice;
    }

    public void setVoice(boolean voice) {
        isVoice = voice;
    }
}
