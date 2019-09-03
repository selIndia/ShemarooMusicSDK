package com.shemaroo.shemaroomusicsdk.network;

import com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata.GetBhaktiPartnerDataRequestParser;
import com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata.GetBhaktiPartnerDataResponseParser;
import com.shemaroo.shemaroomusicsdk.model.getslugdata.GetSlugDataRequsetParser;
import com.shemaroo.shemaroomusicsdk.model.getslugdata.GetSlugDataResponseParser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("inner/music/getslugdata")
    Call<GetSlugDataResponseParser> getSlugData(@Body GetSlugDataRequsetParser requestParser);

    @POST("api/getbhaktipartnerdata")
    Call<GetBhaktiPartnerDataResponseParser> getBhaktiPartnerData(@Body GetBhaktiPartnerDataRequestParser requestParser);
}
