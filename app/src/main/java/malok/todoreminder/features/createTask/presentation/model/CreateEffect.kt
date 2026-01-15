package malok.todoreminder.features.createTask.presentation.model

sealed interface CreateEffect {
    object TaskCreated : CreateEffect
    data class ShowError(val message: String) : CreateEffect
}