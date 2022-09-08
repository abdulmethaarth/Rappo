package com.affinity.rappo.ui_activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affinity.rappo.R;
import com.affinity.rappo.model.Api;
import com.affinity.rappo.model.BaseResponse;
import com.affinity.rappo.model.Constant;
import com.affinity.rappo.model.Create;
import com.affinity.rappo.model.RetrofitClient;
import com.affinity.rappo.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MachineStatus2Activity extends AppCompatActivity {

    ImageView side_menu;
    LinearLayout side_menu_layout,main_layout;
    RelativeLayout rl_back;
    String fname,lname,phone_number,email,user_id,customer_id;
    TextView user_name,mob_no,mail_id,save_continue;
    EditText edt_feedback,edt_remarks;
    String feedback = " ";
    String remarks = " ";
    Api myApi;
    String machine_id,createUserCheckValue,purposeCheckValue,ifOtherFault,service,workStatus,
            sparePartsUsed,sparePartsAmnt,sparePartsstatus,serviceCharge,timeSpent,rportNo,selectDate;
    ImageView back_btn,home_btn,setting_btn;
    LinearLayout li_back_btn,li_home_btn,li_setting_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_status2);

        SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        // user_id = prefs.getString(Constants.user_id, "");
        fname = prefs.getString(Constant.firstname, "");
        lname = prefs.getString(Constant.lastname, "");
        phone_number = prefs.getString(Constant.mobileno, "");
        email = prefs.getString(Constant.email_id, "");
        user_id = prefs.getString(Constant.user_id, "");
        customer_id = prefs.getString(Constant.customer_id, "");
        machine_id = prefs.getString(Constant.machine_id, "");
        createUserCheckValue = prefs.getString(Constant.createUserCheckValue, "");
        purposeCheckValue = prefs.getString(Constant.purposeCheckValue, "");
        ifOtherFault = prefs.getString(Constant.ifOtherFault, "");
        service = prefs.getString(Constant.service, "");
        workStatus = prefs.getString(Constant.workStatus, "");
        sparePartsUsed = prefs.getString(Constant.sparePartsUsed, "");
        sparePartsAmnt = prefs.getString(Constant.sparePartsAmnt, "");
        sparePartsstatus = prefs.getString(Constant.sparePartsstatus, "");
        serviceCharge = prefs.getString(Constant.serviceCharge, "");
        timeSpent = prefs.getString(Constant.timeSpent, "");
        rportNo = prefs.getString(Constant.rportNo, "");
        selectDate = prefs.getString(Constant.selectDate, "");

        edt_feedback = (EditText) findViewById(R.id.edt_feedback);
        edt_remarks = (EditText) findViewById(R.id.edt_remarks);
        user_name = (TextView)findViewById(R.id.user_name);
        mob_no = (TextView)findViewById(R.id.mob_no);
        mail_id = (TextView)findViewById(R.id.mail_id);
        user_name.setText(fname+" "+lname);
        mob_no.setText(phone_number);
        mail_id.setText(email);

        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        side_menu_layout = (LinearLayout) findViewById(R.id.side_menu_layout);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        save_continue = (TextView) findViewById(R.id.save_continue);
        side_menu = (ImageView)findViewById(R.id.side_menu);
        side_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                side_menu_layout.setVisibility(View.VISIBLE);
                main_layout.setVisibility(View.GONE);
            }
        });

        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                side_menu_layout.setVisibility(View.GONE);
                main_layout.setVisibility(View.VISIBLE);
            }
        });

        back_btn = (ImageView)findViewById(R.id.back_btn);
        home_btn = (ImageView)findViewById(R.id.home_btn);
        setting_btn = (ImageView)findViewById(R.id.setting_btn);
        li_back_btn = (LinearLayout) findViewById(R.id.li_back_btn);
        li_home_btn = (LinearLayout)findViewById(R.id.li_home_btn);
        li_setting_btn = (LinearLayout)findViewById(R.id.li_setting_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        }); li_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MachineStatus2Activity.this,MachineStatusActivity.class);
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MachineStatus2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });li_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MachineStatus2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView logout_img = (ImageView)findViewById(R.id.logout_img);
        ImageView share_img = (ImageView)findViewById(R.id.share);

        logout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MachineStatus2Activity.this);
                alertDialogBuilder.setTitle("Logout");
                alertDialogBuilder.setMessage("Are you sure you want logout!");
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                SharedPreferences prefs =getSharedPreferences(Constant.MY_PREFS_NAME,Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.clear();
                                editor.apply();
                                startActivity(new Intent(MachineStatus2Activity.this, SplashScreen.class));
                                finish();

                            }
                        });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        share_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MachineStatus2Activity.this, "Clicked...", Toast.LENGTH_SHORT).show();
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MachineStatus2Activity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });li_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MachineStatus2Activity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });

        save_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback = edt_feedback.getText().toString();
                remarks = edt_remarks.getText().toString();
                /*if (edt_feedback.getText().toString().isEmpty()) {
                    Toast.makeText(MachineStatus2Activity.this, "Please enter your Feedback..", Toast.LENGTH_SHORT).show();
                }*/
              if(edt_remarks.getText().toString().isEmpty()){
                    Toast.makeText(MachineStatus2Activity.this, "Please enter your Remarks", Toast.LENGTH_SHORT).show();
                }
                else{

                    myApi = RetrofitClient.getRetrofitInstance().create(Api.class);
                    Call<Create> call = myApi.createMethod(user_id,customer_id,selectDate,machine_id,/*createUserCheckValue,purposeCheckValue*/"","",ifOtherFault,service,workStatus,sparePartsUsed,sparePartsAmnt,sparePartsstatus,serviceCharge,timeSpent,remarks,feedback);
                    call.enqueue(new Callback<Create>() {
                        @Override
                        public void onResponse(Call<Create> call, Response<Create> response) {
                            Create users = response.body();
                            if (users.status.equalsIgnoreCase("true")) {
                                Create.Result userData = users.getUserDetails();
                                Intent intent = new Intent(MachineStatus2Activity.this, SuccessActivity.class);
                                intent.putExtra("RNO",userData.getServiceRNO());
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MachineStatus2Activity.this, "Incorrect Pin", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Create> call, Throwable t) {
                            Toast.makeText(MachineStatus2Activity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}