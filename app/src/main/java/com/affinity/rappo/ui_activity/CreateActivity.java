package com.affinity.rappo.ui_activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.affinity.rappo.R;
import com.affinity.rappo.model.Api;
import com.affinity.rappo.model.BaseResponse;
import com.affinity.rappo.model.Constant;
import com.affinity.rappo.model.CustomerRuslt;
import com.affinity.rappo.model.Customers;
import com.affinity.rappo.model.Machines;
import com.affinity.rappo.model.RetrofitClient;
import com.affinity.rappo.model.ServiceType;
import com.affinity.rappo.model.Services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {

    ImageView side_menu;
    LinearLayout side_menu_layout,main_layout;
    RelativeLayout rl_back;
    String fname,lname,phone_number,email,user_id;
    TextView user_name,mob_no,mail_id,save_continue,selectDate;
    String [] stringArray;
    String MachineId,CustomerId;
    ImageView back_btn,home_btn,setting_btn,drop_down_img;
    LinearLayout li_back_btn,li_home_btn,li_setting_btn;
    CheckBox checkBox;
    String checked = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        // user_id = prefs.getString(Constants.user_id, "");
        fname = prefs.getString(Constant.firstname, "");
        lname = prefs.getString(Constant.lastname, "");
        phone_number = prefs.getString(Constant.mobileno, "");
        email = prefs.getString(Constant.email_id, "");
        user_id = prefs.getString(Constant.user_id, "");

        user_name = (TextView)findViewById(R.id.user_name);
        selectDate = (TextView)findViewById(R.id.selectDate);
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
        drop_down_img = (ImageView)findViewById(R.id.drop_down_img);
        setting_btn = (ImageView)findViewById(R.id.setting_btn);

       li_back_btn = (LinearLayout) findViewById(R.id.li_back_btn);
        li_home_btn = (LinearLayout)findViewById(R.id.li_home_btn);
       li_setting_btn = (LinearLayout)findViewById(R.id.li_setting_btn);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int pYear = c.get(Calendar.YEAR);
                int pMonth = c.get(Calendar.MONTH);
                int pDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }); li_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView logout_img = (ImageView)findViewById(R.id.logout_img);
        ImageView share_img = (ImageView)findViewById(R.id.share);

        logout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CreateActivity.this);
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
                                startActivity(new Intent(CreateActivity.this, SplashScreen.class));
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
                Toast.makeText(CreateActivity.this, "Clicked...", Toast.LENGTH_SHORT).show();
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });li_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateActivity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });li_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateActivity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });

        save_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectDate.getText().toString().isEmpty()){
                    Toast.makeText(CreateActivity.this, "Please enter date..", Toast.LENGTH_SHORT).show();
                }
               else if(checked.equalsIgnoreCase("false")){
                    Toast.makeText(CreateActivity.this, "Please check any one..", Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences.Editor editor = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString(Constant.machine_id, MachineId);
                    editor.putString(Constant.customer_id, CustomerId);
                    editor.putString(Constant.selectDate, selectDate.getText().toString());
                    editor.putString(Constant.createUserCheckValue, String.valueOf(stringArray));
                    editor.apply();
                    Intent intent = new Intent(CreateActivity.this, PurposeOfVisitActivity.class);
                    startActivity(intent);
                }
            }
        });

        getCustomers();
        getServiceType();
        getMachines();
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
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

                    checkBox = new CheckBox(CreateActivity.this);
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
                                checked = "true";
                                stringArrayList.add(String.valueOf(banners.get(finalI).getServiceID()));
                               stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
                            }
                            else{
                                checked = "false";
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
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