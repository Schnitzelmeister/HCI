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


public class Fitness extends Fragment implements View.OnClickListener {

    private Spinner choose_type_of_sport_f;
    private EditText workout_time_f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fitness, container, false);

        workout_time_f = (EditText)rootView.findViewById(R.id.editTextSportAdd);
        choose_type_of_sport_f = (Spinner) rootView.findViewById(R.id.spinnerSportAdd);

        ((Button)rootView.findViewById(R.id.buttonBack)).setOnClickListener(this);
        ((Button)rootView.findViewById(R.id.buttonAddSport)).setOnClickListener(this);

        spinnerFilledBySports();
        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.buttonBack:
                getActivity().finish();
                break;

            case R.id.buttonAddSport:
                //getActivity().finish();
                workout_time_f.setText("");
                Toast.makeText(MyApplication.getAppContext(), "Done", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * Hier wird der Spinner mit Sportarten befüllt.
     */
    public void spinnerFilledBySports(){

        Database database = new Database();
        ArrayList<String> sports = database.getSportName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item,sports);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        choose_type_of_sport_f.setAdapter(adapter);
    }

}
