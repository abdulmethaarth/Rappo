package com.affinity.rappo.model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("users/customers")
    Call<Customers> getCustomers();

    @GET("users/servicetype")
    Call<ServiceType> getServiceType();

     @GET("users/purposelist")
    Call<PurposeType> getPurpose();

    @GET("users/machine")
    Call<Machines> getMachine();

    @GET("users/faultlist")
    Call<FaulsType> getFaults();

    @POST("users/login")
    @FormUrlEncoded
    Call<Users> login(
            @Field("password") String password);

    @POST("users/search")
    @FormUrlEncoded
    Call<History> searchDate(
            @Field("from") String from,
            @Field("to") String to,
            @Field("userID") String userID);

    @POST("users/search")
    @FormUrlEncoded
    Call<History> searchRno(
            @Field("serviceRNO") String serviceRNO);

    @POST("users/search")
    @FormUrlEncoded
    Call<History> searchNmae(
            @Field("customerID") String customerID,
            @Field("userID") String userID);


    @POST("users/create")
    @FormUrlEncoded
    Call<Create>createMethod(
            @Field("userID") String userID,
                                     @Field("customerID") String customerID,
                                     @Field("currentDate") String currentDate,
                                     @Field("MachineID") String MachineID,
                                     @Field("serviceTypeID") String serviceTypeID,
                                     @Field("purpose") String purpose,
                                     @Field("fault") String fault,
                                     @Field("serviceStatus") String serviceStatus,
                                     @Field("workStatus") String workStatus,
                                     @Field("spareUsed") String spareUsed,
                                     @Field("spareAmount") String spareAmount,
                                     @Field("spareStatus") String spareStatus,
                                     @Field("serviceCharge") String serviceCharge,
                                     @Field("timeSpent") String timeSpent,
                                     @Field("remarks") String remarks,
                                     @Field("customeFeed") String customeFeed);

    @POST("users/updates")
    @FormUrlEncoded
    Call<BaseResponse>updateMethod(
            @Field("userID") String userID,
            @Field("serviceID") String serviceID,
            @Field("customerID") String customerID,
            @Field("MachineID") String MachineID,
            @Field("serviceTypeID") String serviceTypeID,
            @Field("purpose") String purpose,
            @Field("fault") String fault,
            @Field("serviceStatus") String serviceStatus,
            @Field("workStatus") String workStatus,
            @Field("spareUsed") String spareUsed,
            @Field("spareAmount") String spareAmount,
            @Field("spareStatus") String spareStatus,
            @Field("serviceCharge") String serviceCharge,
            @Field("timeSpent") String timeSpent,
            @Field("serviceRNO") String serviceRNO,
            @Field("remarks") String remarks,
            @Field("customeFeed") String customeFeed);

    @FormUrlEncoded
    @POST("users/showsearchList")
    Call<History> getHistory(@Field("userID") String userID);
}
