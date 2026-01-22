package malok.todoreminder.features.listTasks.presentation.model

sealed interface ListEffect {
    data class OpenDetails(val id: String) : ListEffect
    data class OpenEditScreen(val id: String) : ListEffect
    class OpenCreateTask() : ListEffect
    data class ShowError(val message: String) : ListEffect
}