package com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBhaktiPartnerDataResponseData {

    @SerializedName("ContentTitle")
    @Expose
    private String contentTitle;
    @SerializedName("ContentUrl")
    @Expose
    private String contentUrl;
    @SerializedName("ContentImageName")
    @Expose
    private String contentImageName;
    @SerializedName("ContentSinger")
    @Expose
    private String contentSinger;
    @SerializedName("BhaktiPartnerID")
    @Expose
    private Integer bhaktiPartnerID;
    @SerializedName("PartnerCode")
    @Expose
    private String partnerCode;
    @SerializedName("AnalyticsCode")
    @Expose
    private String analyticsCode;
    @SerializedName("AdsCode")
    @Expose
    private String adsCode;
    @SerializedName("PartnerName")
    @Expose
    private String partnerName;
    @SerializedName("AdSlot")
    @Expose
    private String adSlot;
    @SerializedName("BhaktiPartnerEnc")
    @Expose
    private Object bhaktiPartnerEnc;

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContentImageName() {
        return contentImageName;
    }

    public void setContentImageName(String contentImageName) {
        this.contentImageName = contentImageName;
    }

    public String getContentSinger() {
        return contentSinger;
    }

    public void setContentSinger(String contentSinger) {
        this.contentSinger = contentSinger;
    }

    public Integer getBhaktiPartnerID() {
        return bhaktiPartnerID;
    }

    public void setBhaktiPartnerID(Integer bhaktiPartnerID) {
        this.bhaktiPartnerID = bhaktiPartnerID;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getAnalyticsCode() {
        return analyticsCode;
    }

    public void setAnalyticsCode(String analyticsCode) {
        this.analyticsCode = analyticsCode;
    }

    public String getAdsCode() {
        return adsCode;
    }

    public void setAdsCode(String adsCode) {
        this.adsCode = adsCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getAdSlot() {
        return adSlot;
    }

    public void setAdSlot(String adSlot) {
        this.adSlot = adSlot;
    }

    public Object getBhaktiPartnerEnc() {
        return bhaktiPartnerEnc;
    }

    public void setBhaktiPartnerEnc(Object bhaktiPartnerEnc) {
        this.bhaktiPartnerEnc = bhaktiPartnerEnc;
    }
}
