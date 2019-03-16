package renaissance.njujiaowu;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import renaissance.njujiaowu.MyPkg.MorningCourse;

import static renaissance.njujiaowu.clock_setting.pickedHour1;
import static renaissance.njujiaowu.clock_setting.pickedHour2;
import static renaissance.njujiaowu.clock_setting.pickedHour3;
import static renaissance.njujiaowu.clock_setting.pickedMinute1;
import static renaissance.njujiaowu.clock_setting.pickedMinute2;
import static renaissance.njujiaowu.clock_setting.pickedMinute3;

public class ClockActivity extends AppCompatActivity {
    TextView tvMonday;
    TextView tvTuesday;
    TextView tvWednesday;
    TextView tvThursday;
    TextView tvFriday;
    TextView clock1;
    TextView clock2;
    TextView clock3;
    TextView clock4;
    TextView clock5;
    int hour1, hour2, hour3, hour4, hour5;
    int min1, min2, min3, min4, min5;
    Button jump;

    Context mContext;

    // 开启服务按钮
    private Button startServiceBtn;
    // 取消服务按钮
    private Button cancelServiceBtn;

    // 模拟的task id
    private static int[] mTaskId = {0,1,2,3,4,5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        findView();
        setClock(clock1, MorningCourse.Monday); setAlarmTime(1,MorningCourse.Monday);
        setClock(clock2, MorningCourse.Tuesday); setAlarmTime(2,MorningCourse.Tuesday);
        setClock(clock3, MorningCourse.Wednesday); setAlarmTime(3,MorningCourse.Wednesday);
        setClock(clock4, MorningCourse.Thrusday); setAlarmTime(4,MorningCourse.Thrusday);
        setClock(clock5, MorningCourse.Friday); setAlarmTime(5,MorningCourse.Friday);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClockActivity.this, clock_setting.class);
                startActivity(i);
            }
        });

        startServiceBtn = (Button) findViewById(R.id.start);
        cancelServiceBtn = (Button) findViewById(R.id.finish);

