package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurposeList {

    @SerializedName("purposeName")
    @Expose
    private String purposeName;

    @SerializedName("purposeID")
    @Expose
    private String purposeID;

    @SerializedName("available")
    @Expose
    private String available;

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    public String getPurposeID() {
        return purposeID;
    }

    public void setPurposeID(String purposeID) {
        this.purposeID = purposeID;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
