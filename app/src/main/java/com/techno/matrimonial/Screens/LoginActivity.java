package com.techno.matrimonial.Screens;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.techno.matrimonial.Global.AnimUtils;
import com.techno.matrimonial.Global.AppDialog;
import com.techno.matrimonial.Global.Global;
import com.techno.matrimonial.Global.Log;
import com.techno.matrimonial.Listener.OnApiCallListener;
import com.techno.matrimonial.Model.common.DeviceInfo;
import com.techno.matrimonial.Model.common.ErrorMessageMain;
import com.techno.matrimonial.Model.common.Login;
import com.techno.matrimonial.Model.common.LoginDetails;
import com.techno.matrimonial.R;
import com.techno.matrimonial.WebServices.Api;
import com.techno.matrimonial.WebServices.ApiFunctions;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements OnApiCallListener {

    TextView txt_TC;
    Button btn_login;
    EditText et_login_user, et_login_password;
    DeviceInfo deviceInfo;
    ApiFunctions apiFunctions;
    LoginDetails loginDetails;
    String userLoginResponse;
    ErrorMessageMain errorMessageMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        apiFunctions = new ApiFunctions(this, this);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_login_user = (EditText) findViewById(R.id.et_login_user);
        et_login_user.setText("akshay@gmail.com");
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        et_login_password.setText("123456");


        //for TC text size
        String s = getString(R.string.login_TC);
        SpannableString ss1 = new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 37, 57, 0); // set size

        // ss1.setSpan(new ForegroundColorSpan(Color.RED), 37, 57, 0);// set color
        txt_TC = (TextView) findViewById(R.id.txt_TC);
        txt_TC.setText(ss1);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_login_user.getText().toString();
                String password = et_login_password.getText().toString();
                if (Global.isNetworkAvailable(LoginActivity.this)) {
                    if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                        deviceInfo = new DeviceInfo();
                        Login login = new Login(email, password, deviceInfo);
                        apiFunctions.userLogin(Api.MainUrl + Api.ActionLogin, login);
                        AppDialog.showProgressDialog(LoginActivity.this);
                    } else {
                        AppDialog.showAlertDialog(LoginActivity.this, null, getResources().getString(R.string.error_enter_login_info),
                                getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                    }

                } else {
                    AppDialog.noNetworkDialog(LoginActivity.this, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    });
                }
            }
        });

        //For animation
        AnimUtils.activityenterAnim(LoginActivity.this);
    }


    @Override
    public void onSuccess(final int responseCode, String responseString, String url) {
        Log.e("Login Success" + responseString);
        AppDialog.dismissProgressDialog();
        Gson gson = new Gson();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(responseString);
            if (responseCode == Api.ResponseOk) {
                loginDetails = gson.fromJson(jsonObject.toString(), LoginDetails.class);
                userLoginResponse = gson.toJson(loginDetails, LoginDetails.class);
            } else if (responseCode == Api.ResponseUnauthorized) {
                JSONObject getFirst = jsonObject.getJSONObject(Api.error);
                errorMessageMain = gson.fromJson(getFirst.toString(), ErrorMessageMain.class);
            } else {
                //If Response Code Except(200 and 401)
            }
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (responseCode == Api.ResponseOk) {
                        Global.storePreference("AccessToken", loginDetails.getAccess_token());
                        Global.storePreference("LoginDetails", userLoginResponse);
                        Global.storePreference("IsLogin", true);
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    } else if (responseCode == Api.ResponseUnauthorized) {
                        Global.clearPreferences();
                        startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                        AppDialog.showAlertDialog(LoginActivity.this, null, errorMessageMain.getDescription(),
                                getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                    } else {
                        AppDialog.showAlertDialog(LoginActivity.this, null, errorMessageMain.getDescription(),
                                getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(final String errorMessage) {
        Log.e("Login Failure " + errorMessage);
        AppDialog.dismissProgressDialog();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppDialog.showAlertDialog(LoginActivity.this, null, errorMessage,
                        getString(R.string.txt_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
            }
        });

    }
}
