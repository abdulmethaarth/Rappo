package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaulsType extends BaseResponse{

    @SerializedName("result")
    @Expose
    private ArrayList<FaultList> faultLists = null;

    public ArrayList<FaultList> getFaultLists() {
        return faultLists;
    }

    public void setFaultLists(ArrayList<FaultList> faultLists) {
        this.faultLists = faultLists;
    }
}
