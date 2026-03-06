package malok.testtask.detail.detail.presentation.model

import malok.testtask.core.domain.Task


data class DetailUiState(
    val isLoading: Boolean = false,
    val task: Task = Task(),
    val error: String? = null
)