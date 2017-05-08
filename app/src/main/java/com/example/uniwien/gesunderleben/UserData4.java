package com.example.uniwien.gesunderleben;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.support.annotation.IntegerRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class UserData4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner foodplan;
    private TextView fooddescr;
    private Spinner fitnessplan;
    private TextView fitnessdescr;
    private EditText desiredWeight;

    Map<String, String> foodplans;
    Map<String, String> fitnessplans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data4);

        foodplan = (Spinner) findViewById(R.id.spinnerFoodplan);
        fooddescr = (TextView) findViewById(R.id.addressFoodplan);
        fitnessplan = (Spinner) findViewById(R.id.spinnerFitnessplan);
        fitnessdescr = (TextView) findViewById(R.id.addressFitnessplan);
        desiredWeight = (EditText) findViewById(R.id.editTextDesiredWeight);

        foodplan.setOnItemSelectedListener(this);
        fitnessplan.setOnItemSelectedListener(this);

        //init Plans
        try {
            foodplans = convertNodesFromXml( R.xml.foodplans );
            fitnessplans = convertNodesFromXml( R.xml.fitnessplans );
    }
        catch(Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        //set Plans
        ArrayAdapter<String> adapterFood = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, foodplans.keySet().toArray(new String[foodplans.size()]) );
        adapterFood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodplan.setAdapter(adapterFood);

        ArrayAdapter<String> adapterFitness = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fitnessplans.keySet().toArray(new String[fitnessplans.size()]) );
        adapterFitness.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fitnessplan.setAdapter(adapterFitness);

        //set possible values
        Object objVal = User.user.getActualParam(ParameterEnum.Wuenschgewicht);
        if ((objVal != null))
            desiredWeight.setText(String.valueOf(objVal));

        //hide Header
        if (!User.user.getRegistred()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Header header = (Header)getSupportFragmentManager().findFragmentById(R.id.headerFragment);
            fragmentTransaction.hide(header);
            Footer footer = (Footer)getSupportFragmentManager().findFragmentById(R.id.footerFragment);
            fragmentTransaction.hide(footer);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack: {
                this.finish();
                break;
            }

            case R.id.buttonOK: {
                if (( desiredWeight.getText().length() == 0 )||( foodplan.getSelectedItem().toString().length() == 0 )||( fitnessplan.getSelectedItem().toString().length() == 0 ) ) {
                    Toast.makeText(getApplicationContext(), R.string.datemustbefilled, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (desiredWeight.getText().length() != 0)
                    User.user.setActualParam( ParameterEnum.Wuenschgewicht, Integer.parseInt( desiredWeight.getText().toString() ) );
                if (foodplan.getSelectedItem().toString().length() != 0)
                    User.user.setActualParam( ParameterEnum.Nahrungsplan, foodplan.getSelectedItem().toString() );
                if (fitnessplan.getSelectedItem().toString().length() != 0)
                    User.user.setActualParam( ParameterEnum.Fitnessplan, fitnessplan.getSelectedItem().toString() );

                //set registred flag
                if (!User.user.getRegistred())
                    User.user.setRegistred();

                try {
                    User.saveUser();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                this.finish();
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case  R.id.spinnerFoodplan: {
                ((TextView) findViewById(R.id.addressFoodplan)).setText( foodplans.get(foodplan.getSelectedItem().toString()) );
                break;
            }

            case  R.id.spinnerFitnessplan: {
                ((TextView) findViewById(R.id.addressFitnessplan)).setText( fitnessplans.get(fitnessplan.getSelectedItem().toString()) );
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView v) {
    }

    //create Map from XML-Plans
    private static Map<String, String> convertNodesFromXml(int id) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        XmlResourceParser xpp = MyApplication.getAppContext().getResources().getXml(id);
        // check state
        int eventType = xpp.getEventType();
        String tmp = null;
        String key = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            // instead of the following if/else if lines
            // you should custom parse your xml
            if(eventType == XmlPullParser.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == XmlPullParser.START_TAG) {
                System.out.println("Start tag "+xpp.getName());
            } else if(eventType == XmlPullParser.END_TAG) {
                System.out.println("End tag "+xpp.getName());
                if (xpp.getName().equals("name")) {
                    key = tmp;
                }
                else if (xpp.getName().equals("description")) {
                    map.put(key, tmp);
                }
            } else if(eventType == XmlPullParser.TEXT) {
                tmp = xpp.getText();
                System.out.println("Text "+xpp.getText());
            }
            eventType = xpp.next();
        }
        xpp.close();

        return map;
    }
}
