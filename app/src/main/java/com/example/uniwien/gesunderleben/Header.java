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
import android.widget.Toast;

/**
 * Header menu
 */

public class Header extends Fragment implements View.OnClickListener {

    Button btnReset;
    Button btnMain;
    Button btnAkt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.header, container, false);

        btnReset = (Button)view.findViewById(R.id.buttonReset);
        btnMain = (Button)view.findViewById(R.id.buttonMain);
        btnAkt = (Button)view.findViewById(R.id.buttonAkt);

        btnReset.setOnClickListener(this);
        btnMain.setOnClickListener(this);
        btnAkt.setOnClickListener(this);

        return view;
    }

    public void setAktive(int einszweidrei) {
        switch (einszweidrei) {
            case 1: { btnReset.setBackgroundColor(MyApplication.getSelectedtColor()); break; }
            case 2: { btnMain.setBackgroundColor(MyApplication.getSelectedtColor()); break; }
            case 3: { btnAkt.setBackgroundColor(MyApplication.getSelectedtColor()); break; }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonReset: {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                try {
                                    User.user.resetData();
                                    User.saveUser();
                                    Intent intent = new Intent(getActivity(), UserData1.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                catch (IllegalArgumentException e) {
                                    Toast.makeText(MyApplication.getAppContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
                builder.setMessage(R.string.do_you_want_to_reset).setPositiveButton(R.string.yes, dialogClickListener).setNegativeButton(R.string.no, dialogClickListener).show();
                break;
            }

            case R.id.buttonMain: {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                getActivity().finish();
                startActivity(intent);
                break;
            }

            case R.id.buttonAkt: {
                Intent intent = new Intent(getActivity(), UserData1.class);
                startActivity(intent);
                break;
            }
        }
    }
}
