package com.ey.datamonger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CashBankManagement extends AppCompatActivity implements View.OnClickListener{
    private TextView question_header, counter, question_View;
    private EditText user_answer;
    private Button button_next, button_close;
    private int current_question_index = 0;
    private String questionLength, currentPosition;
    private StoreCBMValues storeCBMValues = new StoreCBMValues();

    private QuestionCB[] questionCBS = new QuestionCB[] {
            new QuestionCB(R.string.cbMBankName,R.string.cbMBankingDetails),
            new QuestionCB(R.string.cbMRTGSBankAccount,R.string.cbMBankingDetails),
            new QuestionCB(R.string.cbMUSDBankAccount,R.string.cbMBankingDetails),
            new QuestionCB(R.string.cbMFundsDedicatedAccount,R.string.cbMBanking),
            new QuestionCB(R.string.cbMDirectCashManagement,R.string.cbMCashManagement),
            new QuestionCB(R.string.cbMModeOfPayment,R.string.cbMBanking),
            new QuestionCB(R.string.cbMCashUsedModePayment,R.string.cbMBanking),
            new QuestionCB(R.string.cbMCashBookUpdated,R.string.cbMBanking),
            new QuestionCB(R.string.cbMCashBookBalances,R.string.cbMBanking)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_bank_management);

        //initiating views
        question_header = findViewById(R.id.questionHeader);
        question_View = findViewById(R.id.questionTextView);

        counter = findViewById(R.id.question_counter);
        user_answer = findViewById(R.id.user_answer);
        button_next = findViewById(R.id.button_next_question);
        button_close = findViewById(R.id.button_close);


        //setting initial question

        question_View.setText(R.string.cbMBankName);
        question_header.setText(R.string.cbMBankingDetails);
        questionLength = String.valueOf(questionCBS.length);

        button_next.setOnClickListener(CashBankManagement.this);
        button_close.setOnClickListener(CashBankManagement.this);
    }

    public void updateCounter(){
        currentPosition = String.valueOf(current_question_index + 1);
        counter.setText(currentPosition + " of " + questionLength );
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button_next_question:
                if (current_question_index == 8){
                    // TODO IF IT IS - THEN HIDE NEXT BTN AND DISPLAY CLOSE BTN
                    closeActivityAndGoBack();
                }
                else {
                    // TODO ELSE CONTINUE THE PROCESS

                    // fetch data in editText and store is somewhere.
                    String answer =  user_answer.getText().toString();
                    storeValues(current_question_index,answer);

                    //update Question.
                    current_question_index = (current_question_index + 1) % questionCBS.length;
                    updateQuestion(current_question_index);
                    updateCounter();
                }

                break;
            case R.id.button_close:
                // make sure data is posted in the database
                postDatatoDB();
                Intent intent = new Intent(CashBankManagement.this,ManagerExpenditure.class);
                startActivity(intent);
                break;
        }
    }

    private void postDatatoDB() {
        Log.d("DB-POST", storeCBMValues.getBank_name());
        Log.d("DB-POST", storeCBMValues.getRtgs_account());
        Log.d("DB-POST", storeCBMValues.getUsd_account());
        Log.d("DB-POST", storeCBMValues.getDedicated_account());
        Log.d("DB-POST", storeCBMValues.getHow_cash_kept());
        Log.d("DB-POST", storeCBMValues.getMode_of_payment_supplies());
        Log.d("DB-POST", storeCBMValues.getCash_payment_above_100());
        Log.d("DB-POST", storeCBMValues.getCash_book_updated());
        Log.d("DB-POST", storeCBMValues.getCash_book_balances());

    }

    private void updateQuestion(int current_question_index) {
        question_View.setText(questionCBS[current_question_index].getQuestion_id());
        question_header.setText(questionCBS[current_question_index].getQuestionHeader());
        user_answer.setText("");
    }

    private void closeActivityAndGoBack() {
        button_next.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button_close.setVisibility(View.VISIBLE);
            }
        },500);

    }

    private void storeValues(int index, String answer){

        switch(index){
            case 0:
                storeCBMValues.setBank_name(answer);
                break;
            case 1:
                storeCBMValues.setRtgs_account(answer);
                break;
            case 2:
                storeCBMValues.setUsd_account(answer);
                break;
            case 3:
                storeCBMValues.setDedicated_account(answer);
                break;
            case 4:
                storeCBMValues.setHow_cash_kept(answer);
                break;
            case 5:
                storeCBMValues.setMode_of_payment_supplies(answer);
                break;
            case 6:
                storeCBMValues.setCash_payment_above_100(answer);
                break;
            case 7:
                storeCBMValues.setCash_book_updated(answer);
                break;
            case 8:
                storeCBMValues.setCash_book_balances(answer);
                break;
                default:
                    Toast.makeText(CashBankManagement.this,"Information couldn't find appropriate variable",Toast.LENGTH_SHORT).show();
        }

    }

}
