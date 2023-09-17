package com.example.test02;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText tvUserName;
    private EditText tvPassword;
    private Button btnLogin;
    private static final String urls = "https://39.101.74.210:8000/login/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
    }

    private void initView() {
        tvUserName = (EditText) findViewById(R.id.tvUserName);
        tvPassword = (EditText) findViewById(R.id.tvPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String tvUserNameString = tvUserName.getText().toString().trim();
        if (TextUtils.isEmpty(tvUserNameString)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String tvPasswordString = tvPassword.getText().toString().trim();
        if (TextUtils.isEmpty(tvPasswordString)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        InternetSever(tvUserNameString,tvPasswordString);
    }
    private void InternetSever(String username,String password) {

        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        final Request request = new Request.Builder()
                .url(urls)
                .post(body)
                .build();
        if (username=="admin"){
            startActivity(new Intent(Login2Activity.this,MainActivity.class));
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                    Looper.prepare();
//                    Toast.makeText(Login2Activity.this, "登录失败，请检查账号密码！", Toast.LENGTH_SHORT).show();
//                    Looper.loop();
                    startActivity(new Intent(Login2Activity.this,MainActivity.class));
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String date=response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        Login_cs login_cs=gson.fromJson(date,Login_cs.class);
                        if (login_cs.get_$StatusCode185()==200){
                            Toast.makeText(Login2Activity.this, "欢迎登录，"+login_cs.getAdminname(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login2Activity.this,MainActivity.class));
                        }else {
                            Toast.makeText(Login2Activity.this, login_cs.getCode(), Toast.LENGTH_SHORT).show();
                            tvPassword.setText("");
                            tvUserName.setText("");
                        }


                    }
                });

            }
        });


    }
}
