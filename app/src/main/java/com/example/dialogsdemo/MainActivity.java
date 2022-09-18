package com.example.dialogsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDatePickerDialog,btnTimePickerDialog,btnAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initViews(){

        btnDatePickerDialog = findViewById(R.id.btnDatePickerDialog);
        btnTimePickerDialog = findViewById(R.id.btnTimePickerDialog);
        btnAlertDialog = findViewById(R.id.btnAlertDialog);

    }

    private void initListeners(){

        btnDatePickerDialog.setOnClickListener(new BtnDatePickerDialogClickListener());
        btnTimePickerDialog.setOnClickListener(new BtnTimePickerDialogClickListener());
        btnAlertDialog.setOnClickListener(new BtnAlertDialogClickListener());

    }

    private void makeToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }

    private class BtnAlertDialogClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("BitCode Pune");
            builder.setIcon(R.mipmap.ic_launcher_round);
            builder.setMessage("Are you sure you want end th exam?");

            DialogInterface.OnClickListener listener = new AlertDialogButtonsClickListener();
            builder.setPositiveButton("Yes",listener);
            builder.setNegativeButton("No",listener);
            builder.setNeutralButton("Cancel",listener);

            builder.setOnCancelListener(new LogOutDialogCancelClickListener());
            builder.setOnDismissListener(new LogOutDialogDismissClickListener());

            AlertDialog logoutDialog = builder.create();
            logoutDialog.show();
        }
    }

    private class AlertDialogButtonsClickListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_POSITIVE){
                makeToast("Yes Clicked");
            }

            if(which == DialogInterface.BUTTON_NEGATIVE){
                makeToast("No Clicked");
            }

            if(which == DialogInterface.BUTTON_NEUTRAL){
                makeToast("Cancel Clicked");
            }
        }
    }

    private class LogOutDialogCancelClickListener implements DialogInterface.OnCancelListener{

        @Override
        public void onCancel(DialogInterface dialog) {
            makeToast("On Cancel Clicked");
        }
    }

    private class LogOutDialogDismissClickListener implements DialogInterface.OnDismissListener{
        @Override
        public void onDismiss(DialogInterface dialog) {
            makeToast("On Dismiss Clicked");
        }
    }

    private class BtnDatePickerDialogClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    new MyOnDateSetListener(),
                    2001,
                    8,
                    03
            );

            datePickerDialog.show();
        }
    }

    private class MyOnDateSetListener implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            btnDatePickerDialog.setText(year+"/"+(month+1)+"/"+dayOfMonth);
        }
    }

    private class BtnTimePickerDialogClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    MainActivity.this,
                    new MyOnTimeSetListener(),
                    22,
                    57,
                    false
            );

            timePickerDialog.show();
        }
    }

    private class MyOnTimeSetListener implements TimePickerDialog.OnTimeSetListener{
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            btnTimePickerDialog.setText(hourOfDay+":"+minute);
        }
    }
}