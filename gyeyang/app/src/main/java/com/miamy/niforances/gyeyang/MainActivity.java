package com.miamy.niforances.gyeyang;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miamy.niforances.gyeyang.ui.login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private static final int REQUEST_CODE_LOCATION = 2;


    ArrayList<Info> list = new ArrayList<>();
    String jsonResult;

    TextView tvResult;
    TextView tvTest;

    Button btnGoMap;
    Button btnGoAbout;


    String url;

    private HttpConnection httpConnection = HttpConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        url = "http://34.97.181.218:7000/api/posts/";

        httpConnection.requestWebServer("" + url, callback);

        tvResult = findViewById(R.id.tvResult);
        tvTest = findViewById(R.id.tvTest);

        btnGoMap = findViewById(R.id.btnGoMap);
        btnGoAbout = findViewById(R.id.btnGoAbout);

        btnGoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                intent.putExtra("arrayList", list);

                startActivity(intent);
            }
        });

        btnGoAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
              startActivity(intent);
            }
        });
    }

    private void jsonParser(String json) throws JSONException {

        Gson gson = new Gson();

        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            Info info = gson.fromJson(jsonArray.get(i).toString(), Info.class);
            list.add(info);
        }

    }


    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            httpConnection.requestWebServer("" + url, callback);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {


            jsonResult = response.body().string();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {
                        jsonParser(jsonResult);
                    } catch (JSONException e) {

                    }
                }
            });
        }
    };


}
