package malok.todoreminder.features.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    id: String,
    viewModel: DetailViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val task by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.getTaskById(id.toLong())
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(task?.title ?: "Details") })
        },
        bottomBar = {
            // BottomNavigation
        },
        floatingActionButton = {

        }) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

            }
        }
    }
}
