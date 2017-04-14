package com.aadhk.customer.data.api;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.FoodStyles;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.Search;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.library.rx.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by jack on 25/11/2016.
 * http://square.github.io/retrofit/
 */

public interface APIService {

    /**
     * 1.1. 根据定位获取附近餐厅列表
     */
    @POST("restaurant/query")
    Observable<Response<List<Company>>> query(@Body HashMap<String, Object> body);

    /**
     * 1.2. 拿到Admin设置全部的菜市风格
     */
    @POST("restaurant/queryFoodstyles")
    Observable<Response<List<FoodStyles>>> queryFoodstyles(@Body HashMap<String, Object> body);


    /**
     * 1.2. 根据用户搜索个人搜索记录&热点搜索
     */
    @POST("restaurant/fetchSearchRecord")
    Observable<Response<List<Search>>> fetchSearchRecord(@Body HashMap<String, Object> body);

    /**
     * 1.3. 根据关键词综合搜索餐厅和搜索菜式风格可能词
     */
    @POST("restaurant/search")
    Observable<Response<List<Company>>> search(@Body HashMap<String, Object> body);

    /**
     * 1.4. 根据餐厅ID获取餐厅全部数据
     */
    @POST("restaurant/fetchDetail")
    Observable<Response<Company>> fetchDetail(@Body HashMap<String, Object> body);

    /**
     * 1.5 删除个人记录
     */
    @POST("restaurant/deleteRecord")
    Observable<Response<List<Search>>> deleteRecord(@Body HashMap<String, Object> body);

    /**
     * 2.1. 下单
     */
    @POST("order/init")
    Observable<Response<Order>> initOrder(@Body HashMap<String, Object> body);

    /**
     * 2.2. 支付
     */
    @POST("order/pay")
    Observable<Response<Object>> payOrder(@Body HashMap<String, Object> body);

    /**
     * 2.3. 订单查询
     */
    @POST("order/query")
    Observable<Response<List<Order>>> queryOrder(@Body HashMap<String, Object> body);

    /**
     * 2.4.订单删除
     */
    @POST("order/delete")
    Observable<Response<Object>> deleteOrder(@Body HashMap<String, Object> body);

    /**
     * 2.4.订单取消
     */
    @POST("order/cancel")
    Observable<Response<Object>> cancelOrder(@Body HashMap<String, Object> body);

    /**
     * 2.5. 退款
     */
    @POST("order/refund")
    Observable<Response<Object>> refundOrder(@Body HashMap<String, Object> body);

    /**
     * 3.1. 登陆
     */
    @POST("user/login")
    Observable<Response<User>> login(@Body User body);

    /**
     * 3.2. 注册
     */
    @POST("user/signup")
    Observable<Response<User>> signup(@Body HashMap<String, Object> body);

    /**
     * 3.2. 重置密码
     */
    @POST("user/reset")
    Observable<Response<Object>> reset(@Body HashMap<String, Object> body);


    /**
     * 3.3. 修改密码
     */
    @POST("user/upadtepwd")
    Observable<Response<Object>> upadtepwd(@Body HashMap<String, Object> body);

    /**
     * 3.4. 修改用户详细信息
     */
    @POST("user/updateinfo")
    Observable<Response<Object>> updateinfo(@Body HashMap<String, Object> body);

    /**
     * 3.5. 获取系统设置
     */
    @POST("user/getSetting")
    Observable<Response<AppSettings>> getSetting();

    /**
     * 4.1. 添加地址
     */
    @POST("location/add")
    Observable<Response<Object>> addLocation(@Body HashMap<String, Object> body);

    /**
     * 4.2. 删除地址
     */
    @POST("location/delete")
    Observable<Response<Object>> deleteLocation(@Body HashMap<String, Object> body);

    /**
     * 4.3. 修改地址
     */
    @POST("location/update")
    Observable<Response<Object>> updateLocation(@Body HashMap<String, Object> body);

    /**
     * 4.4. 查询地址
     */
    @POST("location/query")
    Observable<Response<List<Address>>> queryLocation(@Body HashMap<String, Object> body);


    /**
     * 5.1. 提现查询
     */
    @POST("withdraw/query")
    Observable<Response<List<UserWithdrawals>>> queryWithDraw(@Body HashMap<String, Object> body);

    /**
     * 5.2. 提现添加
     */
    @POST("withdraw/add")
    Observable<Response<Object>> addWithDraw(@Body HashMap<String, Object> body);

    /**
     * 5.3. 获取最近地址
     */
    @POST("location/getNearestAddress")
    Observable<Response<Address>> nearestAddress(@Body HashMap<String, Object> body);

    @GET("nc/article/{postId}/full.html")
    Observable<Map<String, User>> getNewDetail(
            @Header("Cache-Control") String cacheControl,
            @Path("postId") String postId);


    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<User>>> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);

    @GET
    Observable<ResponseBody> getNewsBodyHtmlPhoto(
            @Header("Cache-Control") String cacheControl,
            @Url String photoPath);
    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
    // baseUrl 需要符合标准，为空、""、或不合法将会报错

    @GET("data/福利/{size}/{page}")
    Observable<User> getPhotoList(
            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);

    @GET("nc/video/list/{type}/n/{startPage}-10.html")
    Observable<Map<String, List<User>>> getVideoList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type,
            @Path("startPage") int startPage);
}
