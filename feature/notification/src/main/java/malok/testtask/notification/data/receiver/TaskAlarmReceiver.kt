package malok.testtask.notification.data.receiver

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import org.koin.core.component.KoinComponent

class TaskAlarmReceiver : BroadcastReceiver(), KoinComponent {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ALARM_DEBUG", "RECEIVER TRIGGERED! onReceive called")
        val taskId = intent.getLongExtra(EXTRA_TASK_ID, -1L)
        val title = intent.getStringExtra(EXTRA_TASK_TITLE) ?: "Task"

        showNotification(context, taskId, title)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "tasks_channel",
                "Напоминания о задачах",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Канал для уведомлений о задачах"
                enableVibration(true)
            }

            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(context: Context, taskId: Long, title: String) {

        createNotificationChannel(context)

        val notification = NotificationCompat.Builder(context, "tasks_channel")
            .setContentTitle("Напоминание")
            .setContentText(title)
            .setSmallIcon(R.drawable.ic_menu_info_details)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(taskId.toInt(), notification)
    }

    companion object {
        const val ACTION_NOTIFY = "com.example.app.ACTION_NOTIFY"
        const val EXTRA_TASK_ID = "task_id"
        const val EXTRA_TASK_TITLE = "task_title"
    }
}