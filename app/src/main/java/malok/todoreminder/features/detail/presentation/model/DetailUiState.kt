package malok.todoreminder.features.detail.presentation.model

import malok.todoreminder.domain.Task

data class DetailUiState(
    val isLoading: Boolean = false,
    val task: Task? = null,
    val error: String? = null
)