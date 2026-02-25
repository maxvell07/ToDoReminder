package malok.testtask.list_tasks.listTasks.presentation.model

import malok.testtask.core.domain.Task


data class ListUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
