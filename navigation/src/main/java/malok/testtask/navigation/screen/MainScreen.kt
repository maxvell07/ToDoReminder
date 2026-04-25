package malok.testtask.navigation.screen


import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import malok.testtask.core_ui.ui.CustomBottomNavBar
import malok.testtask.list_tasks.listTasks.presentation.ListScreen
import malok.testtask.navigation.routes.GlobalRoute
import malok.testtask.navigation.routes.MainTabRoute
import malok.testtask.profile.presentation.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(appNavController: NavController ) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val snackbar = remember { SnackbarHostState() }
    var selectedTab = when {
        currentDestination?.hierarchy?.any {
            it.hasRoute(MainTabRoute.List::class)
        } == true -> 0

        currentDestination?.hierarchy?.any {
            it.hasRoute(MainTabRoute.Profile::class)
        } == true -> 1
        else -> 0
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(hostState = snackbar)
        },
        topBar = {
            TopAppBar(
                title = {
                    val title = when (selectedTab) {
                        0 -> "List Tasks"
                        1 -> "Profile"
                        else -> ""
                    }
                    Text(title, color = MaterialTheme.colorScheme.onSurface)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
                FloatingActionButton(
                    onClick = { appNavController.navigate(GlobalRoute.Create) },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(50.dp)
                        .offset(y = 42.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Добавить задачу")
                }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            CustomBottomNavBar(
                selectedItem = selectedTab,
                onItemSelected = { index ->
                    selectedTab = index
                    when (index) {
                        0 -> bottomNavController.navigate(MainTabRoute.List) {
                            popUpTo(bottomNavController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                        1 -> bottomNavController.navigate(MainTabRoute.Profile) {
                            popUpTo(bottomNavController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = MainTabRoute.List,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<MainTabRoute.List> {
                ListScreen(
                    modifier = Modifier,
                    onItemClick = { id ->
                        appNavController.navigate(GlobalRoute.Detail(id))
                    },
                    snackbarHostState = snackbar,
                    onEditSwipe = { id ->
                        appNavController.navigate(GlobalRoute.Edit(id)) {
                            launchSingleTop = true
                        }
                    } ,
                    onResourceClick= { path ->
                        when (path) {
                            "first" -> Unit//appNavController.navigate()// ....
                            else -> Unit
                        }
                    }
                )
            }
            composable<MainTabRoute.Profile> {
                ProfileScreen()
            }
        }
    }
}