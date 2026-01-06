package malok.todoreminder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import malok.todoreminder.features.listTasks.navigation.ListRoute
import malok.todoreminder.features.listTasks.navigation.listGraph

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ListRoute.ListScreen
    ) {
        listGraph(navController)
    }
}
