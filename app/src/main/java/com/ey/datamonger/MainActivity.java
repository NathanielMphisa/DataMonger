package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnlogin);

    }

    public void startSurvey(View view){

        Intent intent = new Intent(getApplicationContext(), MainMaster.class);
        startActivity(intent);

    }

    public  void goToNextSection(View view){

        Intent intent = new Intent(this,AmountsPaid.class);
        startActivity(intent);

    }
}
