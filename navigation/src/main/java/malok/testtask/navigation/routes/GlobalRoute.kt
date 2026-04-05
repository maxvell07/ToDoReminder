package malok.testtask.navigation.routes


import kotlinx.serialization.Serializable

@Serializable
sealed interface GlobalRoute {
    @Serializable
    data class Detail(val id: String) : GlobalRoute

    @Serializable
    data class Edit(val id: String) : GlobalRoute

    @Serializable
    data object Create : GlobalRoute
}