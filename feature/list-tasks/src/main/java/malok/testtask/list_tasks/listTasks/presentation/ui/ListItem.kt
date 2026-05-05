package malok.testtask.list_tasks.listTasks.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import malok.testtask.core.domain.Task
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(
    task: Task,
    onItemClick: (String) -> Unit,
    onCheckedChange: (String, Boolean) -> Unit,
    onDelete: (String) -> Unit,
    onEdit: (String) -> Unit
) {
    val state = rememberSwipeToDismissBoxState()

    //TODO Надо слеладь version у тасков для исключения залипания карточки элемента списка
    var isHandled by remember { mutableStateOf(false) }

    LaunchedEffect(task.id) {
        state.snapTo(SwipeToDismissBoxValue.Settled)
    }

    LaunchedEffect(state.targetValue) {
        if (isHandled) return@LaunchedEffect

        when (state.targetValue) {
            SwipeToDismissBoxValue.StartToEnd -> {
                isHandled = true
                onEdit(task.id.toString())
                state.reset()
            }
            SwipeToDismissBoxValue.EndToStart -> {
                isHandled = true
                onDelete(task.id.toString())
                state.reset()
            }
            else -> Unit
        }
    }

    SwipeToDismissBox(
        state = state,
        backgroundContent = {
            val direction = state.dismissDirection
            val color = when (direction) {
                SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.primary
                SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.surfaceVariant
            }
            val text = when (direction) {
                SwipeToDismissBoxValue.StartToEnd -> "Редактировать"
                SwipeToDismissBoxValue.EndToStart -> "Удалить"
                else -> ""
            }
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(color, RoundedCornerShape(12.dp)),
                contentAlignment = when (direction) {
                    SwipeToDismissBoxValue.StartToEnd -> Alignment.CenterStart
                    SwipeToDismissBoxValue.EndToStart -> Alignment.CenterEnd
                    else -> Alignment.CenterStart
                }
            ) {
                Text(
                    text,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(task.id.toString()) },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier.weight(1f),
                    text = task.title,
                    maxLines = 1,
                    style =
                        if (task.isDone)
                            MaterialTheme.typography.bodyLarge.copy(
                                textDecoration = TextDecoration.LineThrough
                            )
                        else
                            MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.width(8.dp))

                Checkbox(
                    checked = task.isDone,
                    onCheckedChange = { checked ->
                        onCheckedChange(task.id.toString(), checked)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        checkmarkColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}