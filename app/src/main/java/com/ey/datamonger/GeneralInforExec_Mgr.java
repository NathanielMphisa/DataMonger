package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeneralInforExec_Mgr extends AppCompatActivity {

    Button generalInf, executive, btnExpense;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_infor_exec__mgr);
        generalInf = findViewById(R.id.btnGeneralInfor);
        executive = findViewById(R.id.btnExecutiveSummary);
        btnExpense = findViewById(R.id.btnExpenses);


        generalInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),General_Information.class);
                startActivity(intent);
            }
        });

        executive.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),ExecComments.class);
                startActivity(intent);
            }
        });

        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ExpenseMgr.class);
                startActivity(intent);
            }
        });

    }


}
