package malok.todoreminder.features.listTasks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import malok.todoreminder.features.detail.presentation.DetailsScreen
import malok.todoreminder.features.listTasks.presentation.ui.ListScreen

fun NavGraphBuilder.listGraph(
    navController: NavController
) {
    composable<ListRoute.ListScreen> {
        ListScreen(
            onItemClick = { id ->
                navController.navigate(ListRoute.DetailsScreen(id))
            }
        )
    }
    composable<ListRoute.DetailsScreen> { backStackEntry ->
        val route = backStackEntry.toRoute<ListRoute.DetailsScreen>()
        DetailsScreen(
            id = route.id,
            onBack = { navController.popBackStack() }
        )
    }
}
