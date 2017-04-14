package com.aadhk.customer.data.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jack on 25/11/2016.
 */

public class API {

    public final Retrofit retrofit;
    public final APIService movieService;
    public MCApi mcApi;

//    private static SparseArray<API> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);  //为了节省空间替代HashMap  https://liuzhichao.com/p/832.html

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    //构造方法私有
    private API() {
//        CookieHandler cookieHandler = new CookieManager(new PersistentCookieStore(context), CookiePolicy.ACCEPT_ALL);
//        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(CustomerApplication.getInstance()));
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(logInterceptor)
                .addInterceptor(new AddCookiesInterceptor(CustomerApplication.getInstance()))
                .addInterceptor(new ReceivedCookiesInterceptor(CustomerApplication.getInstance()))
//                .cookieJar(cookieJar)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
//                .addConverterFactory(ScalarsConverterFactory.create())  //字符串转换器
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
//                .baseUrl(HostType.BASE_URL)
                .build();
        movieService = retrofit.create(APIService.class);
    }

    private static API api;

    public static APIService getDefault() {
        if (api == null) {
            api = new API();
        }
        return api.movieService;
    }

    public static MCApi getMCApi(){
        if(api.mcApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(new OkHttpClient())
                    .baseUrl(Constant.MC_PAYMENT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            api.mcApi = retrofit.create(MCApi.class);
        }
        return api.mcApi;
    }


    //方案1 https://gold.xitu.io/entry/572ed42ddf0eea0063186e1f
    private HashSet<String> cookies = new HashSet<>();

    //取得cookie
    public class ReceivedCookiesInterceptor implements Interceptor {
        private final Context context;

        public ReceivedCookiesInterceptor(Context context) {
            this.context = context;
        } // AddCookiesInterceptor()

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet("PREF_COOKIES", new HashSet<String>());
                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                }
                SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(context).edit();
                memes.putStringSet("PREF_COOKIES", cookies).apply();
                memes.commit();
            }
            return originalResponse;
        }
    }

    //添加coockie
    public class AddCookiesInterceptor implements Interceptor {
        public static final String PREF_COOKIES = "PREF_COOKIES";
        // We're storing our stuff in a database made just for cookies called PREF_COOKIES.
        // I reccomend you do this, and don't change this default value.
        private final Context context;

        public AddCookiesInterceptor(Context context) {
            this.context = context;
        }

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            HashSet<String> preferences = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet(PREF_COOKIES, new HashSet<String>());
            // Use the following if you need everything in one line.
            // Some APIs die if you do it differently.
            /*String cookiestring = "";
            for (String cookie : preferences) {
                String[] parser = cookie.split(";");
                cookiestring = cookiestring + parser[0] + "; ";
            }
            builder.addHeader("Cookie", cookiestring);
            */
            for (String cookie : preferences) builder.addHeader("Cookie", cookie);
            return chain.proceed(builder.build());
        }
    }
}
