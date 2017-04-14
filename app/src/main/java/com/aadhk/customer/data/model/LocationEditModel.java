package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.LocationEditContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by jack on 08/12/2016.
 */

public class LocationEditModel implements LocationEditContract.Model {

    @Override
    public Observable<Object> saveAddress(Address address) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("address", address);
        if(address.getId()>0){   //表示更新
            return API.getDefault().updateLocation(body)
                    .compose(RxSchedulers.<Response<Object>>io_main())
                    .compose(RxHelper.handleResult());
        }
        return  API.getDefault().addLocation(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
                .compose(RxHelper.handleResult());
    }

    @Override
    public Observable<Object> deleteAddress(long addressId) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("addressId", addressId);
        return API.getDefault().deleteLocation(body)
                .compose(RxSchedulers.<Response<Object>>io_main())
                .compose(RxHelper.handleResult());
    }
}
