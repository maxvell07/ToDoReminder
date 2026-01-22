package malok.todoreminder.features.editTask.domain

import malok.todoreminder.domain.Task

interface EditRepository {
    suspend fun getTaskById(taskId: Long): Task
    suspend fun updateTask(task: Task)
}