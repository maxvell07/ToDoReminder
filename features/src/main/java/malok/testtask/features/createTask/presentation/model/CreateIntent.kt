package malok.testtask.features.createTask.presentation.model

sealed interface CreateIntent {
    data class TitleChanged(val value: String) : CreateIntent
    data class DescriptionChanged(val value: String) : CreateIntent
    data class TimeChanged(val value: Long) : CreateIntent
    object  CreateTask: CreateIntent
}
