package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.Calendar;

public class General_Information extends AppCompatActivity {

    //Declaring class variables

    public static Long foreignKey;

    private String[] provinces = {"Bulawayo Province",
            "Harare Province",
            "Manicaland Province",
            "Mashonaland Central Province",
            "Mashonaland East Province",
            "Mashonaland West Province",
            "Masvingo Province",
            "Matabeleland North Province",
            "Matabeleland South Province",
            "Midlands Province"};

    private String[] facilityTypes ={"Rural Health Centre", "Rural Hospital","Mission Hospital"};

    private EditText facilityName,facilityDistrict, facilityHead,facilityHeadTitle, verificationDate,
                    facilityHeadContacts, verificationteam, facilityPersonnelMet;
    private Button nextSection, save;
    private Spinner provinceSpinner, facilityTypeSpinner;
    private ArrayAdapter  provinceArrayAdapter;
    private ScrollView scrollView;
    private RadioGroup q1CurrencyA, q2CurrencyB, q3CurrencyC, q4CurrencyD;
    /* IDS
            appRHC (txt)
            appProvince (txt)
            appDistrict (txt)
            appFacilityType (txt)
            appFacilityHead (txt)
            appFacilityHeadTitle (txt)
            appDateofVerification (txt)
            appFacilityHeadContact  (txt)
            appVerificationTeam (txt)
            appPersonsMet (txt)
            saveGeneralInformation *saveInformation()*
            moveToNextActivity  *goToNextSection()*
     */

    private String[] currency = {"USD", "RTGS"};
    private EditText amountQ1received,dateQ1received,amountQ2received,
            dateQ2received,amountQ3received,dateQ3received,amountQ4received,dateQ4received;
    private Spinner spinnerQ1,spinnerQ2,spinnerQ3,spinnerQ4;
    private ArrayAdapter facilityTypeArrayAdapter;

    private Button saveGeneralInformation, moveToNextActivity;

    private Context context;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private DBManager dbManager;


   // public static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_general_info);
        setTitle("DataMonger - General Information");

        dbManager = new DBManager(this);

        initialiseViewsAndWidgets();

    }

    private void initialiseViewsAndWidgets() {

        scrollView = findViewById(R.id.generalInformationQuestions);
        facilityName = findViewById(R.id.appRHC);
        facilityDistrict = findViewById(R.id.appDistrict);
        facilityHead = findViewById(R.id.appFacilityHead);
        facilityHeadTitle = findViewById(R.id.appFacilityHeadTitle);
        verificationDate = findViewById(R.id.appDateofVerification);
        facilityHeadContacts = findViewById(R.id.appFacilityHeadContact);
        verificationteam = findViewById(R.id.appVerificationTeam);
        facilityPersonnelMet = findViewById(R.id.appPersonsMet);
        save = findViewById(R.id.saveFacilityInformation);


        // TODO reading  and configuring the Spinner
        provinceSpinner = findViewById(R.id.appProvince);
        provinceArrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_item, provinces);
        provinceSpinner.setAdapter(provinceArrayAdapter);

        facilityTypeSpinner = findViewById(R.id.appFacilityType);
        facilityTypeArrayAdapter = new ArrayAdapter<>(getApplication(),R.layout.spinner_item,facilityTypes);
        facilityTypeSpinner.setAdapter(facilityTypeArrayAdapter);

        context = getApplicationContext();

        //TODO Read the Front view

        q1CurrencyA = findViewById(R.id.q1Currency); // radioGroup
        amountQ1received = findViewById(R.id.q1AmountReceived);
        dateQ1received = findViewById(R.id.q1DateReceivedFunds);

        q2CurrencyB = findViewById(R.id.q2Currency); // radioGroup
        amountQ2received = findViewById(R.id.q2AmountReceived);
        dateQ2received = findViewById(R.id.q2DateReceivedFunds);

        q3CurrencyC = findViewById(R.id.q3Currency); // radioGroup
        amountQ3received = findViewById(R.id.q3AmountReceived);
        dateQ3received = findViewById(R.id.q3DateReceivedFunds);

        q4CurrencyD = findViewById(R.id.q4Currency); // radioGroup
        amountQ4received = findViewById(R.id.q4AmountReceived);
        dateQ4received = findViewById(R.id.q4DateReceivedFunds);

        saveGeneralInformation = findViewById(R.id.saveGeneralInformation);



    }

    public void saveInformation(View view){
        String selectedProvince = provinceSpinner .getSelectedItem().toString();

        //setValues.setQ1AmountReceived(Integer.parseInt(amountQ1received.getText().toString()));
        //setValues.setQ1Currency(spinnerQ1.getSelectedItem().toString());
        // setValues.setQ1DisbursementDate();
        String currencyQ1, currencyQ2, currencyQ3, currencyQ4;

        int q1C = q1CurrencyA.getCheckedRadioButtonId();

        if (q1C == R.id.first){
            currencyQ1 = "USD";
        }
        else
            currencyQ1 = "RTGS";

        int q2C = q2CurrencyB.getCheckedRadioButtonId();

        if (q2C == R.id.firstUsd){
            currencyQ2 = "USD";
        }
        else
            currencyQ2 = "RTGS";

        int q3C = q3CurrencyC.getCheckedRadioButtonId();

        if (q3C == R.id.usd){
            currencyQ3 = "USD";
        }
        else
            currencyQ3 = "RTGS";

        int q4C = q4CurrencyD.getCheckedRadioButtonId();

        if (q4C == R.id.usda){
            currencyQ4 = "USD";
        }
        else
            currencyQ4 = "RTGS";

       foreignKey =  dbManager.insertFacilityInformation(facilityName.getText().toString(),
                facilityTypeSpinner.getSelectedItem().toString(),
                provinceSpinner.getSelectedItem().toString(),
                facilityDistrict.getText().toString(),
                facilityHead.getText().toString(),
                facilityHeadTitle.getText().toString(),
                facilityHeadContacts.getText().toString(),
                verificationDate.getText().toString(),
                facilityPersonnelMet.getText().toString(),verificationteam.getText().toString());

       // sharedPreferences.edit().putString("foreign_KEY",foreignKey.toString()).apply();


        dbManager.insertDisbursement(Integer.parseInt(amountQ1received.getText().toString()),
                currencyQ1,dateQ1received.getText().toString(),
                Integer.parseInt(amountQ2received.getText().toString()),currencyQ2,dateQ2received.getText().toString(),
                Integer.parseInt(amountQ3received.getText().toString()),currencyQ3,dateQ3received.getText().toString(),
                Integer.parseInt(amountQ4received.getText().toString()),currencyQ4,dateQ4received.getText().toString(),
                foreignKey.intValue());


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
            case "verificationDay":
                variable = verificationDate;
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
                        General_Information.this,
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
