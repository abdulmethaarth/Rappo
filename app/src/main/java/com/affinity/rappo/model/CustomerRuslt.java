package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerRuslt {

    @SerializedName("customersID")
    @Expose
    private String customersID;

    @SerializedName("customersName")
    @Expose
    private String customersName;

    public String getCustomersID() {
        return customersID;
    }

    public void setCustomersID(String customersID) {
        this.customersID = customersID;
    }

    public String getCustomersName() {
        return customersName;
    }

    public void setCustomersName(String customersName) {
        this.customersName = customersName;
    }
}
