package com.healthmall.sail.cat_doctor.bean;

import android.text.TextUtils;

import com.healthmall.sail.cat_doctor.MyApplication;
import com.healthmall.sail.cat_doctor.R;
import com.mai.xmai_fast_lib.utils.MLog;

import java.util.Calendar;
import java.util.List;

/**
 * Created by mai on 2017/11/27.
 */
public class User {

    private String userId;
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

    private List<Physical> isPhysical;

    private String isFcImg;

    private String isTgImg;

    private int type;

    public User(String mallId, String memberName, Integer memberSex, String memHeadImg, String accessToken, String questionAnswerId, String questionResultName, int age) {
        this.userId = mallId;
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
        return userId;
    }

    public void setMallId(String mallId) {
        this.userId = mallId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public List<Physical> getIsPhysical() {
        return isPhysical;
    }

    public void setIsPhysical(List<Physical> isPhysical) {
        this.isPhysical = isPhysical;
    }

    public String getIsFcImg() {
        return isFcImg;
    }

    public void setIsFcImg(String isFcImg) {
        this.isFcImg = isFcImg;
    }

    public String getIsTgImg() {
        return isTgImg;
    }

    public void setIsTgImg(String isTgImg) {
        this.isTgImg = isTgImg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
