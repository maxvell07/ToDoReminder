package malok.testtask.create_task.createTask.domain

import malok.testtask.core.domain.Task


interface CreateRepository {

    suspend fun createTask(task: Task)

}