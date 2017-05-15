package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ZeitplanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zeitplan_view);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.backButton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
