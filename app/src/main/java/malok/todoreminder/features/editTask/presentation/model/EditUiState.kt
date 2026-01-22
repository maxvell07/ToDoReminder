package malok.todoreminder.features.editTask.presentation.model

import malok.todoreminder.domain.Task

data class EditUiState(
    val task: Task = Task(),
    val isLoading: Boolean = true,
    val error: String? = null
)
