package malok.todoreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import malok.todoreminder.navigation.AppNavHost
import malok.todoreminder.ui.theme.ToDoReminderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ToDoReminderTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}