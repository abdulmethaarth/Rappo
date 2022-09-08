package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PurposeType extends BaseResponse{

    @SerializedName("result")
    @Expose
    private ArrayList<PurposeList> purposeLists = null;

    public ArrayList<PurposeList> getPurposeLists() {
        return purposeLists;
    }

    public void setPurposeLists(ArrayList<PurposeList> purposeLists) {
        this.purposeLists = purposeLists;
    }
}
