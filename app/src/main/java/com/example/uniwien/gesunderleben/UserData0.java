package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UserData0 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data0);

        //filter age 1..99
        ((EditText) findViewById(R.id.editTextAlter)).setFilters(new InputFilter[]{ new InputFilterMinMax(1, 99)});

        if ( User.user.getAge() > 0 )
            ((EditText) findViewById(R.id.editTextAlter)).setText(String.valueOf( User.user.getAge() ));

        if (User.user.getGender())
            ((RadioGroup) findViewById(R.id.radioGroupG)).check(R.id.radioButtonM);
        else
            ((RadioGroup) findViewById(R.id.radioGroupG)).check(R.id.radioButtonW);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonOK: {
                if ( ((EditText) findViewById(R.id.editTextAlter)).getText().length() == 0 ) {
                    Toast.makeText(getApplicationContext(), R.string.datemustbefilled, Toast.LENGTH_SHORT).show();
                    return;
                }

                User.user.setGender( ((RadioButton) findViewById(R.id.radioButtonM)).isSelected() );
                User.user.setAge(Integer.parseInt( ((EditText) findViewById(R.id.editTextAlter)).getText().toString()) );
//                User.user.setRegistred();
                try {
                    User.saveUser();
                }
                catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(this, UserData1.class);
                startActivity(intent);
                break;

            }
        }
    }
}
