package malok.testtask.list_tasks.listTasks.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import malok.testtask.core_ui.ui.HorizontalMenu
import malok.testtask.list_tasks.listTasks.presentation.mapper.toUiModel
import malok.testtask.list_tasks.listTasks.presentation.model.ListEffect
import malok.testtask.list_tasks.listTasks.presentation.model.ListIntent
import malok.testtask.list_tasks.listTasks.presentation.ui.ListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    onItemClick: (String) -> Unit,
    onEditSwipe: (String) -> Unit
) {
    val viewModel: ListViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val menuItems = viewModel.menuItems.map { it.toUiModel() }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ListEffect.OpenDetails -> onItemClick(effect.id)
                is ListEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
                is ListEffect.OpenEditTask -> onEditSwipe(effect.id)
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
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
                Column(
                    modifier = modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.size(20.dp))
                    HorizontalMenu(
                        menuItems,
                        onItemClick,
                        modifier = Modifier
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 20.dp),
                        thickness = 2.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            start = 16.dp, end = 16.dp, bottom = 28.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.tasks, key = { it.id!! }) { task ->
                            ListItem(
                                task = task,
                                onItemClick = { viewModel.onIntent(ListIntent.ItemClicked(it)) },
                                onCheckedChange = { id, checked ->
                                    viewModel.onIntent(ListIntent.TaskChecked(id, checked))
                                },
                                onEdit = { id -> viewModel.onIntent(ListIntent.onEdit(id)) },
                                onDelete = { id -> viewModel.onIntent(ListIntent.onDelete(id)) }
                            )
                        }
                    }
                }
            }
        }
    }
}