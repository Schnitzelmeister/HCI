package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Header header = (Header)getSupportFragmentManager().findFragmentById(R.id.headerFragment);
        header.setAktive(2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDailyPlan: {
                //Intent intent = new Intent(this, UserData4.class);
                //startActivity(intent);
                break;
            }
            case R.id.buttonProgress: {
                Intent intent = new Intent(this, FortschrittActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.buttonTimePlan: {
                Intent intent = new Intent(this, ZeitplanActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.buttonNewPlan: {
                Intent intent = new Intent(this, UserData4.class);
                startActivity(intent);
                break;
            }

        }
    }
}
