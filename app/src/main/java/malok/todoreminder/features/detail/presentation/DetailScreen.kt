package malok.todoreminder.features.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val task by viewModel.state.collectAsState()

    LaunchedEffect(id) {
        viewModel.observeTask(id.toLong())
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Details") })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                task.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }

                task.error != null -> {
                    Text(
                        text = task.error!!,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }

                task.task != null -> {
                    val task = task.task
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Task: ${task?.title}",
                                modifier = Modifier.weight(1f)
                            )
                            Checkbox(
                                checked = task?.isDone ?: false,
                                onCheckedChange = { viewModel.onTaskChecked(task?.id, it) }
                            )
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Text(text = "Description: ${task?.description}")
                    }
                }
            }
        }
    }
}
