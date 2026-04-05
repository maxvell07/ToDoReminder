package malok.testtask.navigation.graphs


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import malok.testtask.navigation.routes.AppRoute
import malok.testtask.navigation.screen.MainScreen

fun NavGraphBuilder.mainGraph(
    appNavController: NavController
) {
    composable<AppRoute.MainGraph> {
        MainScreen(appNavController = appNavController)
    }
}