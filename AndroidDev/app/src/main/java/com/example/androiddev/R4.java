package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class R4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r4);

        TextView textView = findViewById(R.id.descriptionTextView);
        textView.setText(addDescription());
    }

    private String addDescription() {

        String text = "";
        int patientSSN = 100;
        String myIP = "192.168.1.5";
        String url= "http://"+ myIP +"/r4.php?patientSSN=" + patientSSN;

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            text = okHttpHandler.addDescription(url);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }
}