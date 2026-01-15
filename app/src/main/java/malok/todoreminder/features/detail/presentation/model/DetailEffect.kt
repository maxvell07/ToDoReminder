package malok.todoreminder.features.detail.presentation.model

sealed interface DetailEffect {
    data class ShowError(val id: String) : DetailEffect
}