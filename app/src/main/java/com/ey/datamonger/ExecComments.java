package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExecComments extends AppCompatActivity {
    private Button goToNext;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executive_comments);

        setTitle("DataMonger: Executive Comments");
        context = getApplicationContext();

        //goToNext = findViewById(R.id.moveToNextActivity);
    }

    public void goToNextSectionAck(View view){
        Intent intent = new Intent(context,AcknowledgementOfFunds.class);
        startActivity(intent);
    }
}
