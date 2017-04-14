package com.aadhk.customer.data.model;


import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.Search;
import com.aadhk.customer.bean.SearchRequest;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.SearchContract;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.PreferenceUtil;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;
import java.util.List;

import rx.Observable;


/**
 * Created by jack on 06/12/2016.
 */

public class SearchModel implements SearchContract.Model {

    @Override
    public Observable<List<Search>> fetchHotAndHistroySearch() {
        CustomerApplication app = CustomerApplication.getInstance();
        HashMap<String, Object> body = new HashMap<>();
        if(app.getUser() != null)
            body.put("userId", app.getUser().getId());
        body.put("maxCount", Constant.MAXCOUNT);
        return API.getDefault().fetchSearchRecord(body)
                .compose(RxSchedulers.<Response<List<Search>>>io_main())
                .compose(RxHelper.<List<Search>>handleResult());
    }

    @Override
    public Observable<List<Company>> search(SearchRequest request) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("keyword", request.getKeyword());
        body.put("maxCount", Constant.SEARCH_MAXCOUNT);
        if(Constant.TEST_MODEL){
            body.put("userId", 40);
            body.put("lng", 103.8506940000);
            body.put("lat", 1.2915100000);
        }else{
            CustomerApplication app = CustomerApplication.getInstance();
            PreferenceUtil prefUtil = new PreferenceUtil(app);
            if(app.getUser() != null)
                body.put("userId", app.getUser().getId());
            body.put("lng", prefUtil.getLastLocationLNG());
            body.put("lat", prefUtil.getLastLocationLAT());
        }
        body.put("type", request.getType());  // 3 4 8
        body.put("arrange", Constant.MAX_DISTANCE);
        return API.getDefault().search(body)
                .compose(RxSchedulers.<Response<List<Company>>>io_main())
                .compose(RxHelper.<List<Company>>handleResult());
    }

    //删除个人记录
    @Override
    public Observable<List<Search>> deleteRecord(long userId) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        return API.getDefault().deleteRecord(body)
                .compose(RxSchedulers.<Response<List<Search>>>io_main())
                .compose(RxHelper.<List<Search>>handleResult());
    }

  /*  private List<Company> getData(int type) {
        List<Company> data = new ArrayList<>();

        data.add(new Company("http://p0.meituan.net/350.214/deal/db03b779cb75e8b3795479a67cb93040379560.jpg@0_61_2000_1212a%7C388h_640w_2e_90Q", "搜索结果1", 36, Arrays.asList("humber", "rice", "aha"), 64, 2, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/d2765123024933f4673d59d1c6733fcf106604.jpg.webp", "搜索结果2", 36, Arrays.asList("chicken", "banana", "cheese"), 84, 5, true));
        data.add(new Company("http://p0.meituan.net/350.214/deal/459a3442830ca3b65d4c23e6a92f0c44344843.jpg.webp", "搜索结果3", 36, Arrays.asList("apple", "mac", "iphone"), 107, 6, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/__24848989__8456253.jpg.webp", "搜索结果4", 36, Arrays.asList("red", "green", "white"), 123, 7, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/39f09f3207616c881d546fdfd4aae818202553.jpg@0_92_1280_775a%7C388h_640w_2e_90Q", "搜索结果5", 36, Arrays.asList("bb", "ds", "des"), 164, 1, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/c190c2576f46b97a7d8e60378799d411101457.jpg.webp", "搜索结果6", 36, Arrays.asList("chi", "dong", "xi"), 264, 3, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/__18266532__1756691.jpg.webp", "搜索结果7", 36, Arrays.asList("humber", "rice", "aha"), 464, 2, true));
        if ((type & Constant.SEARCH_FT_BY_FTNAME) == Constant.SEARCH_FT_BY_FTNAME) {
            data.add(new Company("嘉旺茶", Constant.TYPE_NONE));
            data.add(new Company("嘉旺饭", Constant.TYPE_NONE));
            data.add(new Company("嘉旺果汁", Constant.TYPE_NONE));
            data.add(new Company("嘉旺啥子哟", Constant.TYPE_NONE));
        }
        return data;
    }

    private static List<Search> getSearch(boolean noUser) {
        List<Search> result = new ArrayList<>();
        result.add(new Search(0, "黄茶sasadasdsds"));
        result.add(new Search(0, "嘉旺"));
        result.add(new Search(0, "椰子"));
        result.add(new Search(0, "暖心早餐"));
        result.add(new Search(0, "果汁"));
        result.add(new Search(0, "流氓一族"));
        result.add(new Search(0, "谭大人sdadsssssssssssss"));
        result.add(new Search(0, "烧鸡"));
        result.add(new Search(0, "面子"));
        result.add(new Search(0, "俄合之水"));
        if(!noUser) {
            result.add(new Search(1, "茄子"));
            result.add(new Search(1, "炒饭"));
            result.add(new Search(1, "煲仔饭"));
            result.add(new Search(1, "西瓜"));
            result.add(new Search(1, "玉米饭"));
            result.add(new Search(1, "嘉旺"));
            result.add(new Search(1, "7号餐厅"));
            result.add(new Search(1, "KKONE"));
            result.add(new Search(1, "好吃点"));
        }
        return result;
    }*/
}
