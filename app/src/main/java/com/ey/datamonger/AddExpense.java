package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddExpense extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView periodStatement;
    private EditText product, expense, productDesc, usdCost, rtgsCost;
    private RadioGroup currencySpecification;
    private Spinner specifyCriterion;
    private Button addButton, checkExpenses, btnCancel;
    private ArrayAdapter criterion;
    private String[] criterionTypes = {"Medicine","T and S","Facility Maintenance","Staff incentive","Other"};
    private DBManager dbManager;
    private String timeVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        sharedPreferences = getApplicationContext().getSharedPreferences("Period",MODE_PRIVATE);

        periodStatement = findViewById(R.id.quarterHead);

        timeVal = sharedPreferences.getString("timeValue","notSet");

        periodStatement.setText(timeVal);

        product = findViewById(R.id.product);
        expense = findViewById(R.id.productCost);
        productDesc = findViewById(R.id.productDescription);
        currencySpecification = findViewById(R.id.radio_specify_currency);
        specifyCriterion = findViewById(R.id.criterionSpinner);
        addButton = findViewById(R.id.btnSubmitProduct);
        btnCancel = findViewById(R.id.btnCancelAddition);

        criterion = new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_item,criterionTypes);
        specifyCriterion.setAdapter(criterion);


        addButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String productDescription = productDesc.getText().toString();
                if( productDescription.isEmpty()) productDescription = "None";

                Toast.makeText(getApplicationContext(),productDescription,Toast.LENGTH_SHORT).show();

                dbManager = new DBManager(getApplicationContext());
                String currencySpec;

                int radioSelected = currencySpecification.getCheckedRadioButtonId();

                if (radioSelected == R.id.radio_USD_){
                    currencySpec = "USD";
                }
                else currencySpec = "RTGS";

                dbManager.insertExpense(
                        timeVal,
                        product.getText().toString(),
                        Integer.parseInt(expense.getText().toString()),
                        currencySpec,
                        productDesc.getText().toString(),
                        specifyCriterion.getSelectedItem().toString());
                Intent intent = new Intent(getApplicationContext(),ListViewItems.class);
                startActivity(intent);
                finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListViewItems.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
