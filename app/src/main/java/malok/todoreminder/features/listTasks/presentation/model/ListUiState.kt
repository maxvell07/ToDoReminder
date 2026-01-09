package malok.todoreminder.features.listTasks.presentation.model

import malok.todoreminder.domain.Task

data class ListUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
