package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UserData2 extends AppCompatActivity implements View.OnClickListener  {

    private EditText anteil;
    private boolean selected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data2);

        anteil = (EditText) findViewById(R.id.editTextAnteil);
        anteil.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 100)});


        //set possible values
        Object objVal =  User.user.getActualParam(ParameterEnum.KoerperAnteil);
        selected = (objVal != null);
        if (selected) {
            anteil.setText(String.valueOf(objVal));
            ((RadioGroup) findViewById(R.id.radioGroupFA)).check(R.id.radioButtonY);
        }
        else {
            ((RadioGroup) findViewById(R.id.radioGroupFA)).check(R.id.radioButtonN);
        }


        //hide Header
        if (!User.user.getRegistered()) {
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
            anteil.setVisibility(View.VISIBLE);
            anteil.requestFocus();
        }
        else {
            anteil.setVisibility(View.INVISIBLE);
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
                if ( selected && (anteil.getText().length() == 0) ) {
                    Toast.makeText(getApplicationContext(), R.string.datemustbefilled, Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( selected ) {
                    User.user.setActualParam(ParameterEnum.KoerperAnteil, Integer.parseInt(anteil.getText().toString()) );
                    try {
                        User.saveUser();
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Intent intent = new Intent(this, UserData3.class);
                startActivity(intent);
            }
        }
    }
}
