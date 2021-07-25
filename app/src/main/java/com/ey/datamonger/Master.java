package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Master extends AppCompatActivity {

    private TextView generalInfor, amountsRec, execComm, fundsAck, internalControl, purchaseInternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        setTitle("Question Menu");

        generalInfor = findViewById(R.id.forGeneralInformation);
        amountsRec = findViewById(R.id.forAmountsReceived);
        execComm = findViewById(R.id.forExecComments);
        fundsAck = findViewById(R.id.forFundsAcknowledgement);
        internalControl = findViewById(R.id.forCashInternalControl);
        purchaseInternal = findViewById(R.id.forPurchasesInternalControl);

    }

    public void getGeneralInformation(View view) {
        Intent intent = new Intent(getApplicationContext(), General_Information.class);
        startActivity(intent);
    }

    public  void getExecComments(View view){
        Intent intent = new Intent(getApplicationContext(), ExecComments.class);
        startActivity(intent);
    }

    public  void getAmountsReceived(View view){
        Intent intent = new Intent(getApplicationContext(), AmountsPaid.class);
        startActivity(intent);
    }

    public  void getFundsAcknowledgement(View view){
        Intent intent = new Intent(getApplicationContext(), AcknowledgementOfFunds.class);
        startActivity(intent);
    }

    public  void getCashInternalControl(View view){
        Intent intent = new Intent(getApplicationContext(), CashInternalControl.class);
        startActivity(intent);
    }

    public void getPurchasesInternalControl(View view){
        Intent intent = new Intent(getApplicationContext(), PurchasesInternalControl.class);
        startActivity(intent);

    }

    public void getStaffIncentiveAssessment(View view){
        Intent intent = new Intent(getApplicationContext(), StaffIncentiveAssessment.class);
        startActivity(intent);

    }
    public void getExpenditureInternalControls(View view){
        Intent intent = new Intent(getApplicationContext(), ManagerExpenditure.class);
        startActivity(intent);

    }


}
