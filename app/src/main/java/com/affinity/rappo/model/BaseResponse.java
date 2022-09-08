package com.affinity.rappo.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("status")
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
