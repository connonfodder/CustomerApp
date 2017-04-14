package com.aadhk.customer.data.model;


import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.LocationListContract;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Constant;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by jack on 08/12/2016.
 */

public class LocationListModel implements LocationListContract.Model {

    @Override
    public Observable<List<Address>> fetchAllLocation() {
        HashMap<String, Object> body = new HashMap<>();
        if(Constant.TEST_MODEL){
            body.put("userId", 40);
        }else{
            User user = CustomerApplication.getInstance().getUser();
            if(user!=null)
                body.put("userId", user.getId());
        }
        return API.getDefault().queryLocation(body)
                .compose(RxSchedulers.<Response<List<Address>>>io_main())
                .compose(RxHelper.<List<Address>>handleResult());
    }

/*    private List<Address> getData(){
        List<Address> data = new ArrayList<>();
        data.add(new Address("DS", "12312321321", "shenzhen1", "tiandi222yuan", "581001", "xxx32@qq.com", 3));
        data.add(new Address("DFS", "12312321321", "shenzhen2", "ti2SAandiyuan", "581001", "x2xx@qq.com", 2));
        data.add(new Address("GDF", "12312321321", "shenzhen", "tiandiSADASyuan", "581001", "xxx3@qq.com", 0));
        data.add(new Address("GFD", "12312321321", "shenzhenW", "tiaASDndiSAyuan", "581001", "xx4x@qq.com", 1));
        data.add(new Address("DSFDS", "12312321321", "shenzhenD", "tiandiASDyuan", "581001", "213xxx@qq.com", 3));
        data.add(new Address("GDF", "12312321321", "shenzhen", "tiandSAiyuan", "581001", "122@qq.com", 1));
        return data;
    }*/


}
