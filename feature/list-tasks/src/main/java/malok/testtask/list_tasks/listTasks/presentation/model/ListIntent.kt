package malok.testtask.list_tasks.listTasks.presentation.model

internal sealed interface ListIntent {
    data class OpenNetworkResource (val path: String): ListIntent
    object LoadTasks : ListIntent
    data class ItemClicked(val id: String) : ListIntent
    data class onDelete(val id: String) : ListIntent
    data class onEdit(val id: String) : ListIntent
    data class TaskChecked(val id: String, val checked: Boolean) : ListIntent
}
