package malok.todoreminder.features.detail.presentation.model

sealed interface DetailIntent {
    data class LoadTask(val id: Long) : DetailIntent
    data class EditClicked(val id: String) : DetailIntent
    data class DeleteTask(val id: Long) : DetailIntent
    data class TaskChecked(val id: Long, val checked: Boolean) : DetailIntent
}