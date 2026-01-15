package malok.todoreminder.features.createTask.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import malok.todoreminder.domain.Task
import malok.todoreminder.features.createTask.presentation.model.CreateEffect
import malok.todoreminder.features.createTask.presentation.model.CreateIntent
import malok.todoreminder.features.createTask.presentation.ui.ChooseTime
import malok.todoreminder.features.createTask.presentation.ui.TaskForm
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
            }
        }
    }
    Scaffold(
        snackbarHost = { androidx.compose.material3.SnackbarHost(snackBarHostState) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var title by rememberSaveable() { mutableStateOf("") }
            var description by rememberSaveable() { mutableStateOf("") }
            TaskForm(
                title = title,
                onTitleChange = { title = it },
                description = description,
                onDescriptionChange = { description = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            var time by rememberSaveable { mutableLongStateOf(System.currentTimeMillis()) }
            ChooseTime(time) { time = it }
            Spacer(modifier = Modifier.weight(2f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    val newTask = Task(
                        title = title,
                        description = description,
                        isDone = false,
                        date = time
                    )
                    viewModel.onIntent(
                        CreateIntent.CreateTask(newTask)
                    )
                }) {
                Text(text = "Apply")
            }
        }
    }
}