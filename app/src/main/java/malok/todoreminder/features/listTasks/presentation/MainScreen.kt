package malok.todoreminder.features.listTasks.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import malok.todoreminder.features.listTasks.presentation.model.ListEffect
import malok.todoreminder.features.listTasks.presentation.model.ListIntent
import malok.todoreminder.features.listTasks.presentation.ui.ListItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onItemClick: (String) -> Unit,
    onFloatButtonClick: () -> Unit,
    viewModel: ListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            TopAppBar(title = { Text("List Tasks") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onIntent(ListIntent.AddButtonClicked) }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Добавить задачу")
            }
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
                        Modifier
                            .align(Alignment.Center)
                            .size(56.dp)
                    )
                }

                state.tasks.isEmpty() -> {
                    Text(
                        text = "Список задач пуст",
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
                            bottom = 88.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.tasks) { task ->
                            ListItem(
                                task = task,
                                onItemClick = { viewModel.onIntent(ListIntent.ItemClicked(task.id.toString())) },
                                onCheckedChange = { id, checked -> viewModel.onIntent(ListIntent.TaskChecked(id, checked))}
                            )
                        }
                    }
                }
            }
        }
    }
}