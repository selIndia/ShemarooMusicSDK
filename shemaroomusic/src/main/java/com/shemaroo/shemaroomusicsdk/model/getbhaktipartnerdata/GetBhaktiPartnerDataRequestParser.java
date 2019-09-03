package com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBhaktiPartnerDataRequestParser {
    @SerializedName("BhaktiPartnerEnc")
    @Expose
    private String bhaktiPartnerEnc;

    public String getBhaktiPartnerEnc() {
        return bhaktiPartnerEnc;
    }

    public void setBhaktiPartnerEnc(String bhaktiPartnerEnc) {
        this.bhaktiPartnerEnc = bhaktiPartnerEnc;
    }

}
