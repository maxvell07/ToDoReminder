package malok.testtask.core_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomNavBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf(
        Icons.Default.Home to "Главная",
        Icons.Default.Person to "Профиль"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithContent {

                drawContent()

                val radius = 30.dp.toPx()
                val centerX = size.width / 2
                val centerY = 0f

                drawCircle(
                    color = Color.Transparent,
                    radius = radius,
                    center = Offset(centerX, centerY),
                    blendMode = BlendMode.Clear
                )
            }
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onItemSelected(0) }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = items[0].first,
                    contentDescription = null,
                    tint = if (selectedItem == 0)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }

            IconButton(onClick = { onItemSelected(1) }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = items[1].first,
                    contentDescription = null,
                    tint = if (selectedItem == 1)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}

