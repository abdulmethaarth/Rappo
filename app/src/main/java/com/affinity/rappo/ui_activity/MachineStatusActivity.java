package com.affinity.rappo.ui_activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affinity.rappo.R;
import com.affinity.rappo.model.Constant;

public class MachineStatusActivity extends AppCompatActivity {
    ImageView side_menu;
    LinearLayout side_menu_layout,main_layout;
    RelativeLayout rl_back;
    String fname,lname,phone_number,email,user_id;
    TextView user_name,mob_no,mail_id,save_continue;
    private RadioGroup radiogrupService,radiogropStatus;
    private RadioButton radioButtonService,radioButtonStatus;
    EditText edt_spartUsed,edt_perts_amnt,edt_serviceCharge,edt_time_spent,edt_rportNo;
    String partUsedDtls = " ";
    String partamntDtls = " ";
    String rprtNoDtls = " ";
    String serviceDtls = " ";
    String timeDtls = " ";
    String ServiceRadioBtn = " ";
    String StatusRadioBtn = " ";
    String sparePartsSTatus = " ";
    CheckBox chek_spareStatus;
    ImageView back_btn,home_btn,setting_btn;
    LinearLayout li_back_btn,li_home_btn,li_setting_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_status);

        SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        // user_id = prefs.getString(Constants.user_id, "");
        fname = prefs.getString(Constant.firstname, "");
        lname = prefs.getString(Constant.lastname, "");
        phone_number = prefs.getString(Constant.mobileno, "");
        email = prefs.getString(Constant.email_id, "");
        user_id = prefs.getString(Constant.user_id, "");

        user_name = (TextView)findViewById(R.id.user_name);
        mob_no = (TextView)findViewById(R.id.mob_no);
        mail_id = (TextView)findViewById(R.id.mail_id);
        user_name.setText(fname+" "+lname);
        mob_no.setText(phone_number);
        mail_id.setText(email);

        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        side_menu_layout = (LinearLayout) findViewById(R.id.side_menu_layout);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        save_continue = (TextView)findViewById(R.id.save_continue);
        side_menu = (ImageView)findViewById(R.id.side_menu);
        edt_spartUsed = (EditText) findViewById(R.id.edt_spartUsed);
        edt_perts_amnt = (EditText) findViewById(R.id.edt_parts_amnt);
       // edt_rportNo = (EditText) findViewById(R.id.edt_rportNo);
        edt_serviceCharge = (EditText) findViewById(R.id.edt_serviceCharge);
        edt_time_spent = (EditText) findViewById(R.id.edt_time_spent);
        chek_spareStatus = (CheckBox) findViewById(R.id.chek_spareStatus);

        chek_spareStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sparePartsSTatus = buttonView.getText().toString();
                }
            }
        });

        radiogrupService = (RadioGroup) findViewById(R.id.radiogrupService);
        radiogropStatus = (RadioGroup) findViewById(R.id.radiogropStatus);
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
                Intent intent = new Intent(MachineStatusActivity.this,Fault_and_RepairActivity.class);
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MachineStatusActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });li_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MachineStatusActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MachineStatusActivity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });li_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MachineStatusActivity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView logout_img = (ImageView)findViewById(R.id.logout_img);
        ImageView share_img = (ImageView)findViewById(R.id.share);

        logout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MachineStatusActivity.this);
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
                                startActivity(new Intent(MachineStatusActivity.this, SplashScreen.class));
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
                Toast.makeText(MachineStatusActivity.this, "Clicked...", Toast.LENGTH_SHORT).show();
            }
        });
        save_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if (radiogropStatus.getCheckedRadioButtonId() == -1) {
                     Toast.makeText(MachineStatusActivity.this, "Please select your service", Toast.LENGTH_SHORT).show();

                    return;
                }
                 else if(radiogrupService.getCheckedRadioButtonId() == -1){
                     Toast.makeText(MachineStatusActivity.this, "Please select your work status", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     int selectedId = radiogropStatus.getCheckedRadioButtonId();
                     radioButtonStatus = (RadioButton) findViewById(selectedId);
                     StatusRadioBtn = radioButtonStatus.getText().toString();

                     int selectedIds = radiogrupService.getCheckedRadioButtonId();
                     radioButtonService = (RadioButton) findViewById(selectedIds);
                     ServiceRadioBtn = radioButtonService.getText().toString();

                     partUsedDtls= edt_spartUsed.getText().toString();
                     partamntDtls= edt_perts_amnt.getText().toString();
                     //rprtNoDtls= edt_rportNo.getText().toString();
                     serviceDtls= edt_serviceCharge.getText().toString();
                     timeDtls= edt_time_spent.getText().toString();

                     SharedPreferences.Editor editor = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE).edit();
                     editor.putString(Constant.service, ServiceRadioBtn);
                     editor.putString(Constant.workStatus,StatusRadioBtn );
                     editor.putString(Constant.sparePartsUsed, partUsedDtls);
                     editor.putString(Constant.sparePartsAmnt,partamntDtls );
                     editor.putString(Constant.serviceCharge,serviceDtls );
                     editor.putString(Constant.timeSpent, timeDtls);
                    // editor.putString(Constant.rportNo,rprtNoDtls );
                     editor.putString(Constant.sparePartsstatus,sparePartsSTatus );
                     editor.apply();

                     Intent intent = new Intent(MachineStatusActivity.this, MachineStatus2Activity.class);
                     startActivity(intent);
                 }
            }
        });
    }
}