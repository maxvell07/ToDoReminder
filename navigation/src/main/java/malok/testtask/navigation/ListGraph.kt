package malok.testtask.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import malok.testtask.create_task.createTask.presentation.CreateScreen
import malok.testtask.detail.detail.presentation.DetailScreen
import malok.testtask.edit_task.editTask.presentation.EditTaskScreen
import malok.testtask.list_tasks.listTasks.presentation.ListScreen
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.animation.core.tween

fun NavGraphBuilder.listGraph(
    navController: NavController
) {
    composable<ListRoute.ListScreen> {
        ListScreen(
            onItemClick = { id -> navController.navigate(ListRoute.DetailScreen(id)) },
            onFloatButtonClick = { navController.navigate(ListRoute.CreateScreen()) },
            onEditSwipe = { id ->
                navController.navigate(ListRoute.EditTaskScreen(id)) {
                    launchSingleTop = true
                }
            }
        )
    }
    composable<ListRoute.DetailScreen>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(450, easing = FastOutSlowInEasing)
            ) + fadeIn(tween(350))
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(450, easing = FastOutSlowInEasing)
            ) + fadeOut(tween(350))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start) + fadeOut()
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End) + fadeIn()
        }
    ) { backStackEntry ->
        val route = backStackEntry.toRoute<ListRoute.DetailScreen>()
        DetailScreen(
            id = route.id,
            onEditClick = { id -> navController.navigate(ListRoute.EditTaskScreen(id)) },
            onBack = { navController.navigateUp() }
        )
    }

    composable<ListRoute.CreateScreen>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                tween(500, easing = LinearOutSlowInEasing)
            ) + fadeIn(tween(300))
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                tween(500, easing = FastOutLinearInEasing)
            ) + fadeOut(tween(300))
        },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        CreateScreen(onBack = { navController.navigateUp() })
    }

    composable<ListRoute.EditTaskScreen>(
        enterTransition = {
            scaleIn(initialScale = 0.94f, animationSpec = tween(400)) +
                    fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            scaleOut(targetScale = 0.94f, animationSpec = tween(380)) +
                    fadeOut(animationSpec = tween(280))
        }
    ) { backStackEntry ->
        val route = backStackEntry.toRoute<ListRoute.EditTaskScreen>()
        EditTaskScreen(
            id = route.id,
            onBack = { navController.navigateUp() },
            onBackHome = {navController.navigate(ListRoute.ListScreen)}
        )
    }
}