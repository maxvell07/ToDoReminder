package malok.todoreminder.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4F9EFF),        // яркий синий — FAB, акценты
    onPrimary = Color(0xFF000000),      // иконка на FAB
    secondary = Color(0xFF7B61FF),      // фиолетовый акцент
    background = Color(0xFF0F1117),     // почти чёрный фон
    surface = Color(0xFF1A1D27),        // ботомбар чуть светлее фона
    onSurface = Color(0xFFE0E0E0),      // иконки навигации
    onBackground = Color(0xFFFFFFFF),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2563EB),        // насыщенный синий — FAB, акценты
    onPrimary = Color(0xFFFFFFFF),      // иконка на FAB
    secondary = Color(0xFF7C3AED),      // фиолетовый акцент
    background = Color(0xFFF0F2F8),     // мягкий серо-голубой фон
    surface = Color(0xFFFFFFFF),        // ботомбар белый
    onSurface = Color(0xFF374151),      // иконки навигации тёмно-серые
    onBackground = Color(0xFF111827),
)

@Composable
fun ToDoReminderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}