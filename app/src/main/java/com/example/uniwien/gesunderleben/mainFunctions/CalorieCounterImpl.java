package com.example.uniwien.gesunderleben.mainFunctions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uniwien.gesunderleben.R;

/**
 * Created by Raf on 06.05.2017.
 */

public class CalorieCounterImpl extends AppCompatActivity implements View.OnClickListener{

    public TextView header_calorie_counter_f,
            calorie_fraction_number_f, calorie_fraction_description_f,
            protein_fraction_number_f, protein_fraction_description_f,
            carb_fraction_number_f, carb_fraction_description_f,
            fat_fraction_number_f, fat_fraction_description_f;
    public Button confirmation_button_counter_f, back_button_counter_F;
    public Spinner choose_type_of_food_f;


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

        choose_type_of_food_f = (Spinner) findViewById(R.id.choose_type_of_food);

       SpinnerFilledByFood();

    }

    public void onClick(View view){
        ChoosenFood();

    }

    public void SpinnerFilledByFood() {

        String[] food = {"Banane","Apfel","Orange"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,food);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        choose_type_of_food_f.setAdapter(adapter);

    }

    public void ChoosenFood(){

    }

}
