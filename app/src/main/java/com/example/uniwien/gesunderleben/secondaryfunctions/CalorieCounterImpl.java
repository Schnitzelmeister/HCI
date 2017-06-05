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

import com.example.uniwien.gesunderleben.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Raf on 06.05.2017.
 */

public class CalorieCounterImpl extends AppCompatActivity implements View.OnClickListener{

    /**
     * Variablendeklarierung
     */
    public TextView header_calorie_counter_f,
            calorie_fraction_number_f, calorie_fraction_description_f,
            protein_fraction_number_f, protein_fraction_description_f,
            carb_fraction_number_f, carb_fraction_description_f,
            fat_fraction_number_f, fat_fraction_description_f;
    public Button confirmation_button_counter_f, back_button_counter_F;
    public Spinner choose_type_of_food_f;
    public EditText quanitity_food_consumption_f;

    /**
     * Variableninitialisierung
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorie_counter);

        confirmation_button_counter_f = (Button) findViewById(R.id.confirmation_button_counter);
        back_button_counter_F = (Button) findViewById(R.id.back_button_counter);

        confirmation_button_counter_f.setOnClickListener(this);
        back_button_counter_F.setOnClickListener(this);

        header_calorie_counter_f = (TextView) findViewById(R.id.header_calorie_counter);
        header_calorie_counter_f.setText("KALORIENZÄHLER ");

        calorie_fraction_number_f =  (TextView) findViewById(R.id.calorie_fraction_number);
        calorie_fraction_number_f.setText("");

        calorie_fraction_description_f = (TextView) findViewById(R.id.calorie_fraction_description);
        calorie_fraction_description_f.setText("Kalorien (kcal)");

        protein_fraction_number_f = (TextView) findViewById(R.id.protein_fraction_number);
        protein_fraction_number_f.setText("");

        protein_fraction_description_f = (TextView) findViewById(R.id.protein_fraction_description);
        protein_fraction_description_f.setText("Einweiß (g)");

        carb_fraction_number_f = (TextView) findViewById(R.id.carb_fraction_number);
        carb_fraction_number_f.setText("");

        carb_fraction_description_f = (TextView) findViewById((R.id.carb_fraction_description));
        carb_fraction_description_f.setText("Kohlenhydrate (g)");

        fat_fraction_number_f = (TextView) findViewById(R.id.fat_fraction_number);
        fat_fraction_number_f.setText("");

        fat_fraction_description_f = (TextView) findViewById((R.id.fat_fraction_description));
        fat_fraction_description_f.setText("Fett (g)");

        confirmation_button_counter_f = (Button) findViewById(R.id.confirmation_button_counter);
        confirmation_button_counter_f.setText("OK");

        back_button_counter_F = (Button) findViewById(R.id.back_button_counter);
        back_button_counter_F.setText("ZURÜCK");

        quanitity_food_consumption_f = (EditText) findViewById(R.id.quanitity_food_consumption);
        //quanitity_food_consumption_f.setHint("Gramm");
        //quanitity_food_consumption_f.setOnClickListener(this);

        choose_type_of_food_f = (Spinner) findViewById(R.id.choose_type_of_food);

        spinnerFilledByFood();
    }

    /**
     * Wird Ein Button gedrückt so wird hier entsprechend reagiert.
     * @param view
     */
    public void onClick(View view){

        switch (view.getId()){

            case R.id.back_button_counter:
                this.finish();
                break;

            case R.id.confirmation_button_counter:
                calorieCalculation();
                break;
        }
    }

    /**
     * Hier wird der Spinner mit Nahrungsmitteln befüllt.
     */
    public void spinnerFilledByFood() {

        Database database = new Database();
        ArrayList<String> food = database.getFoodName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,food);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        choose_type_of_food_f.setAdapter(adapter);
    }

    /**
     * Hier werden die Kalorien eine Nahrungsmittels berechnet.
     */
    public void calorieCalculation(){

        DecimalFormat x = new DecimalFormat("0.0");

        Double amountOfFood = Double.parseDouble(quanitity_food_consumption_f.getText().toString());
        String choosenFood = choose_type_of_food_f.getSelectedItem().toString();

        Database database = new Database();
        ArrayList<String> food = database.getFood();

        for (int i=0;i<food.size();++i){
            if (food.get(i).equals(choosenFood)){
                fat_fraction_number_f.setText(String.valueOf(x.format(amountOfFood*(Double.parseDouble(food.get(i+1)))/100)));
                protein_fraction_number_f.setText(String.valueOf(x.format(amountOfFood*(Double.parseDouble(food.get(i+2)))/100)));
                carb_fraction_number_f.setText(String.valueOf(x.format(amountOfFood*(Double.parseDouble(food.get(i+3)))/100)));
                calorie_fraction_number_f.setText(String.valueOf((int) (amountOfFood*(Double.parseDouble(food.get(i+4)))/100)));
            }
        }
    }
}
