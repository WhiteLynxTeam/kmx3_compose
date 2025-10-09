package com.kmx3.compose.ui.navflow.mainflow.showcase

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmx3.compose.R
import com.kmx3.compose.ui.models.Candidate
import com.kmx3.compose.ui.theme.BorderGreen
import com.kmx3.compose.ui.theme.Bordo
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun CandidatesLazyColumn(candidates: List<Candidate>) {

    LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = 8.dp)) {
        items(candidates) { candidate ->
            Card(
                border = BorderStroke(1.dp, BorderGreen),
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
                            Text(
                                candidate.name,
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp
                            )
                            Row {
                                Text( "${candidate.age} " + pluralizeAge(candidate.age.toString()).substringAfter(" "), color = Color.Gray)
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
                    Spacer(Modifier.height(8.dp))
                    candidate.courses.forEach {
                        Row {
                            Box(
                                Modifier
                                    .size(14.dp)
                                    .background(Bordo)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(it)
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Text("Достижения", color = Bordo, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    // Выравнивание в две колонки
                    val (left, right) = candidate.achievements.entries.partition {
                        it.key in listOf("Объекты", "Лиды", "Сделки")
                    }
                    Row {
                        Column(Modifier.weight(1f)) {
                            left.forEach { (label, value) ->
                                Row {
                                    Box(
                                        Modifier
                                            .size(14.dp)
                                            .background(Bordo)
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text("$label $value")
                                }
                            }
                        }
                        Column(Modifier.weight(1f)) {
                            right.forEach { (label, value) ->
                                Row {
                                    Box(
                                        Modifier
                                            .size(14.dp)
                                            .background(Bordo)
                                    )
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
