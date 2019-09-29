package com.healthmall.sail.cat_doctor.http;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.healthmall.sail.cat_doctor.MyApplication;
import com.healthmall.sail.cat_doctor.utils.AesEncryptUtil;
import com.mai.xmai_fast_lib.R;
import com.mai.xmai_fast_lib.basehttp.HttpResponse;
import com.mai.xmai_fast_lib.basehttp.MParams;
import com.mai.xmai_fast_lib.exception.NetWorkException;
import com.mai.xmai_fast_lib.exception.ServerException;
import com.mai.xmai_fast_lib.utils.MLog;
import com.mai.xmai_fast_lib.utils.NetUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @param <T> okHttp接口
 * @author mai
 */
public abstract class BaseRetrofitService2<T> {

    private static final int TIME_OUT = 15000;
    protected T mService;

    protected abstract String getBaseUrl();

    protected void notNetWork(Context context) {
        Toast.makeText(context, R.string.network_not_available, Toast.LENGTH_SHORT).show();
    }

    protected void serverError(Context context, int code, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    protected abstract void showDialog(Context context);

    protected abstract void dismissDialog();

    /**
     * 检测网络可用
     *
     * @param observable
     * @param context
     */
    protected <M> Observable<M> checkNetWork(Observable<M> observable, final Context context) {
        return observable.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (!NetUtils.isNetworkAvailable(context)) {
                    throw new NetWorkException("The network is not available");
                }
            }
        });
    }

    protected void timeout(Context context) {
        Toast.makeText(context, R.string.time_out, Toast.LENGTH_SHORT).show();
    }

    /**
     * loading框
     *
     * @param observable
     * @param context
     */
    protected <M> Observable<M> showDialog(Observable<M> observable, final Context context) {
        return observable.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showDialog(context);
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                dismissDialog();
            }
        });
    }

    /**
     * 检查返回值
     *
     * @param observable
     * @param <M>
     */
    protected <M> Observable<M> checkResult(Observable<HttpResponse<M>> observable) {
        return observable.map(new Func1<HttpResponse<M>, M>() {
            @Override
            public M call(HttpResponse<M> response) {
                MLog.log("访问结果", response.toString());
                if (response.getCode() != 200) {
                    ServerException serverException = new ServerException(response.getMsg());
                    serverException.setCode(response.getCode());
                    throw serverException;
                }
                return response.getResult();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 检查出错
     *
     * @param observable
     * @param context
     * @param <M>
     */
    protected <M> Observable<M> checkError(Observable<M> observable, final Context context) {
        return observable.doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissDialog();
                if (throwable instanceof NetWorkException) {
                    notNetWork(context);
                } else if (throwable instanceof ServerException) {
                    ServerException serverException = (ServerException) throwable;
                    serverError(context, serverException.getCode(), serverException.getMessage());
                } else if (throwable instanceof SocketTimeoutException) {
                    timeout(context);
                } else {
                    throwable.printStackTrace();
                }
            }
        });
    }

    protected <M> Observable<M> checkAll(Observable<HttpResponse<M>> observable, Context context) {
        return checkError(showDialog(checkNetWork(checkResult(observable), context), context), context);
    }

    /**
     * 超时时间 （单位ms）
     */
    protected int getTimeOut() {
        return TIME_OUT;
    }


    /**
     * http请求日志
     *
     * @param message
     */
    protected void logHttpMessage(String message) {
        MLog.log(message);
    }

    public BaseRetrofitService2() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                logHttpMessage(message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        ParameterizedType pt = (ParameterizedType) this.getClass()
                .getGenericSuperclass();

        Retrofit.Builder builder = new Retrofit.Builder();


        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(getTimeOut(), TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        // 获取 Request
                        Request request = chain.request();
                        // 获取 Request 的创建者 Builder
                        Request.Builder builder2 = request.newBuilder();

                        if (MyApplication.get().getCurrUser() != null) {
                            builder2.addHeader("Authorization", MyApplication.get().getCurrUser().getAccessToken());
                        }


                        RequestBody requestBody = request.body();
                        if (requestBody != null) {
                            Buffer buffer = new Buffer();
                            request.body().writeTo(buffer);

                            Charset charset = Charset.forName("UTF-8");
                            String bodyStr = buffer.readString(charset);
                            if (!TextUtils.isEmpty(bodyStr)) {
                                try {
                                    MLog.log( "bodyStr=" + bodyStr);
                                    String encodeData = AesEncryptUtil.encrypt(bodyStr, "xlmParameter6HaF", "FY9BQGaFk0QBv6aF");
                                    MLog.log( "encodeData=" + encodeData);

                                    builder2.method(request.method(),new MParams().add("parameter", encodeData).getJsonRequestBody());

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        // 从 request 中获取原有的 HttpUrl 实例
                        HttpUrl oldHttpUrl = request.url();

                        HttpUrl newBaseUrl = HttpUrl.parse(getBaseUrl());

                        // 重建新的 HttpUrl，修改需要修改的 url 部分
                        HttpUrl newFullUrl = oldHttpUrl
                                .newBuilder()
                                .scheme(newBaseUrl.scheme())
                                .host(newBaseUrl.host())
                                .port(newBaseUrl.port())
                                .build();
                        // 重建这个 request，通过 builder.url(newFullUrl).build()
                        // 然后返回一个 response 至此结束修改
                        return chain.proceed(builder2.url(newFullUrl).build());
                    }
                })
                .addInterceptor(logging)
                .build();
        mService = builder.baseUrl(getBaseUrl()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(client).addConverterFactory(GsonConverterFactory.create()).build().create((Class<T>) pt.getActualTypeArguments()[0]);
    }

}
