package malok.todoreminder.features.createTask.presentation.model

import malok.todoreminder.domain.Task

data class CreateUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
