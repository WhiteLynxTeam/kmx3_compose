package com.kmx3.compose.ui.navflow.mainflow.invitations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            // Имя
            Text(
                text = "Романова Мария Ивановна",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(Modifier.height(32.dp))

            // Город
            Text(
                text = "Москва",
                fontSize = 15.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(24.dp))

            // Возраст
            Text(
                text = "22 года",
                fontSize = 15.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(24.dp))

            // Нижняя строка с датой
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Приглашена",
                    fontSize = 15.sp,
                    color = Color.Black
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "15.02.2024",
                    fontSize = 15.sp,
                    color = Color.Black
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}
