package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UserData3 extends AppCompatActivity implements View.OnClickListener  {

    private EditText weight;
    private EditText size;
    private boolean selected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data3);

        weight = (EditText) findViewById(R.id.editTextWeight);
        size = (EditText) findViewById(R.id.editTextSize);

        //set possible values
        Object objVal =  User.user.getActualParam(ParameterEnum.Gewicht);
        selected = (objVal != null);

        if ((objVal != null))
            weight.setText(String.valueOf(objVal));

        objVal =  User.user.getActualParam(ParameterEnum.Koerpergroesse);
        selected = (selected || objVal != null);
        if ((objVal != null))
            size.setText(String.valueOf(objVal));

        if (selected)
            ((RadioGroup) findViewById(R.id.radioGroupFA)).check(R.id.radioButtonY);
        else
            ((RadioGroup) findViewById(R.id.radioGroupFA)).check(R.id.radioButtonN);

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
        if ( selected ) {
            weight.setVisibility(View.VISIBLE);
            size.setVisibility(View.VISIBLE);
            weight.requestFocus();
        }
        else {
            weight.setVisibility(View.INVISIBLE);
            size.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioButtonY: {
                selected = true;
                updateControls();
                break;
            }

            case R.id.radioButtonN: {
                selected = false;
                updateControls();
                break;
            }

            case R.id.buttonBack: {
                this.finish();
                break;
            }

            case R.id.buttonOK: {
                if ( selected && (weight.getText().length() == 0 && size.getText().length() == 0) ) {
                    Toast.makeText(getApplicationContext(), R.string.datemustbefilled, Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( selected ) {
                    if (weight.getText().length() != 0)
                        User.user.setActualParam(ParameterEnum.Gewicht, Integer.parseInt(weight.getText().toString()) );
                    if (size.getText().length() != 0)
                        User.user.setActualParam(ParameterEnum.Koerpergroesse, Integer.parseInt(size.getText().toString()) );

                    try {
                        User.saveUser();
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (User.user.getRegistred()) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    this.finish();
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(this, UserData4.class);
                    startActivity(intent);
                }
            }
        }
    }
}
