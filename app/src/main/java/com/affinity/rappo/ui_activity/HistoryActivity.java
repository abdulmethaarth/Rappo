package com.affinity.rappo.ui_activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.affinity.rappo.R;
import com.affinity.rappo.SingleHistoryDtlsActivity;
import com.affinity.rappo.model.Api;
import com.affinity.rappo.model.Constant;
import com.affinity.rappo.model.CustomerRuslt;
import com.affinity.rappo.model.Customers;
import com.affinity.rappo.model.History;
import com.affinity.rappo.model.HistoryDetails;
import com.affinity.rappo.model.RetrofitClient;
import com.victor.loading.rotate.RotateLoading;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    ImageView side_menu;
    LinearLayout side_menu_layout,main_layout;
    RelativeLayout overall;
    String fname,lname,phone_number,email,user_id;
    TextView user_name,mob_no,mail_id;
    LinearLayout li_back_btn,li_home_btn,li_setting_btn;
    ImageView back_btn,home_btn,setting_btn;
    private ArrayList<HistoryDetails> historyDetails;
    private RecyclerView recyclerView;
    private HistoryDetailsAdapter eAdapter;
    EditText edt_serviceNo;
    Spinner edt_customer_name;
    TextView btn_nameGo,btn_serGo,dateGo,dateFrom,dateTo;
    Date strDate ,endDate;
    String CustomerId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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

        overall = (RelativeLayout) findViewById(R.id.overall);
        side_menu_layout = (LinearLayout) findViewById(R.id.side_menu_layout);
       // main_layout = (LinearLayout) findViewById(R.id.main_layout);
        side_menu = (ImageView)findViewById(R.id.side_menu);
        side_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                side_menu_layout.setVisibility(View.VISIBLE);
               // main_layout.setVisibility(View.GONE);
            }
        });

        overall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                side_menu_layout.setVisibility(View.GONE);
              //  main_layout.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(HistoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });li_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HistoryActivity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });li_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HistoryActivity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });

        getHistory();
        getCustomers();

        edt_customer_name = (Spinner)findViewById(R.id.edt_customer_name);
        edt_serviceNo = (EditText)findViewById(R.id.edt_serviceNo);
        dateGo = (TextView) findViewById(R.id.dateGo);
        dateFrom = (TextView) findViewById(R.id.dateFrom);
        dateTo = (TextView) findViewById(R.id.dateTo);
        btn_nameGo = (TextView) findViewById(R.id.btn_nameGo);
        btn_serGo = (TextView) findViewById(R.id.btn_serGo);

        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int pYear = c.get(Calendar.YEAR);
                int pMonth = c.get(Calendar.MONTH);
                int pDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(HistoryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateFrom.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });

        ImageView logout_img = (ImageView)findViewById(R.id.logout_img);
        ImageView share_img = (ImageView)findViewById(R.id.share);

        logout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HistoryActivity.this);
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
                                startActivity(new Intent(HistoryActivity.this, SplashScreen.class));
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
                Toast.makeText(HistoryActivity.this, "Clicked...", Toast.LENGTH_SHORT).show();
            }
        });

        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int pYear = c.get(Calendar.YEAR);
                int pMonth = c.get(Calendar.MONTH);
                int pDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(HistoryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateTo.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });

        dateGo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String Start = dateFrom.getText().toString();
                String EndDate =dateTo.getText().toString();

                if(Start.isEmpty()){
                    Toast.makeText(HistoryActivity.this, "From date is empty", Toast.LENGTH_SHORT).show();
                }else if(EndDate.isEmpty()){
                    Toast.makeText(HistoryActivity.this, "To date is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Api api = RetrofitClient.getApiService();
                    Call<History> call = api.searchDate(Start,EndDate,user_id);

                    call.enqueue(new Callback<History>() {
                        @Override
                        public void onResponse(Call<History> call, Response<History> response) {
                            History  users = response.body();
                            if (users.status.equalsIgnoreCase("true")) {

                                historyDetails = response.body().getHistoryDetails();
                                recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                                eAdapter = new HistoryDetailsAdapter(historyDetails);
                                RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, false);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(eAdapter);
                                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(HistoryActivity.this,
                                        recyclerView, new HistoryActivity.ClickListener() {
                                    @Override
                                    public void onClick(View view, final int position) {
                                        // Toast.makeText(HistoryActivity.this, "res "+historyDetails.get(position).getServiceRNO(), Toast.LENGTH_SHORT).show();
                                        String cust_id = historyDetails.get(position).getCustomerID();
                                        String service_id = historyDetails.get(position).getServiceID();
                                        Intent intent = new Intent(HistoryActivity.this, SingleHistoryDtlsActivity.class);
                                        intent.putExtra("ser_id",service_id);
                                        intent.putExtra("customer_id",cust_id);
                                        intent.putExtra("customer_name",historyDetails.get(position).getCustomerName().toString());
                                        intent.putExtra("machine_id",historyDetails.get(position).getMachineID().toString());
                                        intent.putExtra("serviceType_id",historyDetails.get(position).getServiceTypeID().toString());
                                        intent.putExtra("service_charge",historyDetails.get(position).getServiceCharge().toString());
                                        intent.putExtra("spare_status",historyDetails.get(position).getSpareStatus().toString());
                                        intent.putExtra("time_spent",historyDetails.get(position).getTimeSpent().toString());
                                        intent.putExtra("service_Rno",historyDetails.get(position).getServiceRNO().toString());
                                        intent.putExtra("remarks",historyDetails.get(position).getRemarks().toString());
                                        intent.putExtra("cust_feed",historyDetails.get(position).getCustomeFeed().toString());
                                        intent.putExtra("curr_date",historyDetails.get(position).getCurrentDate().toString());
                                        intent.putExtra("spare_used",historyDetails.get(position).getSpareUsed().toString());
                                        intent.putExtra("spare_amnt",historyDetails.get(position).getSpareAmount().toString());
                                        startActivity(intent);
                                    }
                                    @Override
                                    public void onLongClick(View view, int position) {
                                    }
                                }));
                            }
                            else{
                                Toast.makeText(HistoryActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<History> call, Throwable t) {
                            Toast.makeText(HistoryActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btn_nameGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Api api = RetrofitClient.getApiService();
                Call<History> call = api.searchNmae(CustomerId,user_id);

                call.enqueue(new Callback<History>() {
                    @Override
                    public void onResponse(Call<History> call, Response<History> response) {
                        History  users = response.body();
                        if (users.status.equalsIgnoreCase("true")) {

                            historyDetails = response.body().getHistoryDetails();
                            recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                            eAdapter = new HistoryDetailsAdapter(historyDetails);
                            RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(eAdapter);
                            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(HistoryActivity.this,
                                    recyclerView, new HistoryActivity.ClickListener() {
                                @Override
                                public void onClick(View view, final int position) {
                                    // Toast.makeText(HistoryActivity.this, "res "+historyDetails.get(position).getServiceRNO(), Toast.LENGTH_SHORT).show();
                                    String cust_id = historyDetails.get(position).getCustomerID();
                                    String service_id = historyDetails.get(position).getServiceID();
                                    Intent intent = new Intent(HistoryActivity.this, SingleHistoryDtlsActivity.class);
                                    intent.putExtra("ser_id",service_id);
                                    intent.putExtra("customer_id",cust_id);
                                    intent.putExtra("customer_name",historyDetails.get(position).getCustomerName().toString());
                                    intent.putExtra("machine_id",historyDetails.get(position).getMachineID().toString());
                                    intent.putExtra("serviceType_id",historyDetails.get(position).getServiceTypeID().toString());
                                    intent.putExtra("service_charge",historyDetails.get(position).getServiceCharge().toString());
                                    intent.putExtra("spare_status",historyDetails.get(position).getSpareStatus().toString());
                                    intent.putExtra("time_spent",historyDetails.get(position).getTimeSpent().toString());
                                    intent.putExtra("service_Rno",historyDetails.get(position).getServiceRNO().toString());
                                    intent.putExtra("remarks",historyDetails.get(position).getRemarks().toString());
                                    intent.putExtra("cust_feed",historyDetails.get(position).getCustomeFeed().toString());
                                    intent.putExtra("curr_date",historyDetails.get(position).getCurrentDate().toString());
                                    intent.putExtra("spare_used",historyDetails.get(position).getSpareUsed().toString());
                                    intent.putExtra("spare_amnt",historyDetails.get(position).getSpareAmount().toString());
                                    startActivity(intent);
                                }
                                @Override
                                public void onLongClick(View view, int position) {
                                }
                            }));
                        }
                        else{
                            Toast.makeText(HistoryActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<History> call, Throwable t) {
                        Toast.makeText(HistoryActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        btn_serGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // noFilters(edt_serviceNo.getText().toString());

                if(edt_serviceNo.getText().toString().isEmpty()){
                    Toast.makeText(HistoryActivity.this, "Enter the Report number", Toast.LENGTH_SHORT).show();
                }else{
                    String Rno = edt_serviceNo.getText().toString();

                    Api api = RetrofitClient.getApiService();
                    Call<History> call = api.searchRno(Rno);

                    call.enqueue(new Callback<History>() {
                        @Override
                        public void onResponse(Call<History> call, Response<History> response) {
                            History  users = response.body();
                            if (users.status.equalsIgnoreCase("true")) {

                                historyDetails = response.body().getHistoryDetails();
                                recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                                eAdapter = new HistoryDetailsAdapter(historyDetails);
                                RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, false);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(eAdapter);
                                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(HistoryActivity.this,
                                        recyclerView, new HistoryActivity.ClickListener() {
                                    @Override
                                    public void onClick(View view, final int position) {
                                        // Toast.makeText(HistoryActivity.this, "res "+historyDetails.get(position).getServiceRNO(), Toast.LENGTH_SHORT).show();
                                        String cust_id = historyDetails.get(position).getCustomerID();
                                        String service_id = historyDetails.get(position).getServiceID();
                                        Intent intent = new Intent(HistoryActivity.this, SingleHistoryDtlsActivity.class);
                                        intent.putExtra("ser_id",service_id);
                                        intent.putExtra("customer_id",cust_id);
                                        intent.putExtra("customer_name",historyDetails.get(position).getCustomerName().toString());
                                        intent.putExtra("machine_id",historyDetails.get(position).getMachineID().toString());
                                        intent.putExtra("serviceType_id",historyDetails.get(position).getServiceTypeID().toString());
                                        intent.putExtra("service_charge",historyDetails.get(position).getServiceCharge().toString());
                                        intent.putExtra("spare_status",historyDetails.get(position).getSpareStatus().toString());
                                        intent.putExtra("time_spent",historyDetails.get(position).getTimeSpent().toString());
                                        intent.putExtra("service_Rno",historyDetails.get(position).getServiceRNO().toString());
                                        intent.putExtra("remarks",historyDetails.get(position).getRemarks().toString());
                                        intent.putExtra("cust_feed",historyDetails.get(position).getCustomeFeed().toString());
                                        intent.putExtra("curr_date",historyDetails.get(position).getCurrentDate().toString());
                                        intent.putExtra("spare_used",historyDetails.get(position).getSpareUsed().toString());
                                        intent.putExtra("spare_amnt",historyDetails.get(position).getSpareAmount().toString());
                                        startActivity(intent);
                                    }
                                    @Override
                                    public void onLongClick(View view, int position) {
                                    }
                                }));
                            }
                            else{
                                Toast.makeText(HistoryActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<History> call, Throwable t) {
                            Toast.makeText(HistoryActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        edt_serviceNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                noFilters(editable.toString());
            }
        });
    }



    private void noFilters(String text) {
        ArrayList<HistoryDetails> filteredList = new ArrayList<>();
        for (HistoryDetails item : historyDetails) {
            if (item.getServiceRNO().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        eAdapter.filterList(filteredList);
    }

    private void nameFilters(String text) {
        ArrayList<HistoryDetails> filteredList = new ArrayList<>();
        for (HistoryDetails item : historyDetails) {
            if (item.getCustomerName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        eAdapter.filterList(filteredList);
    }
    private void getHistory() {

        Api api = RetrofitClient.getApiService();
        Call<History> call = api.getHistory(user_id);

        call.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                History  users = response.body();
                if (users.status.equalsIgnoreCase("true")) {

                    historyDetails = response.body().getHistoryDetails();
                    recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                    eAdapter = new HistoryDetailsAdapter(historyDetails);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);
                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(HistoryActivity.this,
                            recyclerView, new HistoryActivity.ClickListener() {
                        @Override
                        public void onClick(View view, final int position) {
                           // Toast.makeText(HistoryActivity.this, "res "+historyDetails.get(position).getServiceRNO(), Toast.LENGTH_SHORT).show();
                            String cust_id = historyDetails.get(position).getCustomerID();
                            String service_id = historyDetails.get(position).getServiceID();
                            Intent intent = new Intent(HistoryActivity.this, SingleHistoryDtlsActivity.class);
                            intent.putExtra("ser_id",service_id);
                            intent.putExtra("customer_id",cust_id);
                            intent.putExtra("customer_name",historyDetails.get(position).getCustomerName().toString());
                            intent.putExtra("machine_id",historyDetails.get(position).getMachineID().toString());
                            intent.putExtra("serviceType_id",historyDetails.get(position).getServiceTypeID().toString());
                            intent.putExtra("service_charge",historyDetails.get(position).getServiceCharge().toString());
                            intent.putExtra("spare_status",historyDetails.get(position).getSpareStatus().toString());
                            intent.putExtra("time_spent",historyDetails.get(position).getTimeSpent().toString());
                            intent.putExtra("service_Rno",historyDetails.get(position).getServiceRNO().toString());
                            intent.putExtra("remarks",historyDetails.get(position).getRemarks().toString());
                            intent.putExtra("cust_feed",historyDetails.get(position).getCustomeFeed().toString());
                            intent.putExtra("curr_date",historyDetails.get(position).getCurrentDate().toString());
                            intent.putExtra("spare_used",historyDetails.get(position).getSpareUsed().toString());
                            intent.putExtra("spare_amnt",historyDetails.get(position).getSpareAmount().toString());
                            startActivity(intent);
                        }
                        @Override
                        public void onLongClick(View view, int position) {
                        }
                    }));
                }
                else{
                    Toast.makeText(HistoryActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Recycler view clicking functions
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private HistoryActivity.ClickListener clicklistener;
        private GestureDetector gestureDetector;
        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final HistoryActivity.ClickListener clicklistener){
            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }
            return false;
        }
        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
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

                Spinner dynamicSpinner = (Spinner) findViewById(R.id.edt_customer_name);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistoryActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
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
            }

            @Override
            public void onFailure(Call<Customers> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage()+"Server error, check your internet", Toast.LENGTH_LONG).show();
            }
        });
    }
}