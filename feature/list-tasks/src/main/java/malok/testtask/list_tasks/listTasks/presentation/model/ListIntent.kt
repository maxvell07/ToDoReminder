package malok.testtask.list_tasks.listTasks.presentation.model

sealed interface ListIntent {
    object LoadTasks : ListIntent
    data class ItemClicked(val id: String) : ListIntent
    object AddButtonClicked : ListIntent
    data class TaskChecked(val id: String, val checked: Boolean) : ListIntent
}
