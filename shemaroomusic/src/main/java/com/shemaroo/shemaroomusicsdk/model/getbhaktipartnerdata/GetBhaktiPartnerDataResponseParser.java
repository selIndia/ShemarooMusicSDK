package com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBhaktiPartnerDataResponseParser {


    @SerializedName("Result")
    @Expose
    private List<GetBhaktiPartnerDataResponseResult> result = null;

    public List<GetBhaktiPartnerDataResponseResult> getResult() {
        return result;
    }

    public void setResult(List<GetBhaktiPartnerDataResponseResult> result) {
        this.result = result;
    }
}
