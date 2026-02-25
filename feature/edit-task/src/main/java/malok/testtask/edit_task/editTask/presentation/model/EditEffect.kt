package malok.testtask.edit_task.editTask.presentation.model

sealed interface EditEffect {
    object TaskSaved : EditEffect
    data class ShowError(val message: String) : EditEffect

}