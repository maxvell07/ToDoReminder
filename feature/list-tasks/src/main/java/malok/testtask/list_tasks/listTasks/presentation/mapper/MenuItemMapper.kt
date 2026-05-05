package malok.testtask.list_tasks.listTasks.presentation.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import malok.testtask.core.domain.model.MenuItem
import malok.testtask.core_ui.models.UiMenuItem

fun MenuItem.toUiModel(): UiMenuItem = UiMenuItem(
    title = title,
    icon = when (iconRes) {
        "person"       -> Icons.Default.Person
        "article"      -> Icons.Default.Article
        "check_circle" -> Icons.Default.CheckCircle
        else           -> Icons.Default.Android
    },
    route = route,
    description = description
)