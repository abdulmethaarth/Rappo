package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Create extends BaseResponse {


    @SerializedName("result")
    @Expose
    private Result userDetails;

    public Result getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Result userDetails) {
        this.userDetails = userDetails;
    }

    public class Result{
        @SerializedName("serviceRNO")
        @Expose
        private String serviceRNO;

        public String getServiceRNO() {
            return serviceRNO;
        }

        public void setServiceRNO(String serviceRNO) {
            this.serviceRNO = serviceRNO;
        }
    }
}
