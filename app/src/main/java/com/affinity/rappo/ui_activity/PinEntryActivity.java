package com.affinity.rappo.ui_activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.affinity.rappo.model.Api;
import com.affinity.rappo.R;
import com.affinity.rappo.model.RetrofitClient;
import com.affinity.rappo.model.Constant;
import com.affinity.rappo.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PinEntryActivity extends AppCompatActivity implements TextWatcher {

    LinearLayout continue_btn;
    EditText editText_one,editText_two,editText_three,editText_four;
    Retrofit retrofit;
    Api myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_entry);

        editText_one = (EditText)findViewById(R.id.editTextone);
        editText_two = (EditText)findViewById(R.id.editTexttwo);
        editText_three = (EditText)findViewById(R.id.editTextthree);
        editText_four = (EditText)findViewById(R.id.editTextfour);

        editText_one.addTextChangedListener(PinEntryActivity.this);
        editText_two.addTextChangedListener(PinEntryActivity.this);
        editText_three.addTextChangedListener(PinEntryActivity.this);
        editText_four.addTextChangedListener(PinEntryActivity.this);

        continue_btn = (LinearLayout) findViewById(R.id.continue_btn);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinValidation();
            }
        });

    }

    private void pinValidation() {

        String one = editText_one.getText().toString();
        String two = editText_two.getText().toString();
        String three = editText_three.getText().toString();
        String four = editText_four.getText().toString();
        if(one.isEmpty()){
            Toast.makeText(this, "Please enter your 4 digit pin", Toast.LENGTH_SHORT).show();
        }else if(two.isEmpty()){
            Toast.makeText(this, "Please enter your 4 digit pin", Toast.LENGTH_SHORT).show();
        }else if(three.isEmpty()){
            Toast.makeText(this, "Please enter your 4 digit pin", Toast.LENGTH_SHORT).show();
        }else if(four.isEmpty()){
            Toast.makeText(this, "Please enter your 4 digit pin", Toast.LENGTH_SHORT).show();
        }
        else{
            String password = one+two+three+four;

            myApi = RetrofitClient.getRetrofitInstance().create(Api.class);
            Call<Users> call = myApi.login(password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    Users users = response.body();
                    if (users.status.equalsIgnoreCase("true")) {
                        List<Users.LoginUserDetails> userData = users.getUserArray();
                        SharedPreferences.Editor editor = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString(Constant.user_id, userData.get(0).getId());
                        editor.putString(Constant.firstname, userData.get(0).getFirstname());
                        editor.putString(Constant.lastname,userData.get(0).getLastname());
                        editor.putString(Constant.email_id, userData.get(0).getEmail());
                        editor.putString(Constant.mobileno, userData.get(0).getMobile());
                        editor.putBoolean(Constant.KEY_PIN_LOGGED_IN,true);
                        // editor.putString(Constant.image, userData.getUser_image());
                        editor.apply();
                        Intent intent = new Intent(PinEntryActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(PinEntryActivity.this, "Incorrect Pin", Toast.LENGTH_SHORT).show();
                    }

                    // loader(false);
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(PinEntryActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    // To enter only one value to edit Text and cursor move on next edit Text automatically and when we want to remove password one by one sequencially in which cursor move back automatically,to write the cobe below on this method:

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() == 1) {
            if (editText_one.length() == 1) {
                editText_two.requestFocus();
            }

            if (editText_two.length() == 1) {
                editText_three.requestFocus();
            }
            if (editText_three.length() == 1) {
                editText_four.requestFocus();
            }
        } else if (editable.length() == 0) {
            if (editText_four.length() == 0) {
                editText_three.requestFocus();
            }
            if (editText_three.length() == 0) {
                editText_two.requestFocus();
            }
            if (editText_two.length() == 0) {
                editText_one.requestFocus();
            }
        }
    }
}