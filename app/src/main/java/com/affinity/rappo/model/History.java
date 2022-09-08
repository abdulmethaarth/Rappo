package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class History extends BaseResponse {

    @SerializedName("result")
    @Expose
    private ArrayList<HistoryDetails> historyDetails = null;

    public ArrayList<HistoryDetails> getHistoryDetails() {
        return historyDetails;
    }

    public void setHistoryDetails(ArrayList<HistoryDetails> historyDetails) {
        this.historyDetails = historyDetails;
    }
}
