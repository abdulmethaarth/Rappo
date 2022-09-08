package com.affinity.rappo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Customers {

    @SerializedName("result")
    private ArrayList<CustomerRuslt> customerRuslts;

    public Customers(){ }

    public ArrayList<CustomerRuslt> getCustomerRuslts(){
        return customerRuslts;
    }
}
