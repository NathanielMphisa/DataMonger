package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExpenseMgr extends AppCompatActivity {

    private Button btnq1, btnq2, btnq3, btnq4;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_mgr);

        setPersistence();

        btnq1 = findViewById(R.id.qOne);
        btnq2 = findViewById(R.id.qTwo);
        btnq3 = findViewById(R.id.qThree);
        btnq4 = findViewById(R.id.qFour);

    }

    public void setPersistence() {
        sharedPreferences = ExpenseMgr.this.getSharedPreferences("Period",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("timeValue","needSetting");
        editor.commit();
    }

    public void organiseIntents(String period){

        editor.putString("timeValue",period);
        editor.apply();
        Intent intent = new Intent(ExpenseMgr.this,ListViewItems.class);
        //intent.putExtra("quarter",period);
        startActivity(intent);
    }

    public void getList(View view) {
        String tag = view.getTag().toString();

        switch (tag){
            case "firstQuarter":
                organiseIntents("Q1 2018");
                break;
            case "secondQuarter":
                organiseIntents("Q2 2018");
                break;
            case "thirdQuarter":
                organiseIntents("Q3 2018");
                break;
            case "forthQuarter":
                organiseIntents("Q4 2019");
                break;
            case "viewAll":
                organiseIntents("allExpenses");
                break;
        }
    }
}
