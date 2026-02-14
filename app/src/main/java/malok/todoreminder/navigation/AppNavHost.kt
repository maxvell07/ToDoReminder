package malok.todoreminder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import malok.todoreminder.features.listTasks.navigation.ListRoute
import malok.todoreminder.features.listTasks.navigation.listGraph

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ListRoute.ListScreen
    ) {
        listGraph(navController)
    }
}
