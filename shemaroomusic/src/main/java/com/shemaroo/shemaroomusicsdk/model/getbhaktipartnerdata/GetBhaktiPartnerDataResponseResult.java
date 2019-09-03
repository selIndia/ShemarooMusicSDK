package com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBhaktiPartnerDataResponseResult {

    @SerializedName("Data")
    @Expose
    private GetBhaktiPartnerDataResponseData data;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Name")
    @Expose
    private Object name;
    @SerializedName("RedirectTo")
    @Expose
    private Object redirectTo;
    @SerializedName("ErrorInfo")
    @Expose
    private Object errorInfo;
    @SerializedName("Message")
    @Expose
    private Object message;
    @SerializedName("VendorCode")
    @Expose
    private Object vendorCode;
    @SerializedName("VendorTypeID")
    @Expose
    private Integer vendorTypeID;
    @SerializedName("BarcodeVendorID")
    @Expose
    private Integer barcodeVendorID;

    public GetBhaktiPartnerDataResponseData getData() {
        return data;
    }

    public void setData(GetBhaktiPartnerDataResponseData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getRedirectTo() {
        return redirectTo;
    }

    public void setRedirectTo(Object redirectTo) {
        this.redirectTo = redirectTo;
    }

    public Object getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(Object errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(Object vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Integer getVendorTypeID() {
        return vendorTypeID;
    }

    public void setVendorTypeID(Integer vendorTypeID) {
        this.vendorTypeID = vendorTypeID;
    }

    public Integer getBarcodeVendorID() {
        return barcodeVendorID;
    }

    public void setBarcodeVendorID(Integer barcodeVendorID) {
        this.barcodeVendorID = barcodeVendorID;
    }
}
