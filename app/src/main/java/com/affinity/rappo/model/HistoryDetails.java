package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryDetails {

    @SerializedName("serviceID")
    @Expose
    private String serviceID;

    @SerializedName("userID")
    @Expose
    private String userID;

    @SerializedName("customerID")
    @Expose
    private String customerID;

    @SerializedName("customerName")
    @Expose
    private String customerName;

    @SerializedName("MachineID")
    @Expose
    private String MachineID;

    @SerializedName("serviceTypeID")
    @Expose
    private String serviceTypeID;

    @SerializedName("purpose")
    @Expose
    private String purpose;

    @SerializedName("fault")
    @Expose
    private String fault;

    @SerializedName("serviceStatus")
    @Expose
    private String serviceStatus;

    @SerializedName("workStatus")
    @Expose
    private String workStatus;

    @SerializedName("spareUsed")
    @Expose
    private String spareUsed;

    @SerializedName("spareAmount")
    @Expose
    private String spareAmount;

    @SerializedName("spareStatus")
    @Expose
    private String spareStatus;

    @SerializedName("serviceCharge")
    @Expose
    private String serviceCharge;

    @SerializedName("timeSpent")
    @Expose
    private String timeSpent;

    @SerializedName("serviceRNO")
    @Expose
    private String serviceRNO;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("customeFeed")
    @Expose
    private String customeFeed;

    @SerializedName("currentDate")
    @Expose
    private String currentDate;

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMachineID() {
        return MachineID;
    }

    public void setMachineID(String machineID) {
        MachineID = machineID;
    }

    public String getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(String serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getSpareUsed() {
        return spareUsed;
    }

    public void setSpareUsed(String spareUsed) {
        this.spareUsed = spareUsed;
    }

    public String getSpareAmount() {
        return spareAmount;
    }

    public void setSpareAmount(String spareAmount) {
        this.spareAmount = spareAmount;
    }

    public String getSpareStatus() {
        return spareStatus;
    }

    public void setSpareStatus(String spareStatus) {
        this.spareStatus = spareStatus;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getServiceRNO() {
        return serviceRNO;
    }

    public void setServiceRNO(String serviceRNO) {
        this.serviceRNO = serviceRNO;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCustomeFeed() {
        return customeFeed;
    }

    public void setCustomeFeed(String customeFeed) {
        this.customeFeed = customeFeed;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}
