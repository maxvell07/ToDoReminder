package malok.todoreminder.features.listTasks.presentation.model

sealed interface ListEffect {
    data class OpenDetails(val id: String) : ListEffect
    data class ShowError(val message: String) : ListEffect
}