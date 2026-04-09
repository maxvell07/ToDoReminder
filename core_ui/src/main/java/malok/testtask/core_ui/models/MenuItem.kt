package malok.testtask.core_ui.models

import androidx.compose.ui.graphics.vector.ImageVector

data class UiMenuItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val description: String? = null
)