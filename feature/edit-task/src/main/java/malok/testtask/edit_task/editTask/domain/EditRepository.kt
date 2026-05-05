package malok.testtask.edit_task.editTask.domain

import malok.testtask.core.domain.Task


interface EditRepository {
    suspend fun getTaskById(taskId: Long): Task
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(taskId: Long)
}