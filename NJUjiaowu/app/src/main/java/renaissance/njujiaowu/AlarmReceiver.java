package renaissance.njujiaowu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // ToastUtil.showShort(context,
        //     "从服务启动广播：at " + DateTimeUtil.getCurrentDateTimeString());
        //Log.d("Alarm", "从服务启动广播：at " + DateTimeUtil.getCurrentDateTimeString());
        //Toast.makeText(MainActivity.getApplicationContext(), "默认Toast样式",Toast.LENGTH_SHORT).show();
        Intent mIntent = new Intent(context, audio.class);
        intent.setFlags(mIntent.FLAG_ACTIVITY_NEW_TASK);
        Log.d("问题", "heheh");
        context.startActivity(mIntent);
    }

}
