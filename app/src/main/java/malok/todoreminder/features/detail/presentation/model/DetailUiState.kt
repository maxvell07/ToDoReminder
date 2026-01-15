package malok.todoreminder.features.detail.presentation.model

import malok.todoreminder.domain.Task

data class DetailUiState(
    val isLoading: Boolean = false,
    val task: Task = Task(),
    val error: String? = null
)