package com.kmx3.compose.ui.navflow.mainflow.quotas

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.kmx3.compose.ui.navflow.mainflow.MainFlowNavigation
import com.kmx3.compose.ui.navflow.mainflow.main_navigation.UserProfileTopBar
import com.kmx3.compose.ui.navflow.mainflow.menu.BottomMenu
import com.kmx3.compose.ui.theme.Bordo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotasScreen(
    state: QuotasScreenState, 
    events: QuotasScreenEvents,
    userProfile: com.kmx3.compose.domain.models.User?
) {
    BackHandler(enabled = true) {}
    val periods = listOf("Неделя", "Месяц", "Год")
    var selectedPeriod by remember { mutableStateOf("Период") }
    var sheetOpened by remember { mutableStateOf(false) }

    if (sheetOpened) {
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = { sheetOpened = false }
        ) {
            Column(Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                Text(
                    "Период",
                    modifier = Modifier.padding(start = 12.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(12.dp))
                periods.forEach { period ->
                    Text(
                        text = period,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedPeriod = period
                                sheetOpened = false
                            }
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                    )
                    HorizontalDivider()
                }
            }
        }
    }

    val userName = userProfile?.fullName ?: userProfile?.username ?: "Пользователь"
    val date = java.text.SimpleDateFormat("EEEE, d MMMM yyyy 'года'", java.util.Locale("ru")).format(java.util.Date())
        .replaceFirstChar { it.uppercase() }

    Scaffold(
        topBar = { UserProfileTopBar(name = userName, date = date) },
        bottomBar = {
            BottomMenu(
                selected = MainFlowNavigation.Routes.QuotasScreen,
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
                        text = "Статистика квот",
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Button(
                        onClick = { sheetOpened = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        border = BorderStroke(1.dp, Color.LightGray),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_down), null)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            selectedPeriod,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
                ) {
                    InfoLine("Количество выданных квот за период", "5")
                    InfoLine("Количество использованных квот за период", "3")
                    InfoLine("Количество приглашённых на собеседование за период", "10")
                }

            }

        }
    }
}

@Composable
fun InfoLine(title: String, value: String) {
    Column() {
        Text(title, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        Text(value, style = MaterialTheme.typography.headlineMedium)
        HorizontalDivider(Modifier.padding(vertical = 8.dp))
    }
}

data class QuotasScreenState(
    val value: String,
)

interface QuotasScreenEvents {
    fun onRequestQuota()
    fun onSelectBottomMenu(item: MainFlowNavigation.Routes)
}

@Preview(showBackground = true)
@Composable
fun PreviewQuotasScreen() {
    QuotasScreen(
        events = object : QuotasScreenEvents {
            override fun onRequestQuota() {

            }

            override fun onSelectBottomMenu(item: MainFlowNavigation.Routes) {
            }
        },
        state = QuotasScreenState(value = "")
    )
}