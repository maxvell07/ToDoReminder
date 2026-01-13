package malok.todoreminder.features.createTask.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import malok.todoreminder.core.toHumanDateTime
import malok.todoreminder.domain.Task
import malok.todoreminder.features.createTask.presentation.ui.ChooseTime
import malok.todoreminder.features.createTask.presentation.ui.TaskForm
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateScreen(
    onBack: () -> Unit,
    viewModel: CreateViewModel = koinViewModel()
){
    Scaffold() { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
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
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                onClick = {
                    viewModel.createTask(task = Task( title=title, description = description, isDone = false, date = time))
                    Log.d("date", " ${time.toHumanDateTime()}")
                    onBack() }) {
                Text(text = "Apply")
            }
        }
    }
}