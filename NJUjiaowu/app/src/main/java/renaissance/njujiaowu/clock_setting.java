package renaissance.njujiaowu;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class clock_setting extends AppCompatActivity {
    TextView timechoice1; TextView timechoice2; TextView timechoice3;
    TextView screentime1; TextView screentime2; TextView screentime3;
    public static int pickedHour1 = 7 ,pickedMinute1 = 0;
    public static int pickedHour2 = 8 ,pickedMinute2 = 0;
    public static int pickedHour3 = 9 ,pickedMinute3 = 0;
    //public static int pickedHour4 = 13 , pickedMinute4 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_setting);
        findView();
        timechoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker1();
            }
        });
        timechoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker2();
            }
        });
        timechoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker3();
            }
        });
    }

    private void setTime(TextView show, int type){
        if(type==1){
            if(pickedMinute1<10)
                show.setText(pickedHour1+":"+pickedMinute1+"0");
            else
                show.setText(pickedHour1+":"+pickedMinute1);
        }else if(type==2){
            if(pickedMinute2<10)
                show.setText(pickedHour2+":"+pickedMinute2+"0");
            else
                show.setText(pickedHour2+":"+pickedMinute2);
        }else {
            if(pickedMinute3<10)
                show.setText(pickedHour3+":"+pickedMinute3+"0");
            else
                show.setText(pickedHour3+":"+pickedMinute3);
        }
    }

    private void showTimePicker1(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(clock_setting.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                pickedHour1 = hourOfDay;
                pickedMinute1 = minute;
                setTime(screentime1,1);
            }
        },0,0,true);
        timePickerDialog.show();
    }

    private void showTimePicker2(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(clock_setting.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                pickedHour2 = hourOfDay;
                pickedMinute2 = minute;
                setTime(screentime2,2);
            }
        },0,0,true);
        timePickerDialog.show();
    }

    private void showTimePicker3(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(clock_setting.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                pickedHour3 = hourOfDay;
                pickedMinute3 = minute;
                setTime(screentime3,3);
            }
        },0,0,true);
        timePickerDialog.show();
    }


    private void findView(){
        timechoice1 = (TextView) findViewById(R.id.choice1);
        timechoice2 = (TextView) findViewById(R.id.choice2);
        timechoice3 = (TextView) findViewById(R.id.choice3);
        screentime1 = (TextView) findViewById(R.id.time1);
        screentime2 = (TextView) findViewById(R.id.time2);
        screentime3 = (TextView) findViewById(R.id.time3);
    }
}