//        startServiceBtn.setOnClickListener(this);
//        cancelServiceBtn.setOnClickListener(this);
        startServiceBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClockActivity.this, AlarmService.class);
                // 获取20秒之后的日期时间字符串
                Log.d("Click", "heheh");
                i.putExtra("alarm_time",
                        DateTimeUtil.getNLaterDateTimeString(Calendar.SECOND, 5));
                i.putExtra("task_id", mTaskId[0]);
                startService(i);
                if(hour1!=0) {
                    i.putExtra("alarm_time",
                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour1, min1, 1));
                    i.putExtra("task_id", mTaskId[1]);
                    startService(i);
                }

                if(hour2!=0) {
                    i.putExtra("alarm_time",
                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour2, min2, 2));
                    i.putExtra("task_id", mTaskId[2]);
                    startService(i);
                }

                if(hour3!=0) {
                    i.putExtra("alarm_time",
                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour3, min3, 3));
                    i.putExtra("task_id", mTaskId[3]);
                    startService(i);
                }

                if(hour4!=0) {
                    i.putExtra("alarm_time",
                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour4, min4, 4));
                    i.putExtra("task_id", mTaskId[4]);
                    startService(i);
                }

                if(hour5!=0) {
                    i.putExtra("alarm_time",
                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour5, min5, 5));
                    i.putExtra("task_id", mTaskId[5]);
                    startService(i);
                }
            }
        });

        cancelServiceBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hour1!=0)
                    AlarmManagerUtil.cancelAlarmBroadcast(ClockActivity.this, mTaskId[1],
                            AlarmReceiver.class);
                if(hour2!=0)
                    AlarmManagerUtil.cancelAlarmBroadcast(ClockActivity.this, mTaskId[2],
                            AlarmReceiver.class);
                if(hour3!=0)
                    AlarmManagerUtil.cancelAlarmBroadcast(ClockActivity.this, mTaskId[3],
                            AlarmReceiver.class);
                if(hour4!=0)
                    AlarmManagerUtil.cancelAlarmBroadcast(ClockActivity.this, mTaskId[4],
                            AlarmReceiver.class);
                if(hour5!=0)
                    AlarmManagerUtil.cancelAlarmBroadcast(ClockActivity.this, mTaskId[5],
                            AlarmReceiver.class);
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.start:
//                Intent i = new Intent(this, AlarmService.class);
//                // 获取20秒之后的日期时间字符串
//                Log.d("问题", "heheh");
//                i.putExtra("alarm_time",
//                        DateTimeUtil.getNLaterDateTimeString(Calendar.SECOND, 5));
//                i.putExtra("task_id", mTaskId[0]);
//                startService(i);
//                if(hour1!=0) {
//                    i.putExtra("alarm_time",
//                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour1, min1, 1));
//                    i.putExtra("task_id", mTaskId[1]);
//                    startService(i);
//                }
//
//                if(hour2!=0) {
//                    i.putExtra("alarm_time",
//                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour2, min2, 2));
//                    i.putExtra("task_id", mTaskId[2]);
//                    startService(i);
//                }
//
//                if(hour3!=0) {
//                    i.putExtra("alarm_time",
//                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour3, min3, 3));
//                    i.putExtra("task_id", mTaskId[3]);
//                    startService(i);
//                }
//
//                if(hour4!=0) {
//                    i.putExtra("alarm_time",
//                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour4, min4, 4));
//                    i.putExtra("task_id", mTaskId[4]);
//                    startService(i);
//                }
//
//                if(hour5!=0) {
//                    i.putExtra("alarm_time",
//                            DateTimeUtil.getNLaterDateTimeStringUsingData(hour5, min5, 5));
//                    i.putExtra("task_id", mTaskId[5]);
//                    startService(i);
//                }
//                break;
//            case R.id.finish:
//                if(hour1!=0)
//                AlarmManagerUtil.cancelAlarmBroadcast(this, mTaskId[1],
//                        AlarmReceiver.class);
//                if(hour2!=0)
//                AlarmManagerUtil.cancelAlarmBroadcast(this, mTaskId[2],
//                        AlarmReceiver.class);
//                if(hour3!=0)
//                AlarmManagerUtil.cancelAlarmBroadcast(this, mTaskId[3],
//                        AlarmReceiver.class);
//                if(hour4!=0)
//                AlarmManagerUtil.cancelAlarmBroadcast(this, mTaskId[4],
//                        AlarmReceiver.class);
//                if(hour5!=0)
//                AlarmManagerUtil.cancelAlarmBroadcast(this, mTaskId[5],
//                        AlarmReceiver.class);
//                break;
//            default:
//                break;
//        }
//    }

    private void findView() {
        tvMonday = (TextView) findViewById(R.id.tv_monday);
        tvTuesday = (TextView) findViewById(R.id.tv_tuesday);
        tvWednesday = (TextView) findViewById(R.id.tv_wednesday);
        tvThursday = (TextView) findViewById(R.id.tv_thursday);
        tvFriday = (TextView) findViewById(R.id.tv_friday);
        clock1 = (TextView) findViewById(R.id.clock_morning);
        clock2 = (TextView) findViewById(R.id.clock_morning2);
        clock3 = (TextView) findViewById(R.id.clock_morning3);
        clock4 = (TextView) findViewById(R.id.clock_morning4);
        clock5 = (TextView) findViewById(R.id.clock_morning5);
        jump = (Button) findViewById(R.id.button1);
    }

    void setAlarmTime(int num, int type) {
        if (num == 1) {
            if(type==1){
                hour1 = pickedHour1;
                min1 = pickedMinute1;
            }else if(type==2){
                hour1 = pickedHour2;
                min1 = pickedMinute2;
            }else if(type==3){
                hour1 = pickedHour3;
                min1 = pickedMinute3;
            }else{
                hour1=0;
                min1=0;
            }
            }
        else if(num==2) {
            if (type == 1) {
                hour2 = pickedHour1;
                min2 = pickedMinute1;
            } else if (type == 2) {
                hour2 = pickedHour2;
                min2 = pickedMinute2;
            } else if(type==3){
                hour2 = pickedHour3;
                min2 = pickedMinute3;
            }else{
                hour2=0;
                min2=0;
            }
        }else if(num==3) {
            if (type == 1) {
                hour3 = pickedHour1;
                min3 = pickedMinute1;
            } else if (type == 2) {
                hour3 = pickedHour2;
                min3 = pickedMinute2;
            } else if(type==3) {
                hour3 = pickedHour3;
                min3 = pickedMinute3;
            }else{
                hour3=0;
                min3=0;
            }
        }else if(num==4) {
            if (type == 1) {
                hour4 = pickedHour1;
                min4 = pickedMinute1;
            } else if (type == 2) {
                hour4 = pickedHour2;
                min4 = pickedMinute2;
            } else if(type==3){
                hour4 = pickedHour3;
                min4 = pickedMinute3;
            }else{
                hour4=0;
                min4=0;
            }
        }else{
            if (type == 1) {
                hour5 = pickedHour1;
                min5 = pickedMinute1;
            } else if (type == 2) {
                hour5 = pickedHour2;
                min5 = pickedMinute2;
            } else if(type==3){
                hour5 = pickedHour3;
                min5 = pickedMinute3;
            }else{
                hour5=0;
                min5=0;
            }
            }
    }

    void setClock(TextView test, int type) {
        if (type == 1) {
            if (pickedMinute1 < 10)
                test.setText("上午: " + pickedHour1 + ":0" + pickedMinute1);
            else
                test.setText("上午: " + pickedHour1 + ":" + pickedMinute1);
        } else if(type == 2){
            if (pickedMinute2 < 10)
                test.setText("上午: " + pickedHour2 + ":0" + pickedMinute2);
            else
                test.setText("上午: " + pickedHour2 + ":" + pickedMinute2);
        }else if(type==3){
            if (pickedMinute3 < 10)
                test.setText("上午: " + pickedHour3 + ":0" + pickedMinute3);
            else
                test.setText("上午: " + pickedHour3 + ":" + pickedMinute3);
        }else{
            test.setText("无闹钟");
        }
    }
}
