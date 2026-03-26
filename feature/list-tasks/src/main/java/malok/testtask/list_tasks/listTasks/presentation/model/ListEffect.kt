package malok.testtask.list_tasks.listTasks.presentation.model

internal sealed interface ListEffect {
    data class OpenDetails(val id: String) : ListEffect
    data class OpenEditTask(val id: String) : ListEffect
    class OpenCreateTask() : ListEffect
    data class ShowError(val message: String) : ListEffect
}