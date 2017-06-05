package com.example.uniwien.gesunderleben.secondaryfunctions;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uniwien.gesunderleben.ParameterEnum;
import com.example.uniwien.gesunderleben.R;
import com.example.uniwien.gesunderleben.User;

import java.util.ArrayList;

/**
 * Created by Raf on 07.05.2017.
 */

public class BurnedCalorieCalculatorImpl extends AppCompatActivity implements View.OnClickListener {

    /**
     * Variablendeklarierung
     */
    public TextView header_burned_calorie_calculator_f, result_field_calculator_f;
    public Button confirmation_button_calculator_f, back_button_calculator_f;
    public Spinner choose_type_of_sport_f;
    public EditText workout_time_f;

    /**
     * Variableninitialiserung
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.burned_calorie_calculator);

        header_burned_calorie_calculator_f = (TextView) findViewById(R.id.header_burned_calorie_calculator);
        header_burned_calorie_calculator_f.setText("KALORIENVERBRAUCH");

        result_field_calculator_f = (TextView) findViewById(R.id.result_field_calculator);
        result_field_calculator_f.setText("Bitte Sporart und Dauer angeben!");

        confirmation_button_calculator_f = (Button) findViewById(R.id.confirmation_button_calculator);
        confirmation_button_calculator_f.setText("OK");

        back_button_calculator_f = (Button) findViewById(R.id.back_button_calculator);
        back_button_calculator_f.setText("ZURÜCK");

        choose_type_of_sport_f = (Spinner) findViewById(R.id.choose_type_of_sport);

        workout_time_f = (EditText) findViewById(R.id.workout_time);
        workout_time_f.setHint("Minuten");

        confirmation_button_calculator_f.setOnClickListener(this);
        back_button_calculator_f.setOnClickListener(this);

        spinnerFilledBySports();
    }

    /**
     * Wird Ein Button gedrückt so wird hier entsprechend reagiert.
     * @param view
     */
    public void onClick(View view){

        switch (view.getId()){

            case R.id.back_button_calculator:
                this.finish();
                break;

            case R.id.confirmation_button_calculator:
                burnedCalorie();
                break;
        }
    }

    /**
     * Hier wird der Spinner mit Sportarten befüllt.
     */
    public void spinnerFilledBySports(){

        Database database = new Database();
        ArrayList<String> sports = database.getSportName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,sports);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        choose_type_of_sport_f.setAdapter(adapter);
    }

    /**
     * Hier werden die verbrannten Kalorien berechnet.
     */
    public void burnedCalorie() {

        Double sport_quotient;
        Integer weight = (int) User.user.getActualParam(ParameterEnum.Gewicht);

        Double workout_time = Double.parseDouble(workout_time_f.getText().toString());
        String choosenSport = choose_type_of_sport_f.getSelectedItem().toString();

        Database database = new Database();
        ArrayList<String> sport = database.getSports();

        for (int i = 0; i < sport.size(); ++i) {
            if (sport.get(i).equals(choosenSport)) {
                sport_quotient = Double.parseDouble(sport.get(i+1));
                result_field_calculator_f.setText("Sie haben " + ((int) (weight * sport_quotient * workout_time)) + " Kalorien verbraucht!");
            }
        }
    }

}

