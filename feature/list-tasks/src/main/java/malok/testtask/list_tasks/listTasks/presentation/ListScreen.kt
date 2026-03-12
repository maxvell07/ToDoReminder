package malok.testtask.list_tasks.listTasks.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import malok.testtask.core_ui.CustomBottomNavBar
import malok.testtask.list_tasks.listTasks.presentation.model.ListEffect
import malok.testtask.list_tasks.listTasks.presentation.model.ListIntent
import malok.testtask.list_tasks.listTasks.presentation.ui.ListItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onItemClick: (String) -> Unit,
    onFloatButtonClick: () -> Unit
) {
    val viewModel: ListViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedTab by remember { mutableStateOf(0) }
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ListEffect.OpenDetails ->
                    onItemClick(effect.id)

                is ListEffect.OpenCreateTask ->
                    onFloatButtonClick()

                is ListEffect.ShowError -> {
                    snackBarHostState.showSnackbar(
                        message = effect.message
                    )
                }
            }
        }
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "List Tasks",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onIntent(ListIntent.AddButtonClicked) },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(50.dp)
                    .offset(y = 42.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Добавить задачу"
                )
            }
        },

        floatingActionButtonPosition = FabPosition.Center,

        bottomBar = {
            CustomBottomNavBar(
                selectedItem = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(56.dp)
                    )
                }

                state.tasks.isEmpty() -> {
                    Text(
                        text = "Список задач пуст",
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                            top = 12.dp,
                            bottom = 28.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.tasks) { task ->
                            ListItem(
                                task = task,
                                onItemClick = { viewModel.onIntent(ListIntent.ItemClicked(task.id.toString())) },
                                onCheckedChange = { id, checked ->
                                    viewModel.onIntent(
                                        ListIntent.TaskChecked(
                                            id,
                                            checked
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}