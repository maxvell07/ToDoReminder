package malok.testtask.notification.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import malok.testtask.core.domain.repositories.TaskRepository
import malok.testtask.notification.domain.NotificationScheduler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RestoreAlarmsWorker( //TODO надо подключить
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {

    private val repository: TaskRepository by inject()
    private val scheduler: NotificationScheduler by inject()

    override suspend fun doWork(): Result {
        return try {
            val now = System.currentTimeMillis()
            val tasks = repository.getTasksWithDueTimeAfter(now)

            tasks.forEach { task ->
                scheduler.schedule(task)
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}