package com.kmx3.compose.ui.navflow.mainflow.favorites

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmx3.compose.R
import com.kmx3.compose.domain.models.User
import com.kmx3.compose.ui.models.Candidate
import com.kmx3.compose.ui.navflow.mainflow.MainFlowNavigation
import com.kmx3.compose.ui.navflow.mainflow.main_navigation.UserProfileTopBar
import com.kmx3.compose.ui.navflow.mainflow.menu.BottomMenu
import com.kmx3.compose.ui.navflow.mainflow.showcase.CandidatesLazyColumn
import com.kmx3.compose.ui.navflow.mainflow.showcase.FilterBottomSheet
import com.kmx3.compose.ui.navflow.mainflow.showcase.SortBottomSheet
import com.kmx3.compose.ui.navflow.mainflow.showcase.SortOption
import com.kmx3.compose.ui.theme.Bordo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    state: FavoritesScreenState, 
    events: FavoritesScreenEvents,
    userProfile: User?
) {
    BackHandler(enabled = true) {}
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
                "Объекты" to 7,
                "Лиды" to 3,
                "Сделки" to 2,
                "Покупатели" to 1
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
            age = 31,
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

    val sheetFilterState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showFilterSheet by remember { mutableStateOf(false) } // For filter sheet
    var showSortSheet by remember { mutableStateOf(false) }

    val userName = userProfile?.fullName ?: userProfile?.username ?: "Пользователь"
    val date = java.text.SimpleDateFormat("EEEE, d MMMM yyyy 'года'", java.util.Locale("ru")).format(java.util.Date())
        .replaceFirstChar { it.uppercase() }

    Scaffold(
        topBar = { UserProfileTopBar(name = userName, date = date) },
        bottomBar = {
            BottomMenu(
                selected = MainFlowNavigation.Routes.FavoritesScreen,
                onSelect = events::onSelectBottomMenu
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
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
                        text = "Избранное",
                        color = Bordo,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        modifier = Modifier
                            .height(36.dp),
                        onClick = events::onRequestQuota,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Bordo,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            "Запросить квоты",
                            fontSize = 12.sp
                        )
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
                            contentDescription = "Сортировка"
                        )
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
                            sheetState = sheetFilterState,
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
                CandidatesLazyColumn(candidates)
            }
        }
    }

}

data class FavoritesScreenState(
    val value: String,
)

interface FavoritesScreenEvents {
    fun onRequestQuota()
    fun onSelectBottomMenu(item: MainFlowNavigation.Routes)
}

@Preview(showBackground = true)
@Composable
fun PreviewInvitationsScreen() {
    FavoritesScreen(
        events = object : FavoritesScreenEvents {
            override fun onRequestQuota() {

            }

            override fun onSelectBottomMenu(item: MainFlowNavigation.Routes) {
            }
        },
        state = FavoritesScreenState(value = ""),
        userProfile = null
    )
}
