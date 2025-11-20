package com.kmx3.compose.ui.navflow.mainflow.main_navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmx3.compose.R
import com.kmx3.compose.ui.theme.TextGray
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UserProfileTopBar(
    name: String = "Пользователь",
    date: String = SimpleDateFormat("EEEE, d MMMM yyyy 'года'", Locale("ru")).format(Date())
        .replaceFirstChar { it.uppercase() },
    onBellClick: () -> Unit = {},
    onArrowClick: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Аватар
        Image(
            painter = painterResource(R.drawable.img_avatar), // замените на свою картинку
            contentDescription = "Профиль",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Имя и дата
        Column(modifier = Modifier.weight(1f)) {
            Text(
                name,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                date,
                color = TextGray,
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }


        IconButton(onClick = onBellClick) {
            Icon(
                painter = painterResource(R.drawable.ic_bell), // используйте свою bell-иконку
                contentDescription = "Уведомления",
                tint = Color.Black
            )
        }

        IconButton(onClick = { showDialog = true }) {
            Icon(
                painter = painterResource(R.drawable.ic_exit), // используйте свою стрелку
                contentDescription = "Выйти",
                tint = Color.Black
            )
        }
    }
    if (showDialog) {
        val activity = LocalActivity.current
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = Color.White,
            title = { Text("Подтверждение выхода") },
            text = { Text("Вы уверены, что хотите выйти из приложения?") },
            confirmButton = {
                Button(
                    modifier = Modifier
                        .height(36.dp),
                    onClick = { activity?.finishAffinity() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        "Да",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            },
            dismissButton = {
                Button(
                    modifier = Modifier
                        .height(36.dp),
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        "Нет",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    UserProfileTopBar()
}