package com.aimatchpro.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import java.time.LocalDate
import java.util.Locale

private val Background = Color(0xFF080D18)
private val CardColor = Color(0xFF151F30)
private val Green = Color(0xFF20D69B)
private val White = Color(0xFFF4F7FC)
private val Muted = Color(0xFF91A0B5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Rtl
            ) {
                MatchApp()
            }
        }
    }
}

data class Match(
    val home: String,
    val away: String,
    val league: String,
    val time: String
)

@Composable
fun MatchApp() {
    var selectedDay by remember { mutableIntStateOf(0) }
    var selectedTab by remember { mutableIntStateOf(0) }

    val matches = listOf(
        Match("ريال مدريد", "برشلونة", "الدوري الإسباني", "20:00"),
        Match("مانشستر سيتي", "ليفربول", "الدوري الإنجليزي", "18:30"),
        Match("بايرن ميونخ", "دورتموند", "الدوري الألماني", "21:00")
    )

    val predictions = listOf(
        "الفريق الأول يفوز",
        "أكثر من 1.5 هدف",
        "أكثر من 2.5 هدف",
        "الفريقان يسجلان",
        "النتيجة المتوقعة: 2 - 1"
    )

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "AI MATCH PRO",
                        color = Green,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        "الذكاء الاصطناعي لتحليل المباريات",
                        color = Muted,
                        fontSize = 12.sp
                    )
                }
                Text("⚽", fontSize = 35.sp)
            }

            Spacer(Modifier.height(22.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "جدول المباريات",
                    color = White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("15 يومًا", color = Green, fontSize = 13.sp)
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(15) { index ->
                    val date = LocalDate.now().plusDays(index.toLong())

                    val dayName = date.dayOfWeek.getDisplayName(
                        java.time.format.TextStyle.SHORT,
                        Locale("ar")
                    )

                    val label = if (index == 0) "اليوم"
                    else "$dayName ${date.dayOfMonth}"

                    Surface(
                        modifier = Modifier.clickable {
                            selectedDay = index
                        },
                        shape = RoundedCornerShape(12.dp),
                        color = if (selectedDay == index)
                            Green else CardColor
                    ) {
                        Column(
                            modifier = Modifier.padding(
                                horizontal = 15.dp,
                                vertical = 12.dp
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                label,
                                color = if (selectedDay == index)
                                    Background else White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(22.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "مباريات اليوم",
                    color = White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("⚡ تحليل AI", color = Green, fontSize = 13.sp)
            }

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(matches.size) { index ->
                    val match = matches[index]

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = CardColor
                        ),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(15.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    match.league,
                                    color = Muted,
                                    fontSize = 12.sp
                                )
                                Text(
                                    match.time,
                                    color = Green,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("⚽", fontSize = 30.sp)
                                    Text(
                                        match.home,
                                        color = White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                }

                                Text(
                                    "VS",
                                    color = Green,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 20.sp
                                )

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("⚽", fontSize = 30.sp)
                                    Text(
                                        match.away,
                                        color = White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                }
                            }

                            Spacer(Modifier.height(16.dp))
                            HorizontalDivider(color = Color(0xFF344155))
                            Spacer(Modifier.height(12.dp))

                            Text(
                                "🤖 توقعات الذكاء الاصطناعي",
                                color = Green,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )

                            Spacer(Modifier.height(10.dp))

                            predictions.forEachIndexed { i, prediction ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "${i + 1}",
                                        color = Green,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Text(
                                        prediction,
                                        color = White,
                                        fontSize = 13.sp
                                    )
                                }
                            }

                            Spacer(Modifier.height(10.dp))

                            Text(
                                "توقعات تجريبية - تحتاج ربط خدمة بيانات وتحليل حقيقية",
                                color = Muted,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }

            NavigationBar(
                containerColor = CardColor,
                contentColor = White
            ) {
                val tabs = listOf("المباريات", "التوقعات", "المفضلة")
                val symbols = listOf("⚽", "🤖", "⭐")

                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Text(symbols[index], fontSize = 19.sp)
                        },
                        label = {
                            Text(tab, fontSize = 11.sp)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Green,
                            selectedTextColor = Green,
                            indicatorColor = Color(0xFF263B43),
                            unselectedTextColor = Muted
                        )
                    )
                }
            }
        }
    }
}
