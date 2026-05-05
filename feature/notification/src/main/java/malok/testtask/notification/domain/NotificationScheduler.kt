package malok.testtask.notification.domain

import malok.testtask.core.domain.Task

interface NotificationScheduler {
    suspend fun schedule(task: Task)
    suspend fun cancel(taskId: Long)
}