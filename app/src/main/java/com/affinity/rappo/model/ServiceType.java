package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceType extends BaseResponse{

    @SerializedName("result")
    @Expose
    private ArrayList<Services> services = null;

    public ArrayList<Services> getServices() {
        return services;
    }

    public void setServices(ArrayList<Services> services) {
        this.services = services;
    }
}
