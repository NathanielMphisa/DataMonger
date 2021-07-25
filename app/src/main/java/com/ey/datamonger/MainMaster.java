package com.ey.datamonger;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMaster extends AppCompatActivity {

    TextView viewObj;
    Button btnSectionA,btnSectionB, btnSectionC,btnSectionD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_master);

        btnSectionA = findViewById(R.id.btnSection1);
        btnSectionB = findViewById(R.id.btnSection2);
        btnSectionC = findViewById(R.id.btnSection3);
        btnSectionD = findViewById(R.id.btnSection4);

        viewObj = findViewById(R.id.viewObjofVerifications);
        viewObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"Objectives");
    }

    public void carryIntents(Class gotoClass){
        Intent intent = new Intent(getApplicationContext(),gotoClass);
        startActivity(intent);
    }


    public void navigator(View view){
        String tag = String.valueOf(view.getTag());

        switch(tag){
            case "sectionFirst":
                carryIntents(GeneralInforExec_Mgr.class);
                break;
            case "sectionSecond":
                carryIntents(SummaryOfFindings_Mgr.class);
                break;
            case "sectionThird":
                carryIntents(ManagerExpenditure.class);
                break;
            case"sectionLast":
               // carryIntents(GeneralInforExec_Mgr.class);
                break;
            default:
                Toast.makeText(getApplicationContext(),"Invalid selection on Menu page " + tag,Toast.LENGTH_LONG).show();

        }
    }

}

