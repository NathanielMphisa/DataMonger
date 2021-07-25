package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListViewItems extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private TextView  whenListIsEmpty;
    private FloatingActionButton floatingActionButton;
    private String periodQ;
    private ListView showListView;
    private Button completedBtn, periodDisplay;
    private SimpleCursorAdapter simpleCursorAdapter;
    public String[] from = new String[]{ DatabaseHelper.EXPENSES_PRODUCT,DatabaseHelper.EXPENSES_CURRENCY,DatabaseHelper.EXPENSES_PRODUCT_COST, DatabaseHelper.EXPENSES_PERIOD};
    public int[] to = new int[]{R.id.name4Product,R.id.currency4Product,R.id.cost4Product, R.id.timeExpensed};
    DBManager dbManager;
    Cursor cursor;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ey.datamonger.R.layout.show_item_list);


        dbManager = new DBManager(this);
        dbManager.open();

        /*
         Setting  SharedPreferences for periods
         */
        getPreference();

        /*
         Initialising Views
         */
        initialiseVariables();

        //***********************************************  Setting up Views
        periodDisplay.setText(periodQ);


        if(periodQ == "All Expenses") {
            completedBtn.setText("Close");
        }


        //***********************************************  Implementation

        // Period Display Button
        periodDisplay.setOnClickListener(this);
        completedBtn.setOnClickListener(this);


        // Floating Action Button
        floatingActionButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(getApplicationContext(),AddExpense.class);
                intent1.putExtra("quarter",periodQ);
                startActivity(intent1);
            }
        });


        //***********************************************  Fetching data from DB

          /*
           Initialising the DBManager
           Fetching the LIst of expenses
         */

        getDataFromDB();
    }

    private void getPreference() {
        sharedPreferences = getApplicationContext().getSharedPreferences("Period",MODE_PRIVATE);
        periodQ = sharedPreferences.getString("timeValue","notSet");

    }

    private void getDataFromDB() {
        cursor = dbManager.fetchExpenseList(periodQ);
        simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.view_record,cursor,from,to,0);
        simpleCursorAdapter.notifyDataSetChanged();
        showListView.setAdapter(simpleCursorAdapter);
    }

    private void initialiseVariables() {
        periodDisplay = findViewById(R.id.periodDisplay); //button
        completedBtn = findViewById(R.id.btnCompleted); //button
        showListView = findViewById(R.id.listViewProducts);
        whenListIsEmpty = findViewById(R.id.whenEmpty);
        showListView.setEmptyView(whenListIsEmpty);
        floatingActionButton = findViewById(R.id.floating_action_button);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.periodDisplay:
                whenPeriodBtnClicked();
                break;
            case R.id.btnCompleted:
                whenCompleteBtnClicked();
                break;
        }

    }

    private void whenCompleteBtnClicked() {

        Intent goBack = new Intent(getApplicationContext(),MainActivity.class);
        Toast.makeText(getApplicationContext(),periodQ + " Expenses Closed",Toast.LENGTH_SHORT).show();
        startActivity(goBack);

    }

    private void whenPeriodBtnClicked() {

        //Creating an instance of the Pop up Menu
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(),periodDisplay);
        // inflating the Popup using xml

        popupMenu.getMenuInflater()
                .inflate(R.menu.menupop,popupMenu.getMenu());

        //registering popup with OnMenuClickListerner
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(),"you clicked " + item.getTitle(),Toast.LENGTH_SHORT).show();
                reOrderInterface(item.getTitle().toString());
                return true;
            }
        });

        popupMenu.show();
    }


    private void reOrderInterface(String impliedPeriod)
    {

        SharedPreferences  sharedPreferences = getApplicationContext().getSharedPreferences("Period",MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("timeValue",impliedPeriod);
        editor.apply();
        periodQ = sharedPreferences.getString("timeValue","notSet");
        periodDisplay.setText(periodQ);


        if( periodQ == "allExpenses") {
            Log.i("Debug","Time period "+periodQ);
            completedBtn.setText("Close");
            getDataFromDB();
        }
        getDataFromDB();

    }
}
