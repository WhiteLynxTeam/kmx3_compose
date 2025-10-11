package com.kmx3.compose.ui.navflow.mainflow.invitations

import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import com.kmx3.compose.ui.navflow.mainflow.MainFlowNavigation
import com.kmx3.compose.ui.navflow.mainflow.main_navigation.UserProfileTopBar
import com.kmx3.compose.ui.navflow.mainflow.menu.BottomMenu
import com.kmx3.compose.ui.navflow.mainflow.showcase.SortBottomSheet
import com.kmx3.compose.ui.theme.Bordo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvitationsScreen(
    state: InvitationsScreenState, events: InvitationsScreenEvents
) {
    BackHandler(enabled = true) {}
    val candidates = listOf(
        CandidateStatus(
            name = "Романова Мария Ивановна",
            avatarUrl = R.drawable.img_avatar,
            type = CandidateStatusType.Employed
        ),
        CandidateStatus(
            name = "Смирнова Дарья Сергеевна",
            avatarUrl = R.drawable.img_avatar,
            type = CandidateStatusType.Invited
        ),
        CandidateStatus(
            name = "Колывайло Александр Петрович",
            avatarUrl = R.drawable.img_avatar,
            type = CandidateStatusType.Refused
        )
    )

    Scaffold(
        topBar = { UserProfileTopBar() },
        bottomBar = {
            BottomMenu(
                selected = MainFlowNavigation.Routes.InvitationsScreen,
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
                        text = "История приглашений",
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
                LazyColumn {
                    items(candidates) { candidate ->
                        CandidateStatusRow(candidate)
                        VerticalDivider()
                    }
                }
            }

        }
    }

}

data class InvitationsScreenState(
    val value: String,
)

interface InvitationsScreenEvents {
    fun onRequestQuota()
    fun onSelectBottomMenu(item: MainFlowNavigation.Routes)
}

@Preview(showBackground = true)
@Composable
fun PreviewInvitationsScreen() {
    InvitationsScreen(
        events = object : InvitationsScreenEvents {
            override fun onRequestQuota() {

            }
            override fun onSelectBottomMenu(item: MainFlowNavigation.Routes) {
            }
        },
        state = InvitationsScreenState(value = "")
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidateStatusRow(status: CandidateStatus) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        val sheetState = rememberModalBottomSheetState()
        var showSheet by remember { mutableStateOf(false) }
        // Левый блок (иконка + ФИО)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1.6f)
        ) {
            Image(
                painter = painterResource(status.avatarUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = status.name)
            }
        }

        // Статус-индикатор по центру всей строки
        Box(
            modifier = Modifier.padding(start = 6.dp, end = 8.dp)
        ) {
            StatusIndicator(statusType = status.type)
        }

        // Правый блок (статус + иконка)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                modifier = Modifier.weight(5f),
                text = status.type.label
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { showSheet = true }
            ) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
            if (showSheet) {
                UserInfoBottomSheet(
                    sheetState = sheetState,
                    onDismiss = { showSheet = false }
                )
            }
        }
    }
}


@Composable
fun StatusIndicator(statusType: CandidateStatusType) {
    val color = when(statusType) {
        CandidateStatusType.Employed -> Color(0xFF27BCCF)
        CandidateStatusType.Invited -> Color(0xFFFF7300)
        CandidateStatusType.Refused -> Color(0xFF930366)
    }
    Box(
        Modifier
            .width(4.dp)
            .height(32.dp)
            .background(color = color, shape = RoundedCornerShape(2.dp))
    )
}

data class CandidateStatus(
    val name: String,
    val avatarUrl: Int,
    val type: CandidateStatusType
)

enum class CandidateStatusType(val label: String) {
    Employed("Трудоустроен(а)"),
    Invited("Приглашен(а)"),
    Refused("Отказ")
}
