package malok.todoreminder.features.editTask.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import malok.todoreminder.features.editTask.presentation.model.EditEffect
import malok.todoreminder.features.editTask.presentation.model.EditIntent
import malok.todoreminder.presentation.TaskFormContent
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

        if (state.isLoading) {
            //
            return@Scaffold
        }

        TaskFormContent(
            modifier = Modifier.padding(innerPadding),
            title = state.task.title,
            description = state.task.description,
            time = state.task.date,
            buttonText = "Save",
            onTitleChange = {
                viewModel.onIntent(EditIntent.TitleChanged(it))
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
