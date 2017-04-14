/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.data.model.LocationEditModel;
import com.aadhk.customer.ui.fragment.LocationEditFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocationEditPresenterTest extends BasePresenterTest<LocationEditModel, LocationEditFragment, LocationEditPresenter> {

    @Override
    public void init() {
        model = spy(LocationEditModel.class);
        view = mock(LocationEditFragment.class);
        presenter = spy(LocationEditPresenter.class);
    }

    @Test
    public void A_saveAddressRequest() throws Exception {
        System.out.println("----------1-----------");
        Address address = new Address(TestConstant.UserPwdTest_USER_ID, "jack  d", "123123213", "address21312", "addressline 2131", "z12312", "jack@gg.cad", 0);
        address.setLongitude(TestConstant.UserPwdTest_LONGTITUDE);
        address.setLatitude(TestConstant.UserPwdTest_LATITUDE);
        presenter.saveAddressRequest(address);
        verify(model).saveAddress(address);
        verify(view).saveAddressResult();
    }

    @Test
    public void deleteAddressRequest() throws Exception {
        System.out.println("---------2-----------");
        presenter.deleteAddressRequest(TestConstant.LocationEditTest_LOCATION_ID);
        verify(model).deleteAddress(TestConstant.LocationEditTest_LOCATION_ID);
        verify(view).deleteAddressResult();
    }
}