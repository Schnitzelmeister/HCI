package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UserData0 extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Drawable defaultColor;
    int selectedtColor;

    int koerperlicheVerfassung = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data0);

        btn1 = (Button) findViewById(R.id.buttonState1);
        btn2 = (Button) findViewById(R.id.buttonState2);
        btn3 = (Button) findViewById(R.id.buttonState3);
        btn4 = (Button) findViewById(R.id.buttonState4);
        btn5 = (Button) findViewById(R.id.buttonState5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        defaultColor = btn1.getBackground();
        selectedtColor  = ((RadioButton) findViewById(R.id.radioButtonM)).getLinkTextColors().getDefaultColor();

        try {
            ((EditText) findViewById(R.id.editTextAlter)).setText((String)User.user.getActualParam(ParameterEnum.Alter));
            if (User.user.getGender())
                ((RadioGroup) findViewById(R.id.radioGroupG)).check(R.id.radioButtonM);
            else
                ((RadioGroup) findViewById(R.id.radioGroupG)).check(R.id.radioButtonW);

            koerperlicheVerfassung = (int)User.user.getActualParam(ParameterEnum.KoerperlicheVerfassung);
        }
        catch (Exception e) {
            //no default data
        }

        updateButtonColors();
    }

    private void updateButtonColors() {
        btn1.setBackground(defaultColor);
        btn2.setBackground(defaultColor);
        btn3.setBackground(defaultColor);
        btn4.setBackground(defaultColor);
        btn5.setBackground(defaultColor);

        switch (koerperlicheVerfassung) {
            case 1: { btn1.setBackgroundColor(selectedtColor); break; }
            case 2: { btn2.setBackgroundColor(selectedtColor); break; }
            case 3: { btn3.setBackgroundColor(selectedtColor); break; }
            case 4: { btn4.setBackgroundColor(selectedtColor); break; }
            case 5: { btn5.setBackgroundColor(selectedtColor); break; }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonState1: {
                koerperlicheVerfassung = 1;
                updateButtonColors();
                break;
            }
            case R.id.buttonState2: {
                koerperlicheVerfassung = 2;
                updateButtonColors();
                break;
            }
            case R.id.buttonState3: {
                koerperlicheVerfassung = 3;
                updateButtonColors();
                break;
            }
            case R.id.buttonState4: {
                koerperlicheVerfassung = 4;
                updateButtonColors();
                break;
            }
            case R.id.buttonState5: {
                koerperlicheVerfassung = 5;
                updateButtonColors();
                break;
            }

            case R.id.buttonOK: {
                if ( koerperlicheVerfassung == 0 || ((EditText) findViewById(R.id.editTextAlter)).getText().length() == 0 ) {
                    Toast.makeText(getApplicationContext(), R.string.datemustbefilled, Toast.LENGTH_SHORT).show();
                    return;
                }


                User.user.setGender( ((RadioButton) findViewById(R.id.radioButtonM)).isSelected() );
                User.user.setActualParam(ParameterEnum.Alter, Integer.parseInt( ((EditText) findViewById(R.id.editTextAlter)).getText().toString()) );
                User.user.setActualParam(ParameterEnum.KoerperlicheVerfassung, koerperlicheVerfassung );
                User.user.setRegistred(false);
                User.saveUser();

                Intent intent = new Intent(this, UserData1.class);
                //intent.putExtra(CITY, cities.get(text.toLowerCase()));
                //intent.putExtra(FLAG, flags.get((cities.get(text.toLowerCase()).getCountry()).toLowerCase()));
                startActivity(intent);

            }
        }
        //Your Logic
    }
}
