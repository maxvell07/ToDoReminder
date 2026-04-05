package malok.testtask.profile.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import malok.testtask.core_ui.CustomBottomNavBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(

) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "lalalalala")
    }
//    val viewModel: ProfileViewModel = koinViewModel()
//    var selectedTab by remember { mutableStateOf(1) }
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        "Profile",
//                        color = MaterialTheme.colorScheme.onSurface
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.surface
//                )
//            )
//        },
//        bottomBar = {
//            CustomBottomNavBar(
//                selectedItem = selectedTab,
//                onItemSelected = { selectedTab = it }
//            )
//        }
//    ) { innerpading ->
//        val isDarkTheme =true
//        Column(
//            modifier = Modifier
//                .padding(innerpading)
//                .fillMaxSize()
//        ) {
////            ThemeSwitchItem(
////                isDarkTheme,
//////                lambda{}
////            )
//            Spacer(modifier = Modifier.height(8.dp))
//
//        }
//    }
}

//@Composable
//fun ThemeSwitchItem(
//    isChecked: Boolean,
//    onCheckedChange: (Boolean) -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onCheckedChange(!isChecked) }
//            .padding(vertical = 12.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        Text(
//            text = "Dark theme",
//            modifier = Modifier.weight(1f),
//            style = MaterialTheme.typography.bodyLarge
//        )
//
//        Switch(
//            checked = isChecked,
//            onCheckedChange = onCheckedChange
//        )
//    }
//}