package malok.testtask.create_task.createTask.presentation

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import malok.testtask.core_ui.TaskFormContent
import malok.testtask.create_task.createTask.presentation.model.CreateEffect
import malok.testtask.create_task.createTask.presentation.model.CreateIntent
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: CreateViewModel = koinViewModel()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Лаунчер для перехода в настройки Android 12+
    val alarmPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // Пользователь вернулся из настроек.
        // Показать сообщение.
        scope.launch {
            snackBarHostState.showSnackbar("Теперь попробуйте создать задачу снова")
        }
    }

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
                    // Проверка разрешения на точный будильник
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                        if (!alarmManager.canScheduleExactAlarms()) {
                            // Если разрешения нет открываем настройки
                            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                            alarmPermissionLauncher.launch(intent)
                            // Показываем подсказку
                            scope.launch {
                                snackBarHostState.showSnackbar("Требуется разрешение на точное время")
                            }
                            return@TaskFormContent // Прерываем выполнение, не сохраняя задачу
                        }
                    }
                    // Если проверка прошла, сохраняем задачу
                    viewModel.onIntent(CreateIntent.CreateTask)
                }
            )
        }
    }
}