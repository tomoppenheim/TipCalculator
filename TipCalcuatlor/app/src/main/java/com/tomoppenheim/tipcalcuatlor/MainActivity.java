package com.tomoppenheim.tipcalcuatlor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {

    private EditText checkAmount;
    private EditText tipPercent;
    private EditText numberPeople;
    private TextView TotalBillCalcView;
    private TextView TotalTipCalcView;
    private TextView TotalPerPersonCalc;
    private TextView TipPerPersonCalc;
    private Button tipButton;
    private Button webButton;
    private Button dialButton;
    private Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAmount = (EditText) findViewById(R.id.checkAmount);
        tipPercent = (EditText) findViewById(R.id.tipPercent);
        numberPeople = (EditText) findViewById(R.id.numberPeople);

        TotalBillCalcView = (TextView) findViewById(R.id.TotalBillCalcView);
        TotalTipCalcView = (TextView) findViewById(R.id.TotalTipCalcView);
        TotalPerPersonCalc = (TextView) findViewById(R.id.TotalPerPersonCalc);
        TipPerPersonCalc = (TextView) findViewById(R.id.TipPerPersonCalc);

        tipButton = (Button) findViewById(R.id.tipButton);
        tipButton.setOnClickListener(this);

        webButton = (Button) findViewById(R.id.webButton);
        webButton.setOnClickListener(this);

        dialButton = (Button) findViewById(R.id.dialButton);
        dialButton.setOnClickListener(this);

        mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(this);
    }
    public void onClick(View v) {

        switch(v.getId()) {

            //Tip Button Case
            case R.id.tipButton:
            String mealPrice = checkAmount.getText().toString();
            double checkAmount = Float.parseFloat(mealPrice);
            String tipNumber = tipPercent.getText().toString();
            double tipFloat = Float.parseFloat(tipNumber) + 1;
            String numPeople = numberPeople.getText().toString();
            double numberOfPeople = Float.parseFloat(numPeople);

            // Adding tip to meal, setting total bill field.
            double fullMealPrice = checkAmount * tipFloat;
            String fmp = String.format("$%.2f", fullMealPrice);
            TotalBillCalcView.setText(fmp);

            // Getting only the tip
            double totalTip = fullMealPrice - checkAmount;
            String entireTip = String.format("$%.2f", totalTip);
            TotalTipCalcView.setText(entireTip);

            //Getting total per person
            double totalPerPerson = fullMealPrice / numberOfPeople;
            String totalPP = String.format("$%.2f", totalPerPerson);
            TotalPerPersonCalc.setText(totalPP);

            //Getting tip per person
            double tipPerPerson = totalTip / numberOfPeople;
            String tipPP = String.format("$%.2f", tipPerPerson);
            TipPerPersonCalc.setText(tipPP);
            break;

            //Dial Button Case
            case R.id.dialButton:
                Uri uri1 = Uri.parse("tel:7818912000");
                Intent i1 = new Intent(Intent.ACTION_DIAL,uri1);
                startActivity(i1);
                break;

            //Web Button Case
            case R.id.webButton:
                Intent i3 = new Intent(this,WebLookup.class);
                startActivity(i3);
                break;

            //Map Button Case
            case R.id.mapButton:
                Uri uri2 = Uri.parse("geo:0,0?q=175+forest+street+waltham+ma");
                Intent i2 = new Intent(Intent.ACTION_VIEW,uri2);
                if (i2.resolveActivity(getPackageManager()) != null){
                    startActivity(i2);
                }
                break;
        }
    }
}






