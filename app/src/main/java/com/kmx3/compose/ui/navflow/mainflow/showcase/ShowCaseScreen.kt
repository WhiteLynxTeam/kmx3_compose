package com.kmx3.compose.ui.navflow.mainflow.showcase

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmx3.compose.R
import com.kmx3.compose.ui.models.Candidate
import com.kmx3.compose.ui.theme.Bordo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseScreen(
    onRequestQuotaClick: () -> Unit,
    onFilterClick: () -> Unit
) {

    val candidates = listOf(
        Candidate(
            name = "Романова Мария Ивановна",
            age = 22,
            photoUrl = "img_avatar", // используем ресурс drawable
            resumeUrl = "https://example.com/resume_maria", // заглушка, можно оставить null
            courses = listOf(
                "Введение в профессию риелтор",
                "Базовый юридический курс",
                "Курс \"Ипотека\""
            ),
            achievements = mapOf(
                "Объекты" to 5,
                "Лиды" to 3,
                "Сделки" to 2,
                "Покупатели" to 1,
                "Эксклюзивы" to 0
            )
        ),
        Candidate(
            name = "Иванов Алексей Петрович",
            age = 30,
            photoUrl = "img_avatar",
            resumeUrl = "https://example.com/resume_maria",
            courses = listOf(
                "Введение в профессию риелтор"
            ),
            achievements = mapOf(
                "Объекты" to 7
            )
        ),
        Candidate(
            name = "Смирнова Дарья Сергеевна",
            age = 27,
            photoUrl = "img_avatar",
            resumeUrl = "https://example.com/resume_daria",
            courses = listOf(
                "Базовый юридический курс",
                "Курс \"Налогобложение\""
            ),
            achievements = mapOf(
                "Лиды" to 2,
                "Эксклюзивы" to 4
            )
        ),
        Candidate(
            name = "Романова Мария Ивановна",
            age = 22,
            photoUrl = "img_avatar", // используем ресурс drawable
            resumeUrl = "https://example.com/resume_maria", // заглушка, можно оставить null
            courses = listOf(
                "Введение в профессию риелтор",
                "Базовый юридический курс",
                "Курс \"Ипотека\""
            ),
            achievements = mapOf(
                "Объекты" to 5,
                "Лиды" to 3,
                "Сделки" to 2,
                "Покупатели" to 1,
                "Эксклюзивы" to 0
            )
        ),
        Candidate(
            name = "Иванов Алексей Петрович",
            age = 30,
            photoUrl = "img_avatar",
            resumeUrl = "https://example.com/resume_maria",
            courses = listOf(
                "Введение в профессию риелтор"
            ),
            achievements = mapOf(
                "Объекты" to 7
            )
        ),
        Candidate(
            name = "Смирнова Дарья Сергеевна",
            age = 27,
            photoUrl = "img_avatar",
            resumeUrl = "https://example.com/resume_daria",
            courses = listOf(
                "Базовый юридический курс",
                "Курс \"Налогобложение\""
            ),
            achievements = mapOf(
                "Лиды" to 2,
                "Эксклюзивы" to 4
            )
        )
    )

    val sheetSortState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(SortOption.NEW) }

    var minAge by remember { mutableStateOf("") }
    var maxAge by remember { mutableStateOf("") }
    var selectedCourses by remember { mutableStateOf(listOf<String>()) }
    val allCourses = listOf(
        "Введение в профессию риелтор",
        "Базовый юридический курс",
        "Курс \"Ипотека\"",
        "Курс \"Налогобложение\""
    )

    val sheetState = rememberModalBottomSheetState()
    var showFilterSheet by remember { mutableStateOf(false) } // For filter sheet
    var showSortSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Витрина кандидатов",
                color = Color(0xFF9D0042), // бордовый
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Button(
                modifier = Modifier
                    .height(36.dp),
                onClick = onRequestQuotaClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Bordo,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Запросить квоты")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { showSheet = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = "Сортировка")
            }
            if (showSheet) {
                SortBottomSheet(
                    sheetState = sheetSortState,
                    selectedOption = selectedOption,
                    onSelect = {
                        selectedOption = it
                        showSheet = false
                    },
                    onDismiss = { showSheet = false }
                )
            }
            Spacer(Modifier.width(16.dp))
            Button(
                onClick = { showFilterSheet = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.LightGray),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_filter),
                    contentDescription = "Фильтр"
                )
                Spacer(Modifier.width(8.dp))
                Text("Фильтр")
            }
            if (showFilterSheet) {
                FilterBottomSheet(
                    sheetState = sheetState,
                    minAge = minAge,
                    maxAge = maxAge,
                    onMinAgeChange = { minAge = it },
                    onMaxAgeChange = { maxAge = it },
                    selectedCourses = selectedCourses,
                    allCourses = allCourses,
                    onCourseToggle = { course ->
                        selectedCourses = if (selectedCourses.contains(course)) {
                            selectedCourses - course
                        } else {
                            selectedCourses + course
                        }
                    },
                    onApply = {
                        showFilterSheet = false
                    },
                    onDismiss = { showFilterSheet = false }
                )
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(candidates) { candidate ->
                Card(
                    border = BorderStroke(1.dp, Color(0xFFB4ED50)),
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White // Любой нужный цвет, например светло-серый
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            candidate.photoUrl?.let {
                                Image(
                                    painter = painterResource(id = R.drawable.img_avatar),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                )
                            }
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text(candidate.name, fontWeight = FontWeight.Normal, fontSize = 20.sp)
                                Row {
                                    Text("${candidate.age} года", color = Color.Gray)
                                    Spacer(Modifier.width(24.dp))
                                    candidate.resumeUrl?.let {
                                        Text("Ссылка на резюме", color = Bordo)
                                    }
                                }
                            }
                            Spacer(Modifier.weight(1f))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_favorites),
                                contentDescription = null,
                                tint = Bordo
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text("МИЭЛЬ ПрактикУМ", color = Bordo, fontWeight = FontWeight.Bold)
                        candidate.courses.forEach {
                            Row {
                                Box(Modifier
                                    .size(14.dp)
                                    .background(Bordo))
                                Spacer(Modifier.width(8.dp))
                                Text(it)
                            }
                        }
                        Spacer(Modifier.height(7.dp))
                        Text("Достижения", color = Bordo, fontWeight = FontWeight.Bold)
                        // Выравнивание в две колонки
                        val (left, right) = candidate.achievements.entries.partition {
                            it.key in listOf("Объекты", "Лиды", "Сделки")
                        }
                        Row {
                            Column(Modifier.weight(1f)) {
                                left.forEach { (label, value) ->
                                    Row {
                                        Box(Modifier
                                            .size(14.dp)
                                            .background(Bordo))
                                        Spacer(Modifier.width(8.dp))
                                        Text("$label $value")
                                    }
                                }
                            }
                            Column(Modifier.weight(1f)) {
                                right.forEach { (label, value) ->
                                    Row {
                                        Box(Modifier
                                            .size(14.dp)
                                            .background(Bordo))
                                        Spacer(Modifier.width(8.dp))
                                        Text("$label $value")
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = { /* TODO */ },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Bordo),
                                modifier = Modifier.fillMaxWidth(0.445f)
                            ) { Text("Пригласить", color = Color.White) }
                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShowcaseScreen() {
    ShowcaseScreen(
        onRequestQuotaClick = {},
        onFilterClick = {}
    )
}