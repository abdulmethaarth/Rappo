package com.affinity.rappo.ui_activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affinity.rappo.R;
import com.affinity.rappo.model.Api;
import com.affinity.rappo.model.Constant;
import com.affinity.rappo.model.FaulsType;
import com.affinity.rappo.model.FaultList;
import com.affinity.rappo.model.PurposeList;
import com.affinity.rappo.model.PurposeType;
import com.affinity.rappo.model.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fault_and_RepairActivity extends AppCompatActivity {

    ImageView side_menu,close_btn;
    LinearLayout side_menu_layout,main_layout;
    RelativeLayout rl_back;
    String fname,lname,phone_number,email,user_id;
    TextView user_name,mob_no,mail_id,save_continue;
    EditText if_others_edt;
    CheckBox checkBoxOthers;
    String [] stringArray;
    String ifOtherEdt = "";
    ImageView back_btn,home_btn,setting_btn;
    LinearLayout li_back_btn,li_home_btn,li_setting_btn;
    CheckBox checkBox;
    String checked = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_and__repair);

        SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        // user_id = prefs.getString(Constants.user_id, "");
        fname = prefs.getString(Constant.firstname, "");
        lname = prefs.getString(Constant.lastname, "");
        phone_number = prefs.getString(Constant.mobileno, "");
        email = prefs.getString(Constant.email_id, "");
        user_id = prefs.getString(Constant.user_id, "");

        user_name = (TextView)findViewById(R.id.user_name);
        if_others_edt = (EditText)findViewById(R.id.if_others_edt);
        close_btn = (ImageView) findViewById(R.id.close_btn);
        ifOtherEdt = if_others_edt.getText().toString();
        mob_no = (TextView)findViewById(R.id.mob_no);
        mail_id = (TextView)findViewById(R.id.mail_id);
        user_name.setText(fname+" "+lname);
        mob_no.setText(phone_number);
        mail_id.setText(email);

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
                Intent intent = new Intent(Fault_and_RepairActivity.this,PurposeOfVisitActivity.class);
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fault_and_RepairActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });li_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fault_and_RepairActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fault_and_RepairActivity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });li_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fault_and_RepairActivity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });

       // checkBox = (CheckBox)findViewById(R.id.chckbox_other);

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



        checkBoxOthers = (CheckBox) findViewById(R.id.chckbox_other);
        checkBoxOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxOthers.isChecked()) {
                    if_others_edt.setVisibility(View.VISIBLE);
                    close_btn.setVisibility(View.VISIBLE);
                }else{
                    if_others_edt.setVisibility(View.GONE);
                    close_btn.setVisibility(View.GONE);
                }
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if_others_edt.setVisibility(View.GONE);
                close_btn.setVisibility(View.GONE);
                checkBoxOthers.setChecked(false);
            }
        });

        ImageView logout_img = (ImageView)findViewById(R.id.logout_img);
        ImageView share_img = (ImageView)findViewById(R.id.share);

        logout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fault_and_RepairActivity.this);
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
                                startActivity(new Intent(Fault_and_RepairActivity.this, SplashScreen.class));
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
                Toast.makeText(Fault_and_RepairActivity.this, "Clicked...", Toast.LENGTH_SHORT).show();
            }
        });

        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                side_menu_layout.setVisibility(View.GONE);
                main_layout.setVisibility(View.VISIBLE);
            }
        });

        save_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checked.equalsIgnoreCase("true")){
                    SharedPreferences.Editor editor = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString(Constant.faultID, String.valueOf(stringArray));
                    editor.putString(Constant.ifOtherFault, ifOtherEdt);
                    editor.putString(Constant.FalutCheckValue, String.valueOf(stringArray));
                    editor.apply();
                    Intent intent = new Intent(Fault_and_RepairActivity.this, MachineStatusActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Fault_and_RepairActivity.this, "Please check any one..", Toast.LENGTH_SHORT).show();
                }

            }
        });
        getFaults();
    }

    private void getFaults() {

        Api api = RetrofitClient.getRetrofitInstance().create(Api.class);
        Call<FaulsType> call = api.getFaults();
        call.enqueue(new Callback<FaulsType>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<FaulsType> call, Response<FaulsType> response) {
                FaulsType users = response.body();
                if(users.status.equalsIgnoreCase("true")){
                    ArrayList<FaultList> banners = response.body().getFaultLists();
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    LinearLayout parentLayout = (LinearLayout) findViewById(R.id.check_add_layout);

                    GridLayoutManager mGridLayoutManager = new GridLayoutManager(Fault_and_RepairActivity.this, 2);
                    LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);
                    for(int i=0; i<banners.size(); i++){

                        checkBox = new CheckBox(Fault_and_RepairActivity.this);
                        checkBox.setButtonDrawable(R.drawable.check_box_selector);
                        checkBox.setText(banners.get(i).getFaultName());
                        checkBox.setTextColor(Color.WHITE);
                        checkBox.setTextSize(16);
                        checkBox.setPadding(checkBox.getTotalPaddingLeft(), 15, 0, 10);
                        checkBox.setLayoutParams(lparams);
                        parentLayout.addView(checkBox);

                        int finalI = i;
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                        {
                            @SuppressLint("ResourceType")
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    checked = "true";
                                    stringArrayList.add(String.valueOf(banners.get(finalI).getFaultID()));
                                    stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
                                }
                                else{
                                    checked = "false";
                                    stringArrayList.remove(String.valueOf(banners.get(finalI).getFaultID()));
                                    stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
                                }
                            }
                        });
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<FaulsType> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage()+"Server error, check your internet", Toast.LENGTH_LONG).show();
            }
        });
    }
}