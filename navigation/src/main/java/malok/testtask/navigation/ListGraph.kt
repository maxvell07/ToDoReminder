package malok.testtask.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import malok.testtask.create_task.createTask.presentation.CreateScreen
import malok.testtask.detail.detail.presentation.DetailScreen
import malok.testtask.edit_task.editTask.presentation.EditTaskScreen
import malok.testtask.list_tasks.listTasks.presentation.ListScreen

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
            onEditClick = { id ->
                navController.navigate(ListRoute.EditTaskScreen(id))
            },
            onBack = { navController.navigateUp() }
        )
    }
    composable<ListRoute.EditTaskScreen> { backStackEntry ->
        val route = backStackEntry.toRoute<ListRoute.EditTaskScreen>()
        EditTaskScreen(
            id = route.id,
            onBack = { navController.navigateUp() }
        )
    }

    composable<ListRoute.CreateScreen> {
        CreateScreen(
            onBack = { navController.navigateUp() }
        )
    }
}