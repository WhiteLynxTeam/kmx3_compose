package com.kmx3.compose.ui.navflow.mainflow.showcase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
fun SortBottomSheet(
    sheetState: SheetState,
    selectedOption: SortOption,
    onSelect: (SortOption) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 14.dp)
        ) {
            Text("Сортировать", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(12.dp))
            SortOption.values().forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(option) }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = option == selectedOption,
                        onClick = { onSelect(option) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF9D0042)
                        )
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(option.displayName)
                }
                HorizontalDivider()
            }
        }
    }
}

enum class SortOption(val displayName: String) {
    NEW("Сначала новые"),
    OLD("Сначала старые"),
    AZ("По алфавиту А-Я"),
    ZA("По алфавиту Я-А")
}