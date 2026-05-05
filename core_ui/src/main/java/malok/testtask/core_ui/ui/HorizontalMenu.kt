package malok.testtask.core_ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import malok.testtask.core_ui.models.UiMenuItem

@Composable
fun HorizontalMenu(
    items: List<UiMenuItem>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            MenuItemCard(
                menuItem = item,
                onClick = {
                    onItemClick(item.route)
                }
            )
        }
    }
}