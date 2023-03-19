//package com.example.tiptime;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Switch;
//import android.widget.TextView;
//import android.widget.Toast;
//
///**
// *  This activity allows a user to calculate the tip
// *  based on the quality of service.
// */
//public class MainActivity extends AppCompatActivity {
//
//
//    private EditText costOfServiceText;
//    private Button calcTipButton;
//    private RadioGroup radioGroup;
//    private Switch roundUpSwitch;
//    private TextView tipAmountText;
//    private double tipPercent;
//    private double tipAmountTotal;
//
//    /**
//     *  This method is called when the Activity is created.
//     * @param savedInstanceState
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        costOfServiceText = findViewById(R.id.costOfService);
//        tipAmountText = findViewById(R.id.tipAmount);
//        calcTipButton = findViewById(R.id.calculateButton);
//        roundUpSwitch = findViewById(R.id.switch1);
//        radioGroup = findViewById(R.id.radioGroup);
//
//        calcTipButton.setOnClickListener(new View.OnClickListener() {
//
//            /**
//             *  This methods calculates the tip based on the quality
//             *  which are amazing, good and okay.
//             * @param view
//             */
//            @Override
//            public void onClick(View view) {
//                try
//                {
//                    String costString = costOfServiceText.getText().toString();
//                    int costAmount = Integer.parseInt(costString);
//                    int checkedRadioButton = radioGroup.getCheckedRadioButtonId();
//                    if (checkedRadioButton == R.id.amazingButton)
//                    {
//                        tipPercent = 0.2;
//                    } else if (checkedRadioButton == R.id.goodButton) {
//                        tipPercent = 0.18;
//                    } else if (checkedRadioButton == R.id.okayButton) {
//                        tipPercent = 0.15;
//                    }else {
//                        tipPercent = 0.0;
//                    }
//
//                    tipAmountTotal = costAmount * tipPercent;
//                    if(roundUpSwitch.isChecked())
//                    {
//                        tipAmountTotal = Math.round(tipAmountTotal);
//                    }
//                    tipAmountText.setText("" + tipAmountTotal);
//                }
//                catch(NumberFormatException e)
//                {
//                    Toast.makeText(getApplicationContext(),"Please enter a numeric value!",Toast.LENGTH_SHORT).show();
//                    tipAmountText.setText("0");
//                }
//
//            }
//        });
//    }
//}


package com.example.tiptime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity allows a user to convert a number from and to
 * various units such as ounce, cup and liter.
 */
public class MainActivity extends AppCompatActivity {

    private EditText valueToConvert;
    private TextView valueAfterConvert;
    private Button button;
    private RadioGroup unitGroup;
    private RadioGroup altUnitGroup;
    private double convertFactor;

    /**
     *  This method is called when the Activity is created.
     * @param savedInstanceState
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueToConvert = findViewById(R.id.editTextNumber);
        valueAfterConvert = findViewById(R.id.convertedValue);
        button = findViewById(R.id.convertButton);
        unitGroup = findViewById(R.id.units);
        altUnitGroup = findViewById(R.id.altUnit);


        button.setOnClickListener(new View.OnClickListener() {

            /**
             * This method calls for convert whenever convert button is clicked.
             * @param view
             */
            @Override
            public void onClick(View view) {
                try {
                    convert();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    /**
     *  Converts the numeric value from user to various units accordingly.
     */
    private void convert() {
        try {
            String valueString = valueToConvert.getText().toString();
            double valueAmount = Double.parseDouble(valueString);
            double valueConverted;
            double checkedUnitButton = unitGroup.getCheckedRadioButtonId();
            double checkedAltButton = altUnitGroup.getCheckedRadioButtonId();

            // If the initial unit to be converted is liter.
            if (checkedUnitButton == R.id.literButton) {
                if (checkedAltButton == R.id.altOunceButton) {
                    valueConverted = valueAmount * 33.814;
                    valueAfterConvert.setText("" + valueConverted);

                } else if (checkedAltButton == R.id.altLiterButton) {
                    valueConverted = valueAmount;
                    valueAfterConvert.setText("" + valueConverted);

                } else if (checkedAltButton == R.id.altCupButton) {
                    valueConverted = valueAmount * 4.227;
                    valueAfterConvert.setText("" + valueConverted);


                }
            }
            // If the initial unit to be converted is ounce.
            else if (checkedUnitButton == R.id.ounceButton) {
                if (checkedAltButton == R.id.altOunceButton) {
                    valueAfterConvert.setText("" + valueAmount);
                } else if (checkedAltButton == R.id.altLiterButton) {
                    valueConverted = valueAmount / 33.814;
                    valueAfterConvert.setText("" + valueConverted);
                } else if (checkedAltButton == R.id.altCupButton) {
                    valueConverted = valueAmount / 8;
                    valueAfterConvert.setText("" + valueConverted);
                }
            }
            // If the initial unit to be converted is cup.
            else if (checkedUnitButton == R.id.cupButton) {
                if (checkedAltButton == R.id.altOunceButton) {
                    valueConverted = valueAmount * 8;
                    valueAfterConvert.setText("" + valueConverted);
                } else if (checkedAltButton == R.id.altLiterButton) {
                    valueConverted = valueAmount / 4.227;
                    valueAfterConvert.setText("" + valueConverted);

                } else if (checkedAltButton == R.id.altCupButton) {
                    valueAfterConvert.setText("" + valueAmount);

                }
            } else {
                valueAfterConvert.setText("" + valueAmount);
            }

        } catch (NumberFormatException e) {
            //Generate a toast if the user tries to convert with out providing any value.
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter a numeric value!", Toast.LENGTH_SHORT);
            toast.show();

        }
    }
}