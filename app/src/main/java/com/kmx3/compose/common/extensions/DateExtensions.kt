package com.kmx3.compose.common.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy 'года'", Locale("ru"))

/**
 * Форматирует дату в русскоязычный формат "День недели, день месяц год"
 * Пример: "Понедельник, 1 января 2024 года"
 */
fun LocalDate.toRussianDate(): String {
    val formattedDate = this.format(dateFormatter)
    return formattedDate.replaceFirstChar { it.uppercase() }
}

/**
 * Возвращает текущую дату в русскоязычном формате
 */
fun getCurrentRussianDate(): String = LocalDate.now().toRussianDate()