package renaissance.njujiaowu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.util.Log;

public class BootCompleteReceiver extends BroadcastReceiver {
    // 模拟的task id
    private static int mTaskId = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("定时服务", "开机启动");
        ToastUtil.showShort(context, "定时服务开机启动");
        Intent i = new Intent(context, AlarmService.class);
        // 获取3分钟之后的日期时间字符串
        i.putExtra("alarm_time",
                DateTimeUtil.getNLaterDateTimeString(Calendar.MINUTE, 3));
        i.putExtra("task_id", mTaskId);
        context.startService(i);
    }
}
