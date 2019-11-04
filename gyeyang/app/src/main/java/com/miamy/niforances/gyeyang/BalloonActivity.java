package com.miamy.niforances.gyeyang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BalloonActivity extends AppCompatActivity {

    private Info list;

    TextView tvTitle;
    TextView tvSubTitle;
    TextView tvContent;

    Button btnWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balloon);

        tvTitle = findViewById(R.id.tvTitle);
        tvSubTitle = findViewById(R.id.tvSubTitle);
        tvContent = findViewById(R.id.tvContent);

        list = (Info) getIntent().getSerializableExtra("List");

        tvTitle.setText(list.getTitle());
        tvSubTitle.setText(list.getSubtitle());
        tvContent.setText(list.getContent());
    }
}
