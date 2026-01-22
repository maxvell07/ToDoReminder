package malok.todoreminder.features.createTask.presentation.model

import malok.todoreminder.domain.Task

data class CreateUiState(
    val task: Task = Task(),
    val isLoading: Boolean = false,
    val error: String? = null
)
