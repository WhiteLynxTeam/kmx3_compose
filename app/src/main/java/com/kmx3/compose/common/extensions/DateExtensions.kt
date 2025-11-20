package com.kmx3.compose.common.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy 'года'",
    Locale.Builder()
        .setLanguage("ru")
        .build()
)

/**
 * Форматирует дату в русскоязычный формат "День недели, день месяц год"
 * Пример: "Понедельник, 1 января 2024 года"
 */
@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toRussianDate(): String {
    val formattedDate = this.format(dateFormatter)
    return formattedDate.replaceFirstChar { it.uppercase() }
}

/**
 * Возвращает текущую дату в русскоязычном формате
 */
@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentRussianDate(): String = LocalDate.now().toRussianDate()