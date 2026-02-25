package malok.testtask.features.createTask.presentation.model

import malok.testtask.core.domain.Task


data class CreateUiState(
    val task: Task = Task(),
    val isLoading: Boolean = false,
    val error: String? = null
)
