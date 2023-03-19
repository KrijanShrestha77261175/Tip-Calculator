package com.example.tiptime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  This activity allows a user to calculate the tip
 *  based on the quality of service.
 */
public class MainActivity extends AppCompatActivity {


    private EditText costOfServiceText;
    private Button calcTipButton;
    private RadioGroup radioGroup;
    private Switch roundUpSwitch;
    private TextView tipAmountText;
    private double tipPercent;
    private double tipAmountTotal;

    /**
     *  This method is called when the Activity is created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        costOfServiceText = findViewById(R.id.costOfService);
        tipAmountText = findViewById(R.id.tipAmount);
        calcTipButton = findViewById(R.id.calculateButton);
        roundUpSwitch = findViewById(R.id.switch1);
        radioGroup = findViewById(R.id.radioGroup);

        calcTipButton.setOnClickListener(new View.OnClickListener() {

            /**
             *  This methods calculates the tip based on the quality
             *  which are amazing, good and okay.
             * @param view
             */
            @Override
            public void onClick(View view) {
                try
                {
                    String costString = costOfServiceText.getText().toString();
                    int costAmount = Integer.parseInt(costString);
                    int checkedRadioButton = radioGroup.getCheckedRadioButtonId();
                    if (checkedRadioButton == R.id.amazingButton)
                    {
                        tipPercent = 0.2;
                    } else if (checkedRadioButton == R.id.goodButton) {
                        tipPercent = 0.18;
                    } else if (checkedRadioButton == R.id.okayButton) {
                        tipPercent = 0.15;
                    }else {
                        tipPercent = 0.0;
                    }

                    tipAmountTotal = costAmount * tipPercent;
                    if(roundUpSwitch.isChecked())
                    {
                        tipAmountTotal = Math.round(tipAmountTotal);
                    }
                    tipAmountText.setText("" + tipAmountTotal);
                }
                catch(NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(),"Please enter a numeric value!",Toast.LENGTH_SHORT).show();
                    tipAmountText.setText("0");
                }

            }
        });
    }
}