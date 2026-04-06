package malok.testtask.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import malok.testtask.core_ui.SwitchItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: ProfileViewModel = koinViewModel()
    val themestate by viewModel.theme.collectAsStateWithLifecycle()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Settings")

        SwitchItem(
            isChecked = themestate,
            onCheckedChange = viewModel::selectModeTheme
        )
    Spacer(modifier.weight(1f))
    }
}