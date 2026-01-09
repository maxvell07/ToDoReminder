package malok.todoreminder.features.createTask.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import malok.todoreminder.features.createTask.presentation.ui.TaskForm
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateScreen(
    onBack: () -> Unit,
   // viewModel: CreateViewModel = koinViewModel()
){
    Scaffold() { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            TaskForm()
            Spacer(modifier = Modifier.weight(2f))
            Button(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                onClick = { onBack() }) {
                Text(text = "Apply")
            }
        }
    }
}

