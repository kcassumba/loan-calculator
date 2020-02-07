package com.example.loancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText priceBox;
    private EditText downBox;
    private EditText aprBox;
    private CheckBox leaseBox;
    private TextView barLabel;
    private SeekBar seekBar;
    private TextView monthlyBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceBox = findViewById(R.id.priceBox);
        downBox = findViewById(R.id.downBox);
        aprBox = findViewById(R.id.aprBox);
        leaseBox = findViewById(R.id.leaseBox);
        barLabel = findViewById(R.id.barLabel);
        seekBar = findViewById(R.id.seekBar);
        monthlyBox = findViewById(R.id.monthlyBox);

        leaseBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        aprBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                loan(v);
                return false;

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                barLabel.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void loan(View v) {


        if (!leaseBox.isChecked()) {

            String inputm = aprBox.getText().toString();
            String inputcost = priceBox.getText().toString();
            String inputdown = downBox.getText().toString();
            String inputbar = barLabel.getText().toString();

            if (inputcost.length() > 0) {
                double label = Double.parseDouble(inputbar);
                double mr = (Double.parseDouble(inputm) / 100) / 12;
                double loan = Double.parseDouble(inputcost) - Double.parseDouble(inputdown);

                double pay = mr * loan / (1 - (Math.pow(1+mr, (-1 * label))));

                monthlyBox.setText(String.format("%.2f", pay));

            } else {


                Toast.makeText(this,"No Purchase price entered.", Toast.LENGTH_SHORT).show();
            }
            }else{

            String inputm = aprBox.getText().toString();
            String inputcost = priceBox.getText().toString();
            String inputdown = downBox.getText().toString();


            double lease = Double.parseDouble(inputcost)/3.0;
            double label = 36.0;
            double mr = (Double.parseDouble(inputm) / 100) / 12;
            double loan = lease - Double.parseDouble(inputdown);

            double pay = mr * loan / (1 - (Math.pow(1+mr, (-1 * label))));

            monthlyBox.setText(String.format("%.2f", pay));




        }


        }
    }