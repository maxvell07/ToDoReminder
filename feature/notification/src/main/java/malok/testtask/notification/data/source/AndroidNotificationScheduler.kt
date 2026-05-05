package malok.testtask.notification.data.source

import malok.testtask.core.domain.Task
import malok.testtask.notification.data.receiver.TaskAlarmReceiver
import malok.testtask.notification.domain.NotificationScheduler
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class AndroidNotificationScheduler(
    private val context: Context,
    private val alarmManager: AlarmManager
) : NotificationScheduler {

    override suspend fun schedule(task: Task) {
        // Проверка разрешений Android 12+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                throw SecurityException("Permission SCHEDULE_EXACT_ALARM not granted")
            }
        }

        val intent = Intent(context, TaskAlarmReceiver::class.java).apply {
            action = TaskAlarmReceiver.ACTION_NOTIFY
            putExtra(TaskAlarmReceiver.EXTRA_TASK_ID, task.id)
            putExtra(TaskAlarmReceiver.EXTRA_TASK_TITLE, task.title)
            `package` = context.packageName
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            task.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        Log.d("ALARM_DEBUG", "AndroidNotificationScheduler: Starting for Task ID ${task.id}, Time ${task.date}")
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            task.date,
            pendingIntent
        )
        Log.d("ALARM_DEBUG", "Alarm set via AlarmManager")
    }

    override suspend fun cancel(taskId: Long) {
        // Логика отмены
    }
}