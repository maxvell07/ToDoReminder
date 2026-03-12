package malok.testtask.edit_task.editTask.presentation.model

import malok.testtask.core.domain.Task


internal data class EditUiState(
    val task: Task = Task(),
    val isLoading: Boolean = true,
    val error: String? = null
)
