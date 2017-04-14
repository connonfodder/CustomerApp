package com.aadhk.library.rx;


import android.text.TextUtils;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * des:对服务器返回数据成功和失败处理
 * Created by xsf
 * on 2016.09.9:59
 */
        /*_apiService.login(mobile, verifyCode)
        .compose(RxSchedulersHelper.io_main())
        .compose(RxResultHelper.handleResult())
        .//省略*/

public class RxHelper {
    /**
     * 对服务器返回数据进行预处理
     */
    public static <T> Observable.Transformer<Response<T>, T> handleResult() {
        return new Observable.Transformer<Response<T>, T>() {
            @Override
            public Observable<T> call(Observable<Response<T>> tObservable) {
                return tObservable.flatMap(new Func1<Response<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(Response<T> result) {
                        Log.d("jack", "----------result--------" + result);  //
                        if (result.code.equals("-1")) {         // 登陆
                            RxManager manager = new RxManager();
                            manager.post("rxbus_login", null);
                            manager.clear();
                            return Observable.error(new ServerException("please login first"));
                        } else if (result.code.equals("1")) {    //成功
                            return createData(result.data);
                        } else if (result.code.equals("2")) {   //没有数据
                            return Observable.error(new ServerException("no data"));
                        } else if (result.code.equals("3")) {   //账号被冻结
                            return Observable.error(new ServerException("Account has been frozen"));
                        } else if (result.code.equals("4")) {   //邮箱地址重复
                            return Observable.error(new ServerException("This Email has existed"));  //Throwable
                        } else if (result.code.equals("5")) {   //密码账号错误
                            return Observable.error(new ServerException("Incorrect password or account"));
                        }
                        return Observable.error(new ServerException(TextUtils.isEmpty(result.msg.trim()) ? "server error" : result.msg));
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
