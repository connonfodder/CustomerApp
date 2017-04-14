/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.data.presenter;

import com.aadhk.customer.data.model.RefundListModel;
import com.aadhk.customer.ui.fragment.RefundListFragment;
import com.aadhk.customertest.TestConstant;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class RefundListPresenterTest extends BasePresenterTest<RefundListModel, RefundListFragment, RefundListPresenter> {

    @Override
    public void init() {
        model = spy(RefundListModel.class);
        view = mock(RefundListFragment.class);
        presenter = spy(RefundListPresenter.class);
    }

    @Test
    public void refundRequest() throws Exception {
        presenter.refundRequest(TestConstant.REFUNDLISTTest_REFUND_ORDER_ID, "reason is not delicious");
        verify(model).refund(TestConstant.REFUNDLISTTest_REFUND_ORDER_ID, "reason is not delicious");
        verify(view).refundResult();
    }

}