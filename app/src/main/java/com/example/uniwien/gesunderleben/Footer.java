package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.uniwien.gesunderleben.mainFunctions.BurnedCalorieCalculatorImpl;
import com.example.uniwien.gesunderleben.mainFunctions.CalorieCounterImpl;
import com.example.uniwien.gesunderleben.mainFunctions.DailyCalorieRequirementImpl;

/**
 * Footer menu
 */

public class Footer extends Fragment implements View.OnClickListener {

    ImageButton btnKalorienverbrauchr;
    ImageButton btnTagesbedarf;
    ImageButton btnKalorienzaehler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.footer, container, false);

        btnKalorienverbrauchr = (ImageButton)view.findViewById(R.id.imageButtonKalorienverbrauch);
        btnTagesbedarf = (ImageButton)view.findViewById(R.id.imageButtonTagesbedarf);
        btnKalorienzaehler = (ImageButton)view.findViewById(R.id.imageButtonKalorienzaehler);

        btnKalorienverbrauchr.setOnClickListener(this);
        btnTagesbedarf.setOnClickListener(this);
        btnKalorienzaehler.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonKalorienverbrauch: {
                Intent intent = new Intent(getActivity(), BurnedCalorieCalculatorImpl.class);
                startActivity(intent);
                break;
            }

            case R.id.imageButtonTagesbedarf: {
                Intent intent = new Intent(getActivity(), DailyCalorieRequirementImpl.class);
                startActivity(intent);
                break;
            }

            case R.id.imageButtonKalorienzaehler: {
                Intent intent = new Intent(getActivity(), CalorieCounterImpl.class);
                startActivity(intent);
                break;
            }
        }
    }
}
