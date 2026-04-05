package malok.testtask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import malok.testtask.navigation.graphs.authGraph
import malok.testtask.navigation.graphs.globalGraph
import malok.testtask.navigation.graphs.mainGraph
import malok.testtask.navigation.routes.AppRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: AppRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(400)
            ) + fadeIn(tween(400))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(400)
            ) + fadeOut(tween(400))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(400)
            ) + fadeIn(tween(400))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(400)
            ) + fadeOut(tween(400))
        }
    ) {
        authGraph(
            onFinish = {
                navController.navigate(AppRoute.MainGraph) {
                    popUpTo(AppRoute.AuthGraph) { inclusive = true }
                }
            }
        )
        mainGraph (appNavController = navController)
        globalGraph (navController = navController)
    }
}
