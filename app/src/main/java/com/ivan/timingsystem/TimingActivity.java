package com.ivan.timingsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ivan.timingsystem.util.Config;
import com.ivan.timingsystem.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TimingActivity extends AppCompatActivity {

    TextView etPhone;
    Button btnEnter, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        etPhone = findViewById(R.id.etPhone);
        btnEnter = findViewById(R.id.btnEnter);
        btnExit = findViewById(R.id.btnExit);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterFunciton();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitFunciton();
            }
        });

    }

    public void KeyBoardClick(View view) {
        switch (view.getId()) {
            case R.id.btnClear:
                etPhone.setText("");
                break;
            case R.id.btnNull:
                break;
            default:
                String tempstr = "" + etPhone.getText() + ((Button) view).getText();
                etPhone.setText(tempstr);
                break;

        }
    }


    public void EnterFunciton() {
//        OkHttpClient mOkHttpClient=new OkHttpClient();
//        Request.Builder requestBuilder = new Request.Builder().url(Config.Url+"GetBillList");
//        //可以省略，默认是GET请求
//        requestBuilder.method("GET",null);
//        Request request = requestBuilder.build();
//        Call mcall= mOkHttpClient.newCall(request);
//        mcall.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (null != response.cacheResponse()) {
//                    String str = response.cacheResponse().toString();
//                    Log.i("wangshu", "cache---" + str);
//                } else {
//                    response.body().string();
//                    String str = response.networkResponse().toString();
//                    Log.i("wangshu", "network---" + str);
//                }
//                String result = response.body().string();
//                Log.i("wangshu", "result---" + result);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                     //   String str = response.body().string();
//                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        HttpUtil hutil=new HttpUtil();
        hutil.requestAsyn()
    }


    public void ExitFunciton() {

    }

}
