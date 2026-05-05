package malok.testtask.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed interface MainTabRoute {
    @Serializable
    data object List : MainTabRoute

    @Serializable
    data object Profile : MainTabRoute
}
