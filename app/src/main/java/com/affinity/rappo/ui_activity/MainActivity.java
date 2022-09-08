package com.affinity.rappo.ui_activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.affinity.rappo.R;
import com.affinity.rappo.SingleHistoryDtlsActivity;
import com.affinity.rappo.model.Constant;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
private ImageView history_btn,create_btn;
    ImageView side_menu,setting_btn;
    LinearLayout side_menu_layout,main_layout,li_setting_btn;
    RelativeLayout rl_back;
    String fname,lname,phone_number,email,user_id;
    TextView user_name,mob_no,mail_id;
    TextView main_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE);
        // user_id = prefs.getString(Constants.user_id, "");
        fname = prefs.getString(Constant.firstname, "");
        lname = prefs.getString(Constant.lastname, "");
        phone_number = prefs.getString(Constant.mobileno, "");
        email = prefs.getString(Constant.email_id, "");
        user_id = prefs.getString(Constant.user_id, "");

        main_name = (TextView)findViewById(R.id.main_name);
        user_name = (TextView)findViewById(R.id.user_name);
        mob_no = (TextView)findViewById(R.id.mob_no);
        mail_id = (TextView)findViewById(R.id.mail_id);
        user_name.setText(fname+" "+lname);
        main_name.setText(fname+" "+lname);
        mob_no.setText(phone_number);
        mail_id.setText(email);

        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        side_menu_layout = (LinearLayout) findViewById(R.id.side_menu_layout);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
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

        create_btn = (ImageView)findViewById(R.id.create_btn);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(inten);
            }
        });

        setting_btn = (ImageView)findViewById(R.id.setting_btn);
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });
        li_setting_btn = (LinearLayout)findViewById(R.id.li_setting_btn);
        li_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity .this, "Settings screen not available", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView logout_img = (ImageView)findViewById(R.id.logout_img);
        ImageView share_img = (ImageView)findViewById(R.id.share);

        logout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
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
                                startActivity(new Intent(MainActivity.this, SplashScreen.class));
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
                Toast.makeText(MainActivity.this, "Clicked...", Toast.LENGTH_SHORT).show();
            }
        });

        history_btn = (ImageView)findViewById(R.id.history_btn);
        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(inten);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new android.app.AlertDialog.Builder(this)
                .setTitle("EXIT")
                .setMessage("Are you you want to exit?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                }).create().show();
    }

}
