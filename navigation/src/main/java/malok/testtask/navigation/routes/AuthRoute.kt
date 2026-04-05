package malok.testtask.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRoute {
    @Serializable
    data object Onboarding : AuthRoute
}