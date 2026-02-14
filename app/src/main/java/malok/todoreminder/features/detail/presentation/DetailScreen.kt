package malok.todoreminder.features.detail.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import malok.todoreminder.features.detail.presentation.model.DetailEffect
import malok.todoreminder.features.detail.presentation.model.DetailIntent
import malok.todoreminder.features.detail.presentation.ui.DetailContent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: String,
    onEditClick: (String) -> Unit,
    viewModel: DetailViewModel = koinViewModel(parameters = { parametersOf(id) }),
    onBack: () -> Unit
) {
    val task by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(id) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is DetailEffect.OpenEditScreen ->
                    onEditClick(effect.id)

                is DetailEffect.ShowError -> {
                    snackBarHostState.showSnackbar(
                        message = effect.message
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
    ) { innerPadding ->
       DetailContent(
            state = task,
            onTaskCheckedChange = { checked ->
                task.task.id?.let { taskId ->
                    viewModel.onIntent(DetailIntent.TaskChecked(taskId, checked))
                }
            },
            onDeleteClick = {
                viewModel.onIntent(DetailIntent.DeleteTask(id = id.toLong()))
                onBack()
            },
            onEditClick = {
                viewModel.onIntent(DetailIntent.EditClicked(id = id))
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
