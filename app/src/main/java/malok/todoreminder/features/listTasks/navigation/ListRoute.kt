package malok.todoreminder.features.listTasks.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface ListRoute {

    @Serializable
    data object ListScreen : ListRoute

    @Serializable
    data class DetailsScreen(val id: String) : ListRoute
}