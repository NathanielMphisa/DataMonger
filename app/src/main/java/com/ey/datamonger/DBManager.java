package com.ey.datamonger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.widget.Toast;

public class DBManager {
    private DatabaseHelper dbhelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DBManager(Context context) {
        this.context = context;
    }

    // passing the context to the DatabaseHelper class to create database

    public DBManager open() throws SQLException{
        dbhelper = new DatabaseHelper(context);
        sqLiteDatabase = dbhelper.getWritableDatabase();
        return this;

    }

    public void close(){
        dbhelper.close();
    }


    // Insert facility information

    public Long insertFacilityInformation(String facilityName, String facilityType, String province, String district, String facilityHeadName, String facilityHeadTitle, String facilityHeadContact, String verificationDate, String peopleMet, String verificationTeam ){
        Long referenceID = 0L;

        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FAC_NAME,facilityName);
        contentValues.put(DatabaseHelper.FAC_TYPE,facilityType);
        contentValues.put(DatabaseHelper.PROVINCE,province);
        contentValues.put(DatabaseHelper.DISTRICT,district);
        contentValues.put(DatabaseHelper.FAC_HEAD_NAME,facilityHeadName);
        contentValues.put(DatabaseHelper.FAC_HEAD_TITLE,facilityHeadTitle);
        contentValues.put(DatabaseHelper.FAC_HEAD_CONTACT,facilityHeadContact);
        contentValues.put(DatabaseHelper.VER_DATE,verificationDate);
        contentValues.put(DatabaseHelper.FAC_PERSON,peopleMet);
        contentValues.put(DatabaseHelper.VER_TEAM,verificationTeam);

        try{
            referenceID = sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME_FAC,null,contentValues);
            Toast.makeText(context,"Information saved in general Information table",Toast.LENGTH_SHORT).show();


        }
        catch (Exception e){
            e.printStackTrace();
        }

        return referenceID;
    }

    // insert disbursement
    public void insertDisbursement( int q1Amount, String q1Currency, String q1Date,
                                    int q2Amount, String q2Currency, String q2Date,
                                    int q3Amount, String q3Currency, String q3Date,
                                    int q4Amount, String q4Currency, String q4Date, int id){

        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DIS_QUARTER1AMOUNT, q1Amount);
        contentValues.put(DatabaseHelper.DIS_QUARTER1CURRENCY, q1Currency);
        contentValues.put(DatabaseHelper.DIS_QUARTER1DATE, q1Date);
        contentValues.put(DatabaseHelper.DIS_QUARTER2AMOUNT, q2Amount);
        contentValues.put(DatabaseHelper.DIS_QUARTER2CURRENCY, q2Currency);
        contentValues.put(DatabaseHelper.DIS_QUARTER2DATE, q2Date);
        contentValues.put(DatabaseHelper.DIS_QUARTER3AMOUNT, q3Amount);
        contentValues.put(DatabaseHelper.DIS_QUARTER3CURRENCY, q3Currency);
        contentValues.put(DatabaseHelper.DIS_QUARTER3DATE, q3Date);
        contentValues.put(DatabaseHelper.DIS_QUARTER4AMOUNT, q4Amount);
        contentValues.put(DatabaseHelper.DIS_QUARTER4CURRENCY, q4Currency);
        contentValues.put(DatabaseHelper.DIS_QUARTER4DATE, q4Date);
        contentValues.put(DatabaseHelper.DIS_FACILITY_FK, id);

        try{
            sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME_DIS,null,contentValues);
            Toast.makeText(context,"Information saved in Disbursement table ",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    // insert expense
    public void insertExpense(String period,
                              String product,
                              int productCost,
                              String currency,
                              String description,
                              String criterion){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.EXPENSES_PERIOD,period);
        contentValues.put(DatabaseHelper.EXPENSES_PRODUCT,product);
        contentValues.put(DatabaseHelper.EXPENSES_PRODUCT_COST,productCost);
        contentValues.put(DatabaseHelper.EXPENSES_CURRENCY,currency);
        contentValues.put(DatabaseHelper.EXPENSES_PRODUCT_DESCRIPTION,description);
        contentValues.put(DatabaseHelper.EXPENSES_CRITERION,criterion);

        try{
            sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME_EXPENSES,null,contentValues);
            Toast.makeText(context,"Item added to Expense Table",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Cursor fetchExpenseList(String timeLimiter){
        //String[] columns = new String[]{DatabaseHelper.EXPENSES_ID,DatabaseHelper.EXPENSES_PERIOD, DatabaseHelper.EXPENSES_PRODUCT,DatabaseHelper.EXPENSES_PRODUCT_COST,DatabaseHelper.EXPENSES_CURRENCY};

        String sql;
        Cursor cursor;

        if (timeLimiter == "allExpenses")
        {
            sql = "SELECT * FROM " + DatabaseHelper.TABLE_NAME_EXPENSES;
            cursor = sqLiteDatabase.rawQuery(sql,null);

        }

        else{

            String[] args = new String[]{timeLimiter};
            sql = "SELECT "+ DatabaseHelper.EXPENSES_ID + ", " +
                    DatabaseHelper.EXPENSES_PERIOD + ", " +
                    DatabaseHelper.EXPENSES_PRODUCT + ", " +
                    DatabaseHelper.EXPENSES_PRODUCT_COST + ", " +
                    DatabaseHelper.EXPENSES_CURRENCY + " FROM " +
                    DatabaseHelper.TABLE_NAME_EXPENSES + " WHERE " + DatabaseHelper.EXPENSES_PERIOD + " = ?";
            cursor = sqLiteDatabase.rawQuery(sql,args);
        }

        //Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_NAME_EXPENSES,columns,null,null,null,null,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;

    }

}
