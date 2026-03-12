package malok.testtask.create_task.createTask.presentation.model

internal sealed interface CreateEffect {
    data class TaskCreated(val message: String) : CreateEffect
    data class ShowError(val message: String) : CreateEffect
    data class EmptyFields(val message: String) : CreateEffect
}