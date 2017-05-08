package com.example.uniwien.gesunderleben;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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

        //btnKalorienverbrauchr = (ImageButton)view.findViewById(R.id.imageButtonKalorienverbrauch);
        //btnTagesbedarf = (ImageButton)view.findViewById(R.id.imageButtonTagesbedarf);
        //btnKalorienzaehler = (ImageButton)view.findViewById(R.id.imageButtonKalorienzaehler);

        //btnKalorienverbrauchr.setOnClickListener(this);
        //btnTagesbedarf.setOnClickListener(this);
        //btnKalorienzaehler.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButtonKalorienverbrauch: {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.imageButtonTagesbedarf: {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.imageButtonKalorienzaehler: {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
