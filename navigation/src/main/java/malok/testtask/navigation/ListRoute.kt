package malok.testtask.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface ListRoute {

    @Serializable
    data object ListScreen : ListRoute

    @Serializable
    data class DetailScreen(val id: String) : ListRoute

    @Serializable
    data class EditTaskScreen(val id: String) : ListRoute

    @Serializable
    class CreateScreen() : ListRoute
}