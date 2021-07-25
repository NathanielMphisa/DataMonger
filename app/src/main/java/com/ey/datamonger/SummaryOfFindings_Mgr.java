package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SummaryOfFindings_Mgr extends AppCompatActivity implements View.OnClickListener {
    private Button acknowledgement_of_funds, cash_Internal_Control, purchase_payment_security,staff_incentive_assess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_of_findings__mgr);

        acknowledgement_of_funds = findViewById(R.id.btnAckFunds);
        cash_Internal_Control = findViewById(R.id.btnCashInternalControl);
        purchase_payment_security = findViewById(R.id.btnPurchase_Payment_security);
        staff_incentive_assess = findViewById(R.id.btnStaff_incentive_assess);

        acknowledgement_of_funds.setOnClickListener(this);
        cash_Internal_Control.setOnClickListener(this);
        purchase_payment_security.setOnClickListener(this);
        staff_incentive_assess.setOnClickListener(this);

    }

    public void navigateToPage(Class gotoClass){
        Intent intent = new Intent(SummaryOfFindings_Mgr.this, gotoClass);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAckFunds:
                navigateToPage(AcknowledgementOfFunds.class);
                break;
            case R.id.btnCashInternalControl:
                navigateToPage(CashInternalControl.class);
                break;
            case R.id.btnPurchase_Payment_security:
                navigateToPage(PurchasesInternalControl.class);
                break;
            case R.id.btnStaff_incentive_assess:
                navigateToPage(StaffIncentiveAssessment.class);
                break;
                default:
                    Toast.makeText(SummaryOfFindings_Mgr.this,"No valid selection - Summary of Findings Mgr",Toast.LENGTH_SHORT).show();
                    break;
        }
    }
}
