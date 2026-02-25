package malok.testtask.features.editTask.presentation.model

import malok.testtask.core.domain.Task


data class EditUiState(
    val task: Task = Task(),
    val isLoading: Boolean = true,
    val error: String? = null
)
