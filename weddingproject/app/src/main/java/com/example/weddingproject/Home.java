package com.example.weddingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


//imports for gesture
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;


public class Home extends AppCompatActivity {


    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    CheckBox CH1, CH2,CH3,CH4;
    Button homesubmit;
    String Username2;
    TextView welcomeTW;
    ImageView planA,planB,planC,planD;


    //implementatin for gestures




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        CH1=findViewById(R.id.planaCH);
        CH2=findViewById(R.id.planbCH);
        CH3=findViewById(R.id.plancCH);
        CH4=findViewById(R.id.plandCH);
        homesubmit=findViewById(R.id.HomeSubmit);
        welcomeTW = (TextView) findViewById(R.id.Welcome);



        //For gesture

        ImageView planA = (ImageView) findViewById(R.id.planaimg);
        ImageView planB = (ImageView) findViewById(R.id.planbimg);
        ImageView planC = (ImageView) findViewById(R.id.plancimg);
        ImageView planD = (ImageView) findViewById(R.id.plandimg);


        GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                planA.setScaleX(2);
                planA.setScaleY(2);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                planA.setScaleX(1);
                planA.setScaleY(1);
                return true;
            }
        });



        planA.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });



        GestureDetector gestureDetector2 = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                planB.setScaleX(2);
                planB.setScaleY(2);
            }
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                planB.setScaleX(1);
                planB.setScaleY(1);
                return true;
            }
        });



        planB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector2.onTouchEvent(motionEvent);
                return true;
            }
        });

        GestureDetector gestureDetector3 = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                planC.setScaleX(2);
                planC.setScaleY(2);
            }
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                planC.setScaleX(1);
                planC.setScaleY(1);
                return true;
            }
        });



        planC.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector3.onTouchEvent(motionEvent);
                return true;
            }
        });

        GestureDetector gestureDetector4 = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                planD.setScaleX(2);
                planD.setScaleY(2);
            }
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                planD.setScaleX(1);
                planD.setScaleY(1);
                return true;
            }
        });



        planD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector4.onTouchEvent(motionEvent);
                return true;
            }
        });










        //End of gesture

        //Getting data from main.activity with Intent
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Username2 = extras.getString("UserName");
            System.out.println(Username2);
            welcomeTW.setText(Username2);
        }




        //CheckBox control for Plans
        homesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CH1.isChecked()){
                    Toast.makeText(Home.this, "You choose plan A", Toast.LENGTH_SHORT).show();
                }
                else if(CH2.isChecked()){
                    Toast.makeText(Home.this, "You choose plan B", Toast.LENGTH_SHORT).show();
                }
                else if(CH3.isChecked()){
                    Toast.makeText(Home.this, "You choose plan C", Toast.LENGTH_SHORT).show();
                }
                else if(CH4.isChecked()){
                    Toast.makeText(Home.this, "You choose plan D", Toast.LENGTH_SHORT).show();
                }
                else if( !CH1.isChecked() || !CH2.isChecked() || !CH3.isChecked() || !CH4.isChecked())
                {
                    Toast.makeText(Home.this, "You dıdn't chose any plan Please chose one plan ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



    //Class for gesture










    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }



    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}

