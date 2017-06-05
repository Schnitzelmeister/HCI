package com.example.uniwien.gesunderleben;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.uniwien.gesunderleben.secondaryfunctions.Database;

import java.util.ArrayList;


public class Food extends Fragment implements View.OnClickListener {

    private Spinner choose_type_of_food_f;
    private EditText quanitity_food_consumption_f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_food, container, false);

        quanitity_food_consumption_f = (EditText)rootView.findViewById(R.id.editTextFoodAdd);
        choose_type_of_food_f = (Spinner) rootView.findViewById(R.id.spinnerFood);

        ((Button)rootView.findViewById(R.id.buttonBack)).setOnClickListener(this);
        ((Button)rootView.findViewById(R.id.buttonAddFood)).setOnClickListener(this);

        spinnerFilledByFood();
        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.buttonBack:
                getActivity().finish();
                break;

            case R.id.buttonAddFood:
                //getActivity().finish();
                quanitity_food_consumption_f.setText("");
                Toast.makeText(MyApplication.getAppContext(), "Done", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * Hier wird der Spinner mit Nahrungsmitteln befüllt.
     */
    public void spinnerFilledByFood() {

        Database database = new Database();
        ArrayList<String> food = database.getFoodName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item,food);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        choose_type_of_food_f.setAdapter(adapter);
    }

}
