package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FaultList {

    @SerializedName("faultID")
    @Expose
    private String faultID;

    @SerializedName("faultName")
    @Expose
    private String faultName;

    @SerializedName("available")
    @Expose
    private String available;

    public String getFaultID() {
        return faultID;
    }

    public void setFaultID(String faultID) {
        this.faultID = faultID;
    }

    public String getFaultName() {
        return faultName;
    }

    public void setFaultName(String faultName) {
        this.faultName = faultName;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
