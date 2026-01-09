package malok.todoreminder.features.listTasks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import malok.todoreminder.features.createTask.presentation.CreateScreen
import malok.todoreminder.features.detail.presentation.DetailScreen
import malok.todoreminder.features.listTasks.presentation.ui.ListScreen

fun NavGraphBuilder.listGraph(
    navController: NavController
) {
    composable<ListRoute.ListScreen> {
        ListScreen(
            onItemClick = { id ->
                navController.navigate(ListRoute.DetailScreen(id))
            },
            onFloatButtonClick = {
                navController.navigate(ListRoute.CreateScreen())
            }
        )
    }
    composable<ListRoute.DetailScreen> { backStackEntry ->
        val route = backStackEntry.toRoute<ListRoute.DetailScreen>()
        DetailScreen(
            id = route.id,
            onBack = { navController.popBackStack() }
        )
    }
    composable<ListRoute.CreateScreen> { backStackEntry ->
        val route = backStackEntry.toRoute<ListRoute.CreateScreen>()
        CreateScreen(
            onBack = { navController.popBackStack() }
        )
    }
}