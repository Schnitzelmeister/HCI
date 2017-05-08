package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Launcher
 */

public class ActivityLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent;
        if ( User.user.getRegistred() ) {
            intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        } else {
            intent = new Intent(this, UserData0.class);
        }
        finish();
        startActivity(intent);

    }
}
