package com.example.myvpn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF080D1C)
private val Panel = Color(0xFF151D34)
private val Cyan = Color(0xFF38D9FF)
private val Purple = Color(0xFF9365FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyVpnScreen() }
    }
}

@Composable
fun MyVpnScreen() {
    var connected by remember { mutableStateOf(false) }
    var server by remember { mutableStateOf("Germany • Frankfurt") }

    MaterialTheme(colorScheme = darkColorScheme(
        primary = Cyan,
        background = Navy,
        surface = Panel
    )) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF101B38), Navy, Color(0xFF090B16))
                    )
                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(24.dp))
                Text("♛  MY VPN", color = Color.White,
                    fontSize = 27.sp, fontWeight = FontWeight.Black)
                Text("SECURE CONNECTION", color = Cyan,
                    fontSize = 11.sp, letterSpacing = 3.sp)

                Spacer(Modifier.height(34.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Panel.copy(alpha = .92f)
                    ),
                    shape = RoundedCornerShape(26.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        Modifier.padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            if (connected) "CONNECTED" else "DISCONNECTED",
                            color = if (connected) Cyan else Color.LightGray,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                        Spacer(Modifier.height(18.dp))
                        Text(
                            if (connected) "✓" else "◉",
                            color = if (connected) Cyan else Purple,
                            fontSize = 68.sp
                        )
                        Text(
                            if (connected) "اتصال نمایشی برقرار است" else "آماده اتصال",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(20.dp))
                        Button(
                            onClick = { connected = !connected },
                            colors = ButtonDefaults.buttonColors(
                                containerColor =
                                    if (connected) Color(0xFF743F68)
                                    else Color(0xFF176A91)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                if (connected) "قطع اتصال" else "اتصال",
                                fontSize = 17.sp
                            )
                        }
                    }
                }

                Spacer(Modifier.height(22.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Panel.copy(alpha = .92f)
                    ),
                    shape = RoundedCornerShape(22.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(18.dp)) {
                        Text(
                            "انتخاب سرور",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Spacer(Modifier.height(12.dp))
                        listOf(
                            "Germany • Frankfurt",
                            "Netherlands • Amsterdam",
                            "Turkey • Istanbul"
                        ).forEach {
                            Row(
                                Modifier.fillMaxWidth().padding(vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = server == it,
                                    onClick = { server = it }
                                )
                                Text(it, color = Color.White)
                            }
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InfoCard("مصرف‌شده", "—", Modifier.weight(1f))
                    InfoCard("باقی‌مانده", "—", Modifier.weight(1f))
                }

                Spacer(Modifier.height(18.dp))
                Text(
                    "نسخه آزمایشی رابط کاربری • اتصال واقعی VPN هنوز پیاده‌سازی نشده",
                    color = Color(0xFF9BA8C7),
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
private fun InfoCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Panel),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title, color = Color(0xFFADB9D5), fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            Text(
                value,
                color = Cyan,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
