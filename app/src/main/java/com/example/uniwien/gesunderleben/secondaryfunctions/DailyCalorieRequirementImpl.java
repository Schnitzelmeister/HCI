package com.example.uniwien.gesunderleben.secondaryfunctions;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uniwien.gesunderleben.ParameterEnum;
import com.example.uniwien.gesunderleben.R;
import com.example.uniwien.gesunderleben.User;

import java.util.ArrayList;

/**
 * Created by Raf on 07.05.2017.
 */

public class DailyCalorieRequirementImpl extends AppCompatActivity implements View.OnClickListener {

    /**
     * Variablendeklarierung
     */
    public TextView header_daily_calorie_requirement_f, result_field_rest_energy_descrption_f,
            result_field_rest_energy_number_f, result_field_total_energy_f, result_field_total_energy_number_f;
    public Button back_button_calorie_requirement_f;
    public Spinner choose_grade_of_activity_f;

    /**
     * Variableninitialisierung
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_calorie_requirement);

        header_daily_calorie_requirement_f = (TextView) findViewById(R.id.header_daily_calorie_requirement);
        header_daily_calorie_requirement_f.setText("TAGESBEDARF");

        result_field_rest_energy_descrption_f = (TextView) findViewById(R.id.result_field_rest_energy_description);
        result_field_rest_energy_descrption_f.setText("Ihr Ruheenergieverbrauch beträgt: ");

        result_field_rest_energy_number_f = (TextView) findViewById(R.id.result_field_rest_energy_number);
        result_field_total_energy_number_f = (TextView) findViewById(R.id.result_field_total_energy_number);

        result_field_total_energy_f = (TextView) findViewById(R.id.result_field_total_energy);
        result_field_total_energy_f.setText("Ihr Gesamtenergie-verbrauch beträgt: ");

        back_button_calorie_requirement_f = (Button) findViewById(R.id.back_button_calorie_requirement);
        back_button_calorie_requirement_f.setText("ZURÜCK");

        choose_grade_of_activity_f = (Spinner) findViewById(R.id.choose_grade_of_activity);

        back_button_calorie_requirement_f.setOnClickListener(this);

        calculateEnergyAtBeginning();
        spinnerFilledByActvityGrade();

        choose_grade_of_activity_f.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculateTotalEnergy(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Wird Ein Button gedrückt so wird hier entsprechend reagiert.
     * @param view
     */
    public void onClick(View view){

        switch (view.getId()) {

            case R.id.back_button_calorie_requirement:
                this.finish();
                break;

        }
    }

    /**
     * Hier wird der Spinner mit den Aktivitätsgraden befüllt.
     */
    public void spinnerFilledByActvityGrade(){
        Database database = new Database();
        ArrayList<String> activity_grade = database.getActivityGradeDescription();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,activity_grade);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        choose_grade_of_activity_f.setAdapter(adapter);
    }

    /**
     * Hier wird der Ruheenergieverbrauch berechnet.
     */
    public void calculateEnergyAtBeginning(){

        Integer age = User.user.getAge();
        Integer weight = (int) User.user.getActualParam(ParameterEnum.Gewicht);
        Boolean gender = User.user.getGender();

        Integer restEnergy;

        if (gender){
            restEnergy = ((int) (((0.047*weight)+((1.009-(0.01452*age))+3.21))*239));
        }else{
            restEnergy = ((int) (((0.047*weight)+((-(0.01452*age))+3.21))*239));
        }

        result_field_rest_energy_number_f.setText(String.valueOf(Math.round(restEnergy*0.95))+" kcal/Tag");
        result_field_total_energy_number_f.setText(String.valueOf(Math.round(restEnergy*0.95))+" kcal/Tag");

    }

    /**
     * Hier wird der Gesamtenergieverbrauch berechnet.
     * @param item
     */
    public void calculateTotalEnergy(String item){

        Integer age = User.user.getAge();
        Integer weight = (int) User.user.getActualParam(ParameterEnum.Gewicht);
        Boolean gender = User.user.getGender();

        Database database = new Database();
        ArrayList<String> activityGrade = database.getActivityGrade();
        Double grade=0.0;

        for(int i=0;i<activityGrade.size();++i){
            if(activityGrade.get(i).equals(item)){
                grade = Double.parseDouble(activityGrade.get(i+1));
            }
        }

        if (gender){
            result_field_total_energy_number_f.setText(String.valueOf((int) ((((0.047*weight)+((1.009-(0.01452*age))+3.21))*239)*grade))+ " kcal/Tag");
        }else{
            result_field_total_energy_number_f.setText(String.valueOf((int) ((((0.047*weight)+((-(0.01452*age))+3.21))*239)*grade))+ " kcal/Tag");
        }
    }

}
