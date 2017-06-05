package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FortschrittActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortschritt_view);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.backButton1){
            this.finish();
        }
    }
}
