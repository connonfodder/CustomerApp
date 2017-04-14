package com.aadhk.customer.data.model;

import com.aadhk.customer.bean.UserWithdrawals;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.contract.WithDrawListContract;
import com.aadhk.library.rx.Response;
import com.aadhk.library.rx.RxHelper;
import com.aadhk.library.rx.RxSchedulers;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by jack on 15/12/2016.
 */

public class WithDrawListModel implements WithDrawListContract.Model {

    @Override
    public Observable<List<UserWithdrawals>> query(long userId) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        return API.getDefault().queryWithDraw(body)
                .compose(RxSchedulers.<Response<List<UserWithdrawals>>>io_main())
                .compose(RxHelper.<List<UserWithdrawals>>handleResult());
    }

   /* private List<UserWithdrawals> getData() {
        List<UserWithdrawals> result = new ArrayList<>();
        result.add(new UserWithdrawals(123, "please give masterpass card 12312321312", "2016-12-12 12:23", 0));
        result.add(new UserWithdrawals(23, "pl 12321312", "2016-12-12 12:23", 1));
        result.add(new UserWithdrawals(54, "please give masterpass card 12312321312", "2016-12-12 12:23", 0));
        result.add(new UserWithdrawals(83, "please gi 321312", "2016-12-12 12:23", 2));
        result.add(new UserWithdrawals(83, "please give m 12321312", "2016-12-12 12:23", 1));
        return result;
    }*/

}
