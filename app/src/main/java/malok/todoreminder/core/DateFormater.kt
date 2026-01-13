package malok.todoreminder.core

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.toHumanDateTime(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm")
        .withLocale(Locale.getDefault())
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
        .format(formatter)
}
