package malok.testtask.features.editTask.presentation.model

sealed interface EditIntent {
    data class TitleChanged(val value: String) : EditIntent
    data class DescriptionChanged(val value: String) : EditIntent
    data class TimeChanged(val value: Long) : EditIntent
    object SaveTask : EditIntent
}
