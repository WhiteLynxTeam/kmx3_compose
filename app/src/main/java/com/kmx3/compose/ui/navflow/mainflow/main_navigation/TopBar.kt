package com.kmx3.compose.ui.navflow.mainflow.main_navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
    name: String = "Колесникова Мария",
    date: String = SimpleDateFormat("EEEE, d MMMM yyyy 'года'", Locale("ru")).format(Date())
        .replaceFirstChar { it.uppercase() },
    onBellClick: () -> Unit = {},
    onArrowClick: () -> Unit = {}
) {
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

        IconButton(onClick = onArrowClick) {
            Icon(
                painter = painterResource(R.drawable.ic_exit), // используйте свою стрелку
                contentDescription = "Выйти",
                tint = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    UserProfileTopBar()
}