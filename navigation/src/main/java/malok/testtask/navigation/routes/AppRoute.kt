package malok.testtask.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {
    @Serializable
    data object AuthGraph : AppRoute

    @Serializable
    data object MainGraph : AppRoute
}