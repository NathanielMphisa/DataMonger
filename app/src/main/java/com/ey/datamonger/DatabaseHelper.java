package com.ey.datamonger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "DBSurveyDatabase";
    public static final int DB_VERSION = 1;
    private Context context;

//DEFINING TABLES AND QUERIES TO MAKE THEM

    // General Information table

    public static final String TABLE_NAME_FAC = "facilityInformation";
    public static final String FAC_ID = "facilityId";
    public static final String FAC_NAME = "facilityName";
    public static final String FAC_TYPE = "facilityType";
    public static final String PROVINCE = "province";
    public static final String DISTRICT = "district";
    public static final String FAC_HEAD_TITLE = "facilityHead";
    public static final String FAC_HEAD_CONTACT= "facilityHeadContact";
    public static final String FAC_HEAD_NAME ="facilityHeadName";
    public static final String VER_DATE = "verificationDate";
    public static final String FAC_PERSON = "facPersonMet";
    public static final String VER_TEAM = "verificationTeam";

    public static final String CREATE_TABLE_FACILITY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_FAC +
            " ( " + FAC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FAC_NAME + " TEXT NOT NULL, " +
            FAC_TYPE + " TEXT NOT NULL, " +
            PROVINCE + " TEXT NOT NULL, " +
            DISTRICT + " TEXT NOT NULL, " +
            FAC_HEAD_NAME + " TEXT NOT NULL, " +
            FAC_HEAD_TITLE + " TEXT NOT NULL, " +
            FAC_HEAD_CONTACT + " TEXT NOT NULL, " +
            VER_DATE + " TEXT NOT NULL, " +
            FAC_PERSON + " TEXT NOT NULL, " +
            VER_TEAM + " TEXT NOT NULL)" ;

    // TABLE DISBURSEMENT

    public static final String TABLE_NAME_DIS = "disbursement";
    public static final String DIS_ID = "disbursementId";

    public static final String DIS_QUARTER1AMOUNT = "quarter1";
    public static final String DIS_QUARTER1CURRENCY = "q1Currency";
    public static final String DIS_QUARTER1DATE ="q1Date";

    public static final String DIS_QUARTER2AMOUNT  = "quarter2";
    public static final String DIS_QUARTER2CURRENCY = "q2Currency";
    public static final String DIS_QUARTER2DATE ="q12Date";

    public static final String DIS_QUARTER3AMOUNT  = "quarter3";
    public static final String DIS_QUARTER3CURRENCY = "q3Currency";
    public static final String DIS_QUARTER3DATE ="q3Date";

    public static final String DIS_QUARTER4AMOUNT  = "quarter4";
    public static final String DIS_QUARTER4CURRENCY = "q4Currency";
    public static final String DIS_QUARTER4DATE ="q4Date";
    //CONSTRAINT FOREIGN KEY
    public static final String DIS_FACILITY_FK = "facilityId";

    public static final String CREATE_TABLE_DISBURSEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_DIS + " (" +
            DIS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DIS_QUARTER1AMOUNT + " INTEGER NOT NULL, " +
            DIS_QUARTER1CURRENCY + " TEXT NOT NULL, " +
            DIS_QUARTER1DATE + " TEXT NOT NULL, " +
            DIS_QUARTER2AMOUNT + " INTEGER NOT NULL, " +
            DIS_QUARTER2CURRENCY + " TEXT NOT NULL, " +
            DIS_QUARTER2DATE + " TEXT NOT NULL, " +
            DIS_QUARTER3AMOUNT + " INTEGER NOT NULL, " +
            DIS_QUARTER3CURRENCY + " TEXT NOT NULL, " +
            DIS_QUARTER3DATE + " TEXT NOT NULL, " +
            DIS_QUARTER4AMOUNT + " INTEGER NOT NULL, " +
            DIS_QUARTER4CURRENCY + " TEXT NOT NULL, " +
            DIS_QUARTER4DATE + " TEXT NOT NULL, " +
            DIS_FACILITY_FK + " INTEGER NOT NULL, " +
            "FOREIGN KEY (" + DIS_FACILITY_FK + ") REFERENCES " + TABLE_NAME_FAC + "(" + FAC_ID + ") ON DELETE CASCADE) ";


    //TABLE EXPENSES
    public static final String TABLE_NAME_EXPENSES = "expenses";
    public static final String EXPENSES_ID = "_id";
    public static final String EXPENSES_PERIOD ="quarter";
    public static final String EXPENSES_PRODUCT = "product";
    public static final String EXPENSES_PRODUCT_COST = "productCost";
    public static final String EXPENSES_PRODUCT_DESCRIPTION = "description";
    public static final String EXPENSES_CURRENCY = "currency";
    public static final String EXPENSES_CRITERION = "criteria";

    public static final String CREATE_TABLE_EXPENSES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_EXPENSES + " ( " +
            EXPENSES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            EXPENSES_PERIOD + " TEXT NOT NULL, " +
            EXPENSES_PRODUCT + " TEXT NOT NULL, " +
            EXPENSES_PRODUCT_COST + " REAL NOT NULL, " +
            EXPENSES_CURRENCY + " TEXT NOT NULL, " +
            EXPENSES_PRODUCT_DESCRIPTION + " TEXT, " +
            EXPENSES_CRITERION + " TEXT NOT NULL)";


    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FACILITY);
        db.execSQL(CREATE_TABLE_DISBURSEMENT);
        db.execSQL(CREATE_TABLE_EXPENSES);
        Log.i("info","Tables Facility information and Disbursement Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FAC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DIS);
        onCreate(db);

    }
}
