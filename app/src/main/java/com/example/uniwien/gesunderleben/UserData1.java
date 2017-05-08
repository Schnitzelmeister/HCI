package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UserData1 extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Drawable defaultColor;
    int selectedColor;

    int koerperlicheVerfassung = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data1);

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
        selectedColor  = btn1.getLinkTextColors().getDefaultColor();

        try {
            koerperlicheVerfassung = (int)User.user.getActualParam(ParameterEnum.KoerperlicheVerfassung);
        }
        catch (Exception e) {
            koerperlicheVerfassung = 0;
        }

        //hide Header
        if (!User.user.getRegistred()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Header header = (Header)getSupportFragmentManager().findFragmentById(R.id.headerFragment);
            fragmentTransaction.hide(header);
            Footer footer = (Footer)getSupportFragmentManager().findFragmentById(R.id.footerFragment);
            fragmentTransaction.hide(footer);
            fragmentTransaction.commit();
        }

        updateControls();
    }

    private void updateControls() {
        btn1.setBackground(defaultColor);
        btn2.setBackground(defaultColor);
        btn3.setBackground(defaultColor);
        btn4.setBackground(defaultColor);
        btn5.setBackground(defaultColor);

        switch (koerperlicheVerfassung) {
            case 1: { btn1.setBackgroundColor(selectedColor); break; }
            case 2: { btn2.setBackgroundColor(selectedColor); break; }
            case 3: { btn3.setBackgroundColor(selectedColor); break; }
            case 4: { btn4.setBackgroundColor(selectedColor); break; }
            case 5: { btn5.setBackgroundColor(selectedColor); break; }
        }
    }

    @Override
    public void onClick(View v) {

        //Toast.makeText(getApplicationContext(), "selectedtColor="+selectedColor, Toast.LENGTH_SHORT).show();

        switch (v.getId()) {
            case R.id.buttonState1: {
                koerperlicheVerfassung = 1;
                updateControls();
                break;
            }
            case R.id.buttonState2: {
                koerperlicheVerfassung = 2;
                updateControls();
                break;
            }
            case R.id.buttonState3: {
                koerperlicheVerfassung = 3;
                updateControls();
                break;
            }
            case R.id.buttonState4: {
                koerperlicheVerfassung = 4;
                updateControls();
                break;
            }
            case R.id.buttonState5: {
                koerperlicheVerfassung = 5;
                updateControls();
                break;
            }

            case R.id.buttonOK: {
                if ( koerperlicheVerfassung == 0 ) {
                    Toast.makeText(getApplicationContext(), R.string.datemustbefilled, Toast.LENGTH_SHORT).show();
                    return;
                }

                User.user.setActualParam(ParameterEnum.KoerperlicheVerfassung, koerperlicheVerfassung );
                try {
                    User.saveUser();
                }
                catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(this, UserData2  .class);
                startActivity(intent);
                break;

            }

            case R.id.buttonBack: {
                this.finish();
                break;
            }
        }
    }
}
