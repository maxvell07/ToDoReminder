package malok.testtask.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import malok.testtask.navigation.routes.AppRoute
import malok.testtask.navigation.routes.AuthRoute
import malok.testtask.onboarding.presentation.OnboardingScreen

fun NavGraphBuilder.authGraph(
    onFinish: () -> Unit
) {
    navigation<AppRoute.AuthGraph>(
        startDestination = AuthRoute.Onboarding
    ){
        composable<AuthRoute.Onboarding> {
            OnboardingScreen(onFinish=onFinish)
        }
    }
}