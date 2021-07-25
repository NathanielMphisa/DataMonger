package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ManagerExpenditure extends AppCompatActivity {
    Button q32018, q42018, q12019, q22019;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_expenditure);

        q32018 = findViewById(R.id.btnQ32018);
        q42018 = findViewById(R.id.btnQ42018);
        q12019 = findViewById(R.id.btnQ12019);
        q22019 = findViewById(R.id.btnQ22019);

    }

    /* TODO Create a method that creates intents */

    public void getFragment(View view)
    {
        String tags = String.valueOf(view.getTag());

        switch (tags)
        {
            case "q318":
                Intent intent = new Intent(getApplicationContext(),ExpenditureTest.class);
                intent.putExtra("period","Q3 2018");
                startActivity(intent);
                break;

            case "q418":
                Intent q4Intent = new Intent(getApplicationContext(),ExpenditureTest.class);
                q4Intent.putExtra("period","Q4 2018");
                startActivity(q4Intent);
                break;

            case "q119":
                Intent q119Intent = new Intent(getApplicationContext(),ExpenditureTest.class);
                q119Intent.putExtra("period","Q1 2019");
                startActivity(q119Intent);
                break;

            case "q219":
                Intent q219Intent = new Intent(getApplicationContext(),ExpenditureTest.class);
                q219Intent.putExtra("period","Q2 2019");
                startActivity(q219Intent);
                break;

            case "getExp":
                Intent expIntent = new Intent(getApplicationContext(),ExpenditureInternalControls.class);
                startActivity(expIntent);
                break;

            case "getPerfomanceSubs":
                Intent pefSubsIntent = new Intent(getApplicationContext(),UseOfPerformanceSub.class);
                startActivity(pefSubsIntent);
                break;
            case "getCashAndBankManagement":
                Intent getCashBankMg = new Intent(getApplicationContext(),CashBankManagement.class);
                startActivity(getCashBankMg);
                break;

            case "getProcurementContracting":
                Intent getProcurementAndCont = new Intent(getApplicationContext(),ProcurementAndContracting.class);
                startActivity(getProcurementAndCont);
                break;

            default:
                Toast.makeText(getApplicationContext(),"Invalid Selection on Period selection", Toast.LENGTH_SHORT).show();

        }

    }

    public void getActivityImplementation(View view)
    {
        String variable = String.valueOf(view.getTag());

        switch (variable){

            case "activity1":
                Intent intents = new Intent(getApplicationContext(),ExpendActivityManager.class);
                intents.putExtra("name","Activity A");
                startActivity(intents);
                break;

            case "activity2":
                Intent intentActivity2 = new Intent(getApplicationContext(),ExpendActivityManager.class);
                intentActivity2.putExtra("name","Activity B");
                startActivity(intentActivity2);
                break;

            case "activity3":
                Intent intentActivity3 = new Intent(getApplicationContext(),ExpendActivityManager.class);
                intentActivity3.putExtra("name","Activity C");
                startActivity(intentActivity3);
                break;

            default:
                Toast.makeText(getApplicationContext(),"Invalid Selection on Activity selection", Toast.LENGTH_SHORT).show();
        }
    }
}
