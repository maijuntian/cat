package com.healthmall.sail.cat_doctor.http;


import com.healthmall.sail.cat_doctor.bean.BloodOxygenReport;
import com.healthmall.sail.cat_doctor.bean.BloodPressureReport;
import com.healthmall.sail.cat_doctor.bean.BodyReport;
import com.healthmall.sail.cat_doctor.bean.BodyRespone;
import com.healthmall.sail.cat_doctor.bean.CDRespone;
import com.healthmall.sail.cat_doctor.bean.Question;
import com.healthmall.sail.cat_doctor.bean.QuestionReport;
import com.healthmall.sail.cat_doctor.bean.Symptom;
import com.healthmall.sail.cat_doctor.bean.Version;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mai on 2017/11/20.
 */
public interface CatDoctorService {


    @POST("doctorMall/report/sanCode")
    Observable<CDRespone<Object>> scanCode(@Body RequestBody json);

    @POST("android/data/api/uploadData/body")
    Observable<CDRespone<Object>> bodyReport(@Body RequestBody json);

    @POST("android/data/api/uploadData/temperature")
    Observable<CDRespone<BodyRespone>> temperatureReport(@Body RequestBody json);

    @POST("android/data/api/uploadData/bloodOxygen")
    Observable<CDRespone<Object>> bloodOxygenReport(@Body RequestBody json);

    @POST("android/data/api/uploadData/bloodPressure")
    Observable<CDRespone<Object>> bloodPressureReport(@Body RequestBody json);

    @Multipart
    @POST("doctorMall/report/testUpload")
    Observable<CDRespone<Object>> faceTonUpload(@PartMap Map<String, RequestBody> params);

    @POST("android/data/api/check/checkDeviceIsRegistered")
    Observable<CDRespone> bindDevice(@Body RequestBody json);

    @POST("sail/question/answer")
    Observable<CDRespone<Question>> questionAnswer(@Query("accessToken") String accessToken, @Body RequestBody json);

    @POST("sail/question/pre_subject")
    Observable<CDRespone<Question>> preQuestion(@Query("accessToken") String accessToken, @Body RequestBody json);

    @POST("sail/question/result")
    Observable<CDRespone<QuestionReport>> questionResult(@Query("accessToken") String accessToken, @Body RequestBody json);

    @POST("android/data/api/information/updateInformation")
    Observable<CDRespone<Object>> saveUserInfo( @Body RequestBody json);

    @GET("android/data/api/uploadData/end")
    Observable<CDRespone<Object>> quit();

    @POST("app/getNewVersion")
    Observable<CDRespone<Version>> getNewVersion(@Body RequestBody json);

    @POST("upush/upgrade")
    Observable<CDRespone<Object>> upgrade(@Body RequestBody json);

    @POST("doctorMall/report/getSymptom")
    @FormUrlEncoded
    Observable<CDRespone<List<Symptom>>> getSymptom(@Field("accessToken") String accessToken);


    @POST("android/data/api/answer/physicalResult")
    Observable<CDRespone<Object>> physicalResult(@Body RequestBody json);


    @POST("android/data/api/upload/img")
    Observable<CDRespone<Object>> uploadImgInfo(@Body RequestBody json);
}

