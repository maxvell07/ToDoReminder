package malok.todoreminder.features.createTask.presentation.model

sealed interface CreateEffect {
    data class TaskCreated(val message: String) : CreateEffect
    data class ShowError(val message: String) : CreateEffect
    data class EmptyFields(val message: String) : CreateEffect
}