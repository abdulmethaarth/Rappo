package com.affinity.rappo;

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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.affinity.rappo.model.Api;
import com.affinity.rappo.model.BaseResponse;
import com.affinity.rappo.model.Constant;
import com.affinity.rappo.model.CustomerRuslt;
import com.affinity.rappo.model.Customers;
import com.affinity.rappo.model.FaulsType;
import com.affinity.rappo.model.FaultList;
import com.affinity.rappo.model.Machines;
import com.affinity.rappo.model.RetrofitClient;
import com.affinity.rappo.model.ServiceType;
import com.affinity.rappo.model.Services;
import com.affinity.rappo.ui_activity.CreateActivity;
import com.affinity.rappo.ui_activity.Fault_and_RepairActivity;
import com.affinity.rappo.ui_activity.HistoryActivity;
import com.affinity.rappo.ui_activity.MachineStatus2Activity;
import com.affinity.rappo.ui_activity.MainActivity;
import com.affinity.rappo.ui_activity.PurposeOfVisitActivity;
import com.affinity.rappo.ui_activity.SplashScreen;
import com.affinity.rappo.ui_activity.SuccessActivity;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleHistoryDtlsActivity extends AppCompatActivity {

    ImageView side_menu;
    LinearLayout side_menu_layout,main_layout;
    RelativeLayout rl_back;
    String fname,lname,phone_number,email,user_id;
    TextView user_name,mob_no,mail_id,update;
    String [] stringArray;
    String MachineId,CustomerId;
    ImageView back_btn,home_btn,setting_btn;
    LinearLayout li_back_btn,li_home_btn,li_setting_btn;
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
    EditText edt_feedback;
    EditText edt_remarks ;
    String service_id,customer_id,customer_name,machine_id,serviceType_id,service_charge,spare_status,time_spent,service_Rno,remarks,cust_feed,spare_used,spare_amnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_history_dtls);

        service_id = getIntent().getExtras().getString("ser_id");
        customer_id = getIntent().getExtras().getString("customer_id");
        customer_name = getIntent().getExtras().getString("customer_name");
        machine_id = getIntent().getExtras().getString("machine_id");
        serviceType_id = getIntent().getExtras().getString("serviceType_id");
        service_charge = getIntent().getExtras().getString("service_charge");
        spare_status = getIntent().getExtras().getString("spare_status");
        time_spent = getIntent().getExtras().getString("time_spent");
        service_Rno = getIntent().getExtras().getString("service_Rno");
        remarks = getIntent().getExtras().getString("remarks");
        cust_feed = getIntent().getExtras().getString("cust_feed");
        spare_used = getIntent().getExtras().getString("spare_used");
        spare_amnt = getIntent().getExtras().getString("spare_amnt");

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
        edt_feedback  =(EditText)findViewById(R.id.edt_feedback) ;
        edt_remarks = (EditText)findViewById(R.id.edt_remarks) ;
        user_name.setText(fname+" "+lname);
        mob_no.setText(phone_number);
        mail_id.setText(email);

        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        side_menu_layout = (LinearLayout) findViewById(R.id.side_menu_layout);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        update = (TextView) findViewById(R.id.submit);
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

        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        side_menu_layout = (LinearLayout) findViewById(R.id.side_menu_layout);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        side_menu = (ImageView)findViewById(R.id.side_menu);
        edt_spartUsed = (EditText) findViewById(R.id.edt_spartUsed);
        edt_perts_amnt = (EditText) findViewById(R.id.edt_parts_amnt);
       // edt_rportNo = (EditText) findViewById(R.id.edt_rportNo);
        edt_serviceCharge = (EditText) findViewById(R.id.edt_serviceCharge);
        edt_time_spent = (EditText) findViewById(R.id.edt_time_spent);
        chek_spareStatus = (CheckBox) findViewById(R.id.chek_spareStatus);

        edt_spartUsed.setText(spare_used);
        edt_perts_amnt.setText(spare_amnt);
       // edt_rportNo.setText(service_Rno);
        edt_serviceCharge.setText(service_charge);
        edt_time_spent.setText(time_spent);
        edt_remarks.setText(remarks);
        edt_feedback.setText(cust_feed);


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


        ImageView logout_img = (ImageView)findViewById(R.id.logout_img);
        ImageView share_img = (ImageView)findViewById(R.id.share);

        logout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SingleHistoryDtlsActivity.this);
                alertDialogBuilder.setTitle("Logout");
                alertDialogBuilder.setMessage("Are you sure you want logout!");
                alertDialogBuilder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                SharedPreferences prefs =getSharedPreferences(Constant.MY_PREFS_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.clear();
                                editor.apply();
                                startActivity(new Intent(SingleHistoryDtlsActivity.this, SplashScreen.class));
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
                Toast.makeText(SingleHistoryDtlsActivity.this, "Clicked...", Toast.LENGTH_SHORT).show();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleHistoryDtlsActivity .this, MainActivity.class);
                startActivity(intent);
            }
        }); li_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleHistoryDtlsActivity .this,MainActivity.class);
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleHistoryDtlsActivity .this,MainActivity.class);
                startActivity(intent);
            }
        });li_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleHistoryDtlsActivity .this,MainActivity.class);
                startActivity(intent);
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingleHistoryDtlsActivity .this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });li_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingleHistoryDtlsActivity .this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String feedback = edt_feedback.getText().toString();
                String remarks = edt_remarks.getText().toString();
                String serviceCharge = edt_serviceCharge.getText().toString();
                String spareAmnt = edt_perts_amnt.getText().toString();
               // String reportNo = edt_rportNo.getText().toString();
                String spareUsed = edt_spartUsed.getText().toString();
                String timeSpent = edt_time_spent.getText().toString();
                if(edt_remarks.getText().toString().isEmpty()){
                    Toast.makeText(SingleHistoryDtlsActivity.this, "Please enter your Remarks", Toast.LENGTH_SHORT).show();
                }
                else{
                  Api myApi = RetrofitClient.getRetrofitInstance().create(Api.class);
                    Call<BaseResponse> call = myApi.updateMethod(user_id,service_id,CustomerId,MachineId,"","","","","workStatus",spareUsed,spareAmnt,"",serviceCharge,timeSpent,"",remarks,feedback);
                    call.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            BaseResponse users = response.body();
                            if (users.status.equalsIgnoreCase("true")) {
                                Toast.makeText(SingleHistoryDtlsActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SingleHistoryDtlsActivity.this, HistoryActivity.class);
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(SingleHistoryDtlsActivity.this, "Incorrect Pin", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            Toast.makeText(SingleHistoryDtlsActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        getCustomers();
        getServiceType();
        getMachines();
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
                    LinearLayout parentLayout = (LinearLayout) findViewById(R.id.check_Falutadd_layout);

                    GridLayoutManager mGridLayoutManager = new GridLayoutManager(SingleHistoryDtlsActivity.this, 2);
                    LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);
                    for(int i=0; i<banners.size(); i++){

                        CheckBox checkBox = new CheckBox(SingleHistoryDtlsActivity.this);
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
                                    stringArrayList.add(String.valueOf(banners.get(finalI).getFaultID()));
                                    stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
                                }
                                else{
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

    private void getMachines() {

        Api api = RetrofitClient.getRetrofitInstance().create(Api.class);
        Call<Machines> call = api.getMachine();
        call.enqueue(new Callback<Machines>() {
            @Override
            public void onResponse(Call<Machines> call, Response<Machines> response) {
                ArrayList<Machines.MachineList> banners = response.body().getCustomerRuslts();

                final  int n = banners.size();
                final ArrayList<HashMap> classList = new ArrayList<>();
                String[] spinnerArray = new String[n];
                HashMap<String,String> spinnerMap = new HashMap<>();

                for(int i = 0; i<banners.size(); i++){

                    String schedule_id = banners.get(i).getMachineID();
                    String subject_name = banners.get(i).getMachineName();

                    spinnerMap.put(schedule_id, subject_name);
                    classList.add(spinnerMap);

                    String total = /*"Class Section : "+schedule_id+" "+*/subject_name;
                    spinnerArray[i] = total;
                }

                Spinner dynamicSpinner = (Spinner) findViewById(R.id.machine_name);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SingleHistoryDtlsActivity .this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
                dynamicSpinner.setAdapter(adapter);

                dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String classid = String.valueOf(classList.get(position));
                        Log.v("item", (String) parent.getItemAtPosition(position));
                        MachineId = banners.get(position).getMachineID();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub
                    }
                });

                ImageView drop_down_imgs = (ImageView)findViewById(R.id.drop_down_imgs);
                drop_down_imgs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dynamicSpinner.performClick();
                    }
                });
            }

            @Override
            public void onFailure(Call<Machines> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage()+"Server error, check your internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getServiceType() {
        Api api = RetrofitClient.getRetrofitInstance().create(Api.class);
        Call<ServiceType> call = api.getServiceType();
        call.enqueue(new Callback<ServiceType>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ServiceType> call, Response<ServiceType> response) {
                ServiceType users = response.body();
                if(users.status.equalsIgnoreCase("true")){
                    ArrayList<Services> banners = response.body().getServices();
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    LinearLayout parentLayout = (LinearLayout) findViewById(R.id.check_add_layout);

                    LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);
                    for(int i=0; i<banners.size(); i++){

                        CheckBox checkBox = new CheckBox(SingleHistoryDtlsActivity .this);
                        checkBox.setButtonDrawable(R.drawable.check_box_selector);
                        checkBox.setText(banners.get(i).getServiceName());
                        checkBox.setTextColor(Color.WHITE);
                        checkBox.setTextSize(16);
                        checkBox.setPadding(checkBox.getTotalPaddingLeft() + 10, 15, 0, 10);
                        checkBox.setLayoutParams(lparams);

                        parentLayout.addView(checkBox);

                        int finalI = i;
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                        {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){

                                    stringArrayList.add(String.valueOf(banners.get(finalI).getServiceID()));
                                    stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
                                }
                                else{
                                    stringArrayList.remove(String.valueOf(banners.get(finalI).getServiceID()));
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
            public void onFailure(Call<ServiceType> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage()+"Server error, check your internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getCustomers() {
        Api api = RetrofitClient.getRetrofitInstance().create(Api.class);
        Call<Customers> call = api.getCustomers();
        call.enqueue(new Callback<Customers>() {
            @Override
            public void onResponse(Call<Customers> call, Response<Customers> response) {
                ArrayList<CustomerRuslt> banners = response.body().getCustomerRuslts();

                final  int n = banners.size();
                final ArrayList<HashMap> classList = new ArrayList<>();
                String[] spinnerArray = new String[n];
                HashMap<String,String> spinnerMap = new HashMap<>();

                for(int i = 0; i<banners.size(); i++){

                    String schedule_id = banners.get(i).getCustomersID();
                    String subject_name = banners.get(i).getCustomersName();

                    spinnerMap.put(schedule_id, subject_name);
                    classList.add(spinnerMap);

                    String total = /*"Class Section : "+schedule_id+" "+*/subject_name;
                    spinnerArray[i] = total;
                }

                Spinner dynamicSpinner = (Spinner) findViewById(R.id.customer_name);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SingleHistoryDtlsActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
                dynamicSpinner.setAdapter(adapter);

                dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String classid = String.valueOf(classList.get(position));
                        Log.v("item", (String) parent.getItemAtPosition(position));
                        CustomerId = banners.get(position).getCustomersID();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO Auto-generated method stub
                    }
                });
                ImageView drop_down_img = (ImageView)findViewById(R.id.drop_down_img);
                drop_down_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dynamicSpinner.performClick();
                    }
                });
            }

            @Override
            public void onFailure(Call<Customers> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage()+"Server error, check your internet", Toast.LENGTH_LONG).show();
            }
        });
    }
}