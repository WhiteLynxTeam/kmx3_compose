package com.kmx3.compose.ui.navflow.mainflow.showcase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.kmx3.compose.ui.theme.Bordo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    sheetState: SheetState,
    minAge: String,
    maxAge: String,
    onMinAgeChange: (String) -> Unit,
    onMaxAgeChange: (String) -> Unit,
    selectedCourses: List<String>,
    allCourses: List<String>,
    onCourseToggle: (String) -> Unit,
    onApply: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
        dragHandle = null, // Уберите если нужно свою "ручку"
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight() // Ограничение высоты на 80% экрана
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            // Заголовок и кнопка "Отмена"
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(4f)
                ) {
                    Text(
                        "Фильтры",
                        color = Bordo,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                Text(
                    "Отмена",
                    color = Color.Gray,
                    modifier = Modifier
                        .clickable { onDismiss() }
                        .padding(8.dp)
                        .weight(1f)
                )
            }

            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f) // Занимает доступное пространство
                    .verticalScroll(rememberScrollState())
            ) {
                // Возраст
                Text("Возраст", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(8.dp))
                Row {
                    OutlinedTextField(
                        value = minAge,
                        onValueChange = onMinAgeChange,
                        label = { Text("от") },
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    )
                    OutlinedTextField(
                        value = maxAge,
                        onValueChange = onMaxAgeChange,
                        label = { Text("до") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(24.dp))

                // Пройденные курсы
                Text("Пройденные курсы", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(12.dp))
                FlowRow(
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    allCourses.forEach { course ->
                        Surface(
                            color = if (selectedCourses.contains(course)) Color(0xFFE2E2E2) else Color(0xFFF7F7F7),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.clickable { onCourseToggle(course) }
                        ) {
                            Text(
                                course,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 15.sp
                            )
                        }
                    }
                }

                Spacer(Modifier.height(32.dp))
            }
            Button(
                onClick = onApply,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Bordo),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(48.dp)
            ) {
                Text("Применить", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
