package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Calendar;

public class AmountsPaid extends AppCompatActivity {

    private String[] currency = {"USD", "RTGS"};
    private EditText amountQ1received,dateQ1received,amountQ2received,
            dateQ2received,amountQ3received,dateQ3received,amountQ4received,dateQ4received;
    private Spinner spinnerQ1,spinnerQ2,spinnerQ3,spinnerQ4;
    private ArrayAdapter arrayAdapter;

    private Button saveGeneralInformation, moveToNextActivity;

    private Context context;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //Instantiating model classes
    AmountsDistributed  setValues = new AmountsDistributed();

    /* IDs
            spinnerCurrencyq1
            q1AmountReceived
            q1DateReceivedFunds

            spinnerCurrencyq2
            q2AmountReceived
            q2DateReceivedFunds

            spinnerCurrencyq3
            q3AmountReceived
            q3DateReceivedFunds

            spinnerCurrencyq4
            q4AmountReceived
            q4DateReceivedFunds

            saveGeneralInformation *saveInformation()*
            moveToNextActivity *goToNextSection()

            String text = mySpinner.getSelectedItem().toString();

     */

    //TODO Declare variables
    // Declare arrays with Spinner Data
    // Add ArrayAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amounts_paid);

        setTitle("DataMonger: Amounts Paid ");

        context = getApplicationContext();

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,currency);

        //TODO Read the Front view
        spinnerQ1 = findViewById(R.id.spinnerCurrencyq1);
        spinnerQ1.setAdapter(arrayAdapter);
        amountQ1received = findViewById(R.id.q1AmountReceived);
        dateQ1received = findViewById(R.id.q1DateReceivedFunds);

        spinnerQ2 = findViewById(R.id.spinnerCurrencyq2);
        spinnerQ2.setAdapter(arrayAdapter);
        amountQ2received = findViewById(R.id.q2AmountReceived);
        dateQ2received = findViewById(R.id.q2DateReceivedFunds);

        spinnerQ3 = findViewById(R.id.spinnerCurrencyq3);
        spinnerQ3.setAdapter(arrayAdapter);
        amountQ3received = findViewById(R.id.q3AmountReceived);
        dateQ3received = findViewById(R.id.q3DateReceivedFunds);

        spinnerQ4 = findViewById(R.id.spinnerCurrencyq4);
        spinnerQ4.setAdapter(arrayAdapter);
        amountQ4received = findViewById(R.id.q4AmountReceived);
        dateQ4received = findViewById(R.id.q4DateReceivedFunds);
        saveGeneralInformation = findViewById(R.id.saveGeneralInformation);
        //moveToNextActivity = findViewById(R.id.moveToNextActivity);

    }

    //Todo Declare method to save Data on the txt boxes

    public void saveInformation(View view){
        //get Selected values from the Spinners;
        String selectedQ1Spinner = spinnerQ1.getSelectedItem().toString();

        setValues.setQ1AmountReceived(Integer.parseInt(amountQ1received.getText().toString()));
        setValues.setQ1Currency(spinnerQ1.getSelectedItem().toString());
       // setValues.setQ1DisbursementDate();


    }

    //Todo Declare method to go to the next Activity
    public void goToNextSection(View view){
        Intent intent  = new Intent(context,ExecComments.class);
        startActivity(intent);
    }

    public void getDate(View view){
        final EditText variable;
        String passedTag = view.getTag().toString();
        Log.i("Info","the tag passed is " + passedTag );

        switch(passedTag){
            case "quarter1":
                variable = dateQ1received;
                break;
            case "quarter2":
                variable = dateQ2received;
                break;
            case "quarter3":
                variable = dateQ3received;
                break;
            case "quarter4":
                variable = dateQ4received;
                break;
                default:
                    variable = null;
        }

        variable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        AmountsPaid.this,
                        android.R.style.ThemeOverlay_Material_Dark,
                        mDateSetListener,
                        year,month,day);
                //dialog.getWindow().setBackgroundDrawable( new ColorDrawable(Color.blue()));
                dialog.show();


            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                variable.setText(date);
                Log.d("Info","onDateSet: date" + year + "/" + month + "/" + dayOfMonth);
            }
        };
    }



}
