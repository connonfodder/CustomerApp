package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.model.UserLoginModel;
import com.aadhk.customer.ui.fragment.UserLoginFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by jack on 10/01/2017.
 * 使用Mockito模拟测试Presenter层
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserLoginPresenterTest extends BasePresenterTest<UserLoginModel, UserLoginFragment, UserLoginPresenter> {

    private static long userid ;
    @Override
    public void init() {
        model = spy(UserLoginModel.class);
        view = mock(UserLoginFragment.class);
        presenter = spy(UserLoginPresenter.class);
    }

    @Test
    public void vertifyUserRequest() throws Exception {
        User user = new User(TestConstant.UserLoginTest_USER_EMAIL, TestConstant.UserLoginTest_USER_PWD);
        presenter.vertifyUserRequest(user, false);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        verify(model).vertifyUser(user);                   //验证Model层是否发出请求了
//        verify(view).vertifyUserResult(any(User.class));  //验证View层是否成功获取数据
        verify(view).vertifyUserResult(captor.capture());  //验证View层是否成功获取数据

//        verify(userView).onUserLoaded(captor.capture());

        User result = captor.getValue(); // 捕获的User

        userid = result.getId();
        System.out.println("--1-----userid------"+userid);
    }

}