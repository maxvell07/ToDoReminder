package malok.todoreminder.features.listTasks.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
    onEditClick: (String) -> Unit,
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
                is ListEffect.OpenEditScreen ->
                    onEditClick(effect.id)
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
        bottomBar = {

        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onIntent(ListIntent.AddButtonClicked) }) {
                Text(text = "Add")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = state.tasks,
                        key = { it.id!! }
                    ) { task ->
                        ListItem(
                            task = task,
                            onItemClick = { viewModel.onIntent(ListIntent.ItemClicked(it)) },
                            onEditClick = {viewModel.onIntent(ListIntent.EditClicked(it))},
                            onCheckedChange = { id, checked ->
                                viewModel.onIntent(ListIntent.TaskChecked(id, checked))
                            }
                        )
                    }
                }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(48.dp)
                )
            }
        }
    }
}