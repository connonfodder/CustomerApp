package com.aadhk.customertest;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.api.API;
import com.aadhk.customer.data.api.APIService;
import com.aadhk.library.rx.Response;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


public class WebTest {

   /* @Test
    public void testNearestAddress() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", 40);
        body.put("companyId", 1);
        body.put("lng", 114.00001652339999);
        body.put("lat", 22.5457818091);
        api.nearestAddress(body)
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<Address>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<Address> addressResponse) {
                        System.out.println("--onNext---"+addressResponse.toString());
                    }
                });
    }

    //16 orderlist/delete
    @Test
    public void testDeleteOrder() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("orderId", 2);
Observable<Response<List<Order>>> result = api.deleteOrder(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<Object>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<Object> user) {
                        System.out.println("--onNext---" + user.toString());
                    }
                });

    }


    //15 orderlist/query
    @Test
    public void testQueryOrder() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", 1);
        body.put("page", 1);
        Observable<Response<List<Order>>> result = api.queryOrder(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<List<Order>>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<List<Order>> user) {
                        System.out.println("--onNext---" + user.toString());
                    }
                });
    }

    //4. restaurant/fetchDetail
    @Test
    public void testFetchDetail() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("companyId", 1);
        Observable<Response<Company>> result = api.fetchDetail(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<Company>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<Company> user) {
                        List<Category> item = user.data.getCategoryList();
                        for (Category c : item) {
                            System.out.println("--c---" + c.toString());
                            for (Item i : c.getItemList()) {
                                System.out.println("--c---" + i.toString());
                            }
                        }
                    }
                });
    }

    //3 restaurant/search
    @Test
    public void testSearch() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("keyword", "good");
        body.put("maxCount", 20);
        body.put("userId", 12);
        body.put("type", 8);  // 3 4 8
        body.put("lng", 122.2131231);
        body.put("lat", 24.132132);
        Observable<Response<List<Company>>> result = api.search(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<List<Company>>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<List<Company>> user) {
                        System.out.println("--onNext---" + user.toString());
                        for (Company item : user.data) {
                            System.out.println("onNext" + item);
                        }
                    }
                });
    }

    //2 restaurant/fetchSearchRecord
    @Test
    public void testFetchSearchRecord() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", 12);
        body.put("maxCount", 20);
        Observable<Response<List<Search>>> result = api.fetchSearchRecord(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<List<Search>>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<List<Search>> user) {
                        System.out.println("--onNext---" + user.toString());
                        for (Search item : user.data) {
                            System.out.println("onNext" + item);
                        }
                    }
                });
    }


    //1 restaurant/query
    @Test
    public void testQueryRestaurant() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("lng", 122.12123);
        body.put("lat", 24.12312321);
        body.put("page", 1);
        body.put("restaurantType", 7);
        body.put("foodType", Arrays.asList("132"));   //List<String>
        Observable<Response<List<Company>>> result = api.query(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<List<Company>>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<List<Company>> user) {
                        System.out.println("--onNext---" + user.toString());
                        for (Company item : user.data) {
                            System.out.println("onNext" + item);
                        }
                    }
                });
    }

    //11 location/query
    @Test
    public void testQueryLocati1on() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", 12);
        Observable<Response<List<Address>>> result = api.queryLocation(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<List<Address>>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<List<Address>> user) {
                        System.out.println("--onNext---" + user.toString());
                        for (Address item : user.data) {
                            System.out.println("onNext" + item);
                        }
                    }
                });
    }

    //10 location/delete
    @Test
    public void testDeleteLocation() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("addressId", 2);
        Observable<Response<Object>> result = api.deleteLocation(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<Object>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<Object> user) {
                        System.out.println("onNext" + user);
                    }
                });
    }

    //9 location/add
    @Test
    public void testAddLocation() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        Address address = new Address(12, "demaxiya", "1231231", "beijinig", "tiananmen", "123123", "dsfsfs", 0);
        body.put("address", address);
        Observable<Response<Object>> result = api.addLocation(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<Object>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<Object> user) {
                        System.out.println("onNext" + user);
                    }
                });
    }

    //8  user/updateinfo
    @Test
    public void testUpdateinfo() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        User user = new User();
        user.setUserName("jackchen");
        user.setId(12);
        body.put("user", user);
        Observable<Response<Object>> result = api.updateinfo(body);
        result.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<Object>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<Object> user) {
                        System.out.println("onNext" + user);
                    }
                });
    }

    //7 user/upadtepwd
    @Test
    public void testUpadtepwd() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("userId", 12);
        body.put("password", "111");
        Observable<Response<Object>> user = api.upadtepwd(body);
        user.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<Object>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<Object> user) {
                        System.out.println("onNext" + user);
                    }
                });
    }

    //6 user/signup   ok
    @Test
    public void testSignUp() throws Exception {
        APIService api = API.getDefault();
        HashMap<String, Object> body = new HashMap<>();
        body.put("user", new User("jack", "123", "152114412111", "shenzhen", "shentang", "603", "jackchen@qq.com"));
        Observable<Response<User>> user = api.signup(body);
        user.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<User>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<User> user) {
                        System.out.println("onNext" + user);
                    }
                });
    }
*/
    //5 user/login   ok
    @Test
    public void testLogin() throws Exception {
        APIService api = API.getDefault();
        User body = new User("a@a.com", "6846860684f05029abccc09a53cd66f1");
        Observable<Response<User>> user = api.login(body);
        user.observeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<User>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("--onError---" + e.toString());
                    }

                    @Override
                    public void onNext(Response<User> user) {
                        System.out.println("onNext" + user);
                    }
                });
    }
}
