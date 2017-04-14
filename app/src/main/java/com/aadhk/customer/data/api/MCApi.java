package com.aadhk.customer.data.api;


import com.aadhk.customer.bean.mcpayment.CheckOutInitRequest;
import com.aadhk.customer.bean.mcpayment.CheckOutInitResponse;
import com.aadhk.customer.bean.mcpayment.ProcessPaymentRequest;
import com.aadhk.customer.bean.mcpayment.ProcessPaymentResponse;
import com.aadhk.customer.bean.mcpayment.RetrieveCheckOutDataTokenRequest;
import com.aadhk.customer.bean.mcpayment.RetrieveCheckOutDataTokenResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jack on 19/12/2016.
 */

public interface MCApi {

    @POST("CheckOutInit")
    Observable<CheckOutInitResponse> checkOutInit(@Body CheckOutInitRequest request);

    @POST("RetrieveCheckOutDataToken")
    Observable<RetrieveCheckOutDataTokenResponse> retrieveCheckOutDataToken(@Body RetrieveCheckOutDataTokenRequest request);

    @POST("ProcessPayment")
    Observable<ProcessPaymentResponse> processPayment(@Body ProcessPaymentRequest request);

}
