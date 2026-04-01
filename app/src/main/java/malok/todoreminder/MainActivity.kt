package malok.todoreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import malok.testtask.navigation.AppNavHost
import malok.todoreminder.core.RequestNotificationPermission
import malok.todoreminder.presentation.AppViewModel
import malok.todoreminder.ui.theme.ToDoReminderTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RequestNotificationPermission()
            val viewModel: AppViewModel = koinViewModel()
            val theme = viewModel.theme.collectAsStateWithLifecycle()
            ToDoReminderTheme(darkTheme = theme.value) {

                val navController = rememberNavController()
                val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()
                    AppNavHost(navController, startDestination)
            }
        }
    }
}