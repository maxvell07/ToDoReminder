package malok.testtask.create_task.createTask.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import malok.testtask.core_ui.TaskFormContent
import malok.testtask.create_task.createTask.presentation.model.CreateEffect
import malok.testtask.create_task.createTask.presentation.model.CreateIntent
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateScreen(
    onBack: () -> Unit,
    viewModel: CreateViewModel = koinViewModel()
) {

    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CreateEffect.ShowError -> {
                    snackBarHostState.showSnackbar(effect.message)
                }
                is CreateEffect.TaskCreated -> {
                    onBack()
                }
                is CreateEffect.EmptyFields -> {
                    snackBarHostState.showSnackbar(effect.message)
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->
        val state by viewModel.state.collectAsStateWithLifecycle()
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TaskFormContent(
                title = state.task.title,
                description = state.task.description,
                time = state.task.date,
                buttonText = "Create",
                onTitleChange = {
                    viewModel.onIntent(CreateIntent.TitleChanged(it))
                },
                onDescriptionChange = {
                    viewModel.onIntent(CreateIntent.DescriptionChanged(it))
                },
                onTimeChange = {
                    viewModel.onIntent(CreateIntent.TimeChanged(it))
                },
                onSubmit = {
                    viewModel.onIntent(CreateIntent.CreateTask)
                }
            )
        }
    }
}