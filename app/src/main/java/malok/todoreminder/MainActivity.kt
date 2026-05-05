package malok.todoreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import malok.testtask.navigation.AppNavHost
import malok.todoreminder.core.RequestNotificationPermission
import malok.todoreminder.presentation.AppViewModel
import malok.todoreminder.ui.theme.ToDoReminderTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        installSplashScreen().setKeepOnScreenCondition {
            viewModel.theme.value == null || viewModel.startDestination.value == null
        }
        setContent {
            RequestNotificationPermission()

            val theme by viewModel.theme.collectAsStateWithLifecycle()
            val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()

            if (theme == null || startDestination == null) return@setContent
            ToDoReminderTheme(darkTheme = theme!!) {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                    startDestination = startDestination!!
                )
            }
        }
    }
}