package malok.testtask.detail.detail.presentation.model

internal sealed interface DetailEffect {
    data class ShowError(val message: String) : DetailEffect
    data class OpenEditScreen(val id: String) : DetailEffect
}