package malok.todoreminder.features.createTask.domain

import malok.todoreminder.domain.Task

interface CreateRepository {

    suspend fun createTask(task: Task)

}