package malok.testtask.edit_task.editTask.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import malok.testtask.core_ui.TaskFormContent
import malok.testtask.edit_task.editTask.presentation.model.EditEffect
import malok.testtask.edit_task.editTask.presentation.model.EditIntent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EditTaskScreen(
    id: String,
    onBack: () -> Unit,
    viewModel: EditViewModel = koinViewModel(parameters = { parametersOf(id) })
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is EditEffect.TaskSaved -> onBack()
                is EditEffect.ShowError ->
                    snackBarHostState.showSnackbar(effect.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(48.dp)
                )
                return@Scaffold
            }

            TaskFormContent(
                title = state.task.title,
                description = state.task.description,
                time = state.task.date,
                buttonText = "Save",
                onTitleChange = {
                    viewModel.onIntent(EditIntent.TitleChanged(it))
                },
                onDeleteClick = {
                viewModel.onIntent(EditIntent.DeleteTask(id = id.toLong()))
                onBack()
                },
                onDescriptionChange = {
                    viewModel.onIntent(EditIntent.DescriptionChanged(it))
                },
                onTimeChange = {
                    viewModel.onIntent(EditIntent.TimeChanged(it))
                },
                onSubmit = {
                    viewModel.onIntent(EditIntent.SaveTask)
                }
            )
        }
    }
}
