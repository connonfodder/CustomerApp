package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.TakeOrderingContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 25/11/2016.
 */

public class TakeOrderingModel implements TakeOrderingContract.Model {

    @Override
    public Observable<Address> chooseAddress(long userId, long companyId, double lng, double lat) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        body.put("companyId", companyId);
        body.put("lng", lng);
        body.put("lat", lat);
        return API.getDefault().nearestAddress(body)
                .compose(RxSchedulers.<Response<Address>>io_main())
                .compose(RxHelper.<Address>handleResult());
    }

    @Override
    public Observable<Company> companyDetail(long id) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("companyId", id);
        return API.getDefault().fetchDetail(body)
                .compose(RxSchedulers.<Response<Company>>io_main())
                .compose(RxHelper.<Company>handleResult());
    }

  /*  public static List<Category> getCategoryData() {
        List<Category> categoryList = new ArrayList<>();
        ArrayList<Item> itemList1 = new ArrayList<>();
        itemList1.add(new Item(1, "面包", "henhaochi1", 5.0, 10, "http://a1.att.hudong.com/58/66/19300001355189131763665554986_950.jpg"));
        itemList1.add(new Item(2, "蛋挞", "henhaochi1", 3.0, 10, "http://pic88.huitu.com/res/20161027/1145775_20161027163027096060_1.jpg"));
        itemList1.add(new Item(3, "milk", "henhaochi1", 12.0, 10, "http://img.zudong.com/upfiles/2015/01/08/142731149.jpg"));  //牛奶
        itemList1.add(new Item(4, "绿茶饼", "henhaochi1", 8.0, 10, "http://pic88.huitu.com/res/20161027/1145775_20161027163027096060_1.jpg"));
        itemList1.add(new Item(5, "肠粉", "henhaochi1", 4.0, 10, "http://img.zudong.com/upfiles/2015/01/08/142731149.jpg"));
        itemList1.add(new Item(6, "花卷", "henhaochi1", 2.0, 10, "http://img.zudong.com/upfiles/2015/01/08/142731149.jpg"));
        itemList1.add(new Item(7, "包子", "henhaochi1", 4.0, 10, "http://pic88.huitu.com/res/20161027/1145775_20161027163027096060_1.jpg"));
        Category breakfast = new Category("Morning", itemList1);

        ArrayList<Item> itemList2 = new ArrayList<>();
        itemList2.add(new Item(8, "粥", "henhaochi1", 12.0, 10, "http://img.zudong.com/upfiles/2015/01/08/142731149.jpg"));
        itemList2.add(new Item(9, "炒饭", "henhaochi1", 11.0, 10, "http://a1.att.hudong.com/58/66/19300001355189131763665554986_950.jpg"));
        itemList2.add(new Item(10, "炒米粉", "henhaochi1", 13.0, 10, "http://www.xwly88.com/Pictures/UeditorImg/TrustSoft/%E8%BF%9C%E7%A8%8B%E4%B8%93%E5%8C%BA/AllRemote/2016-10-26/76fb6b80-8da0-4084-8a7c-649f66a7a823.jpeg"));
        itemList2.add(new Item(11, "amoll", "henhaochi1", 21.0, 10, "http://a1.att.hudong.com/58/66/19300001355189131763665554986_950.jpg")); //炒粿条
        itemList2.add(new Item(12, "炒牛河", "henhaochi1", 11.0, 10, "http://www.xwly88.com/Pictures/UeditorImg/TrustSoft/%E8%BF%9C%E7%A8%8B%E4%B8%93%E5%8C%BA/AllRemote/2016-10-26/76fb6b80-8da0-4084-8a7c-649f66a7a823.jpeg"));
        itemList2.add(new Item(13, "炒菜", "henhaochi1", 13.0, 10, "http://pic88.huitu.com/res/20161027/1145775_20161027163027096060_1.jpg"));
        Category launch = new Category("Lunch", itemList2);

        ArrayList<Item> itemList3 = new ArrayList<>();
        itemList3.add(new Item(14, "淋菜", "henhaochi1", 56.0, 10, "http://pic88.huitu.com/res/20161027/1145775_20161027163027096060_1.jpg"));
        itemList3.add(new Item(15, "川菜", "henhaochi1", 34.0, 10, "http://a1.att.hudong.com/58/66/19300001355189131763665554986_950.jpg"));
        itemList3.add(new Item(16, "湘菜", "henhaochi1", 23.0, 10, "http://www.xwly88.com/Pictures/UeditorImg/TrustSoft/%E8%BF%9C%E7%A8%8B%E4%B8%93%E5%8C%BA/AllRemote/2016-10-26/76fb6b80-8da0-4084-8a7c-649f66a7a823.jpeg"));
        itemList3.add(new Item(17, "粤菜", "henhaochi1", 43.0, 10, "http://pic88.huitu.com/res/20161027/1145775_20161027163027096060_1.jpg"));
        itemList3.add(new Item(18, "赣菜", "henhaochi1", 43.0, 10, "http://a1.att.hudong.com/58/66/19300001355189131763665554986_950.jpg"));
        itemList3.add(new Item(19, "东北菜", "henhaochi1", 64, 10, "http://img.zudong.com/upfiles/2015/01/08/142731149.jpg"));
        Category evening = new Category("Supper", itemList3);

        ArrayList<Item> itemList4 = new ArrayList<>();
        itemList4.add(new Item(20, "淋菜", "henhaochi1", 12.0, 10, "http://a1.att.hudong.com/58/66/19300001355189131763665554986_950.jpg"));
        itemList4.add(new Item(21, "川菜", "henhaochi1", 12.0, 10, "http://img.zudong.com/upfiles/2015/01/08/142731149.jpg"));
        itemList4.add(new Item(22, "湘菜", "henhaochi1", 32.0, 10, "http://www.xwly88.com/Pictures/UeditorImg/TrustSoft/%E8%BF%9C%E7%A8%8B%E4%B8%93%E5%8C%BA/AllRemote/2016-10-26/76fb6b80-8da0-4084-8a7c-649f66a7a823.jpeg"));
        itemList4.add(new Item(23, "粤菜", "henhaochi1", 33.0, 10, "http://a1.att.hudong.com/58/66/19300001355189131763665554986_950.jpg"));
        itemList4.add(new Item(24, "赣菜", "henhaochi1", 35.0, 10, "http://www.xwly88.com/Pictures/UeditorImg/TrustSoft/%E8%BF%9C%E7%A8%8B%E4%B8%93%E5%8C%BA/AllRemote/2016-10-26/76fb6b80-8da0-4084-8a7c-649f66a7a823.jpeg"));
        itemList4.add(new Item(25, "东北菜", "henhaochi1", 123.0, 10, "http://a1.att.hudong.com/58/66/19300001355189131763665554986_950.jpg"));
        Category menu1 = new Category("Dinner", itemList3);

        categoryList.add(breakfast);
        categoryList.add(launch);
        categoryList.add(evening);
        categoryList.add(menu1);

        return categoryList;
    }

    private List<Company> getData() {
        List<Company> data = new ArrayList<>();
        data.add(new Company("http://p0.meituan.net/350.214/deal/db03b779cb75e8b3795479a67cb93040379560.jpg@0_61_2000_1212a%7C388h_640w_2e_90Q", "千滋百味自助烤肉火锅", 36, Arrays.asList("humber", "rice", "aha"), 64, 2, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/d2765123024933f4673d59d1c6733fcf106604.jpg.webp", "锦园四季椰子鸡", 36, Arrays.asList("chicken", "banana", "cheese"), 84, 5, true));
        data.add(new Company("http://p0.meituan.net/350.214/deal/459a3442830ca3b65d4c23e6a92f0c44344843.jpg.webp", "汉釜宫韩式自助烤肉火锅（宝能店）", 36, Arrays.asList("apple", "mac", "iphone"), 107, 6, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/__24848989__8456253.jpg.webp", "板一回转寿司（龙华店）", 36, Arrays.asList("red", "green", "white"), 123, 7, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/39f09f3207616c881d546fdfd4aae818202553.jpg@0_92_1280_775a%7C388h_640w_2e_90Q", "玛喜达韩国年糕料理（万科店）", 36, Arrays.asList("bb", "ds", "des"), 164, 1, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/c190c2576f46b97a7d8e60378799d411101457.jpg.webp", "佳客来牛排专家（南岭店）", 36, Arrays.asList("chi", "dong", "xi"), 264, 3, true));
        data.add(new Company("http://p1.meituan.net/350.214/deal/__18266532__1756691.jpg.webp", "圣多斯巴西烤肉（沙井店）", 36, Arrays.asList("humber", "rice", "aha"), 464, 2, true));
        return data;
    }*/

}


