package malok.testtask.core.domain.model

data class MenuItem(
    val title: String,
    val iconRes: String,
    val route: String,
    val description: String? = null
)