package malok.todoreminder.features.createTask.presentation.model

import malok.todoreminder.domain.Task

sealed interface CreateIntent {
    data class CreateTask(val task: Task): CreateIntent
}
