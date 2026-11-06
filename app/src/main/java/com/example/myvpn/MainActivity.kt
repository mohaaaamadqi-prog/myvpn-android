
package com.example.myvpn

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*

private val Navy = Color(0xFF050B1C)
private val Panel = Color(0xFF101C38)
private val Blue = Color(0xFF087BFF)
private val Cyan = Color(0xFF16CFFF)
private val Muted = Color(0xFF91A5C9)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.rgb(5, 11, 28)
        window.navigationBarColor = android.graphics.Color.rgb(5, 11, 28)

        setContent {
            KoroshVpnApp {
                Toast.makeText(
                    this,
                    "اتصال واقعی در مرحله بعد اضافه می‌شود",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@Composable
fun KoroshVpnApp(onConnect: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Blue,
            background = Navy,
            surface = Panel
        )
    ) {
        Scaffold(
            containerColor = Navy,
            bottomBar = {
                NavigationBar(
                    containerColor = Color(0xFF09132B)
                ) {
                    val items = listOf(
                        "خانه" to Icons.Default.Home,
                        "آمار" to Icons.Default.BarChart,
                        "تنظیمات" to Icons.Default.Settings,
                        "درباره" to Icons.Default.Info
                    )

                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            icon = {
                                Icon(item.second, contentDescription = item.first)
                            },
                            label = { Text(item.first) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Cyan,
                                selectedTextColor = Cyan,
                                unselectedIconColor = Muted,
                                unselectedTextColor = Muted,
                                indicatorColor = Panel
                            )
                        )
                    }
                }
            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFF10244D),
                                Navy,
                                Color(0xFF030817)
                            )
                        )
                    )
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
            ) {

                Spacer(Modifier.height(24.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("☰", color = Color.White, fontSize = 27.sp)

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "♛ KOROSH VPN",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            "FAST • PRIVATE • SECURE",
                            color = Cyan,
                            fontSize = 10.sp,
                            letterSpacing = 2.sp
                        )
                    }

                    Icon(
                        Icons.Default.Security,
                        contentDescription = null,
                        tint = Cyan,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(Modifier.height(35.dp))

                Text(
                    "خوش آمدید",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "اتصال امن خود را مدیریت کنید",
                    color = Muted,
                    fontSize = 13.sp
                )

                Spacer(Modifier.height(30.dp))

                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        Modifier
                            .size(210.dp)
                            .background(
                                Blue.copy(alpha = 0.10f),
                                CircleShape
                            )
                            .border(2.dp, Blue.copy(.35f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = onConnect,
                            modifier = Modifier.size(160.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0755C9)
                            ),
                            elevation = ButtonDefaults.buttonElevation(12.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    Icons.Default.PowerSettingsNew,
                                    contentDescription = null,
                                    modifier = Modifier.size(55.dp),
                                    tint = Color.White
                                )
                                Text(
                                    "اتصال",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(30.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    InfoPanel(
                        "سرور انتخابی",
                        "انتخاب خودکار",
                        Icons.Default.Dns,
                        Modifier.weight(1f)
                    )
                    InfoPanel(
                        "پروتکل",
                        "Auto",
                        Icons.Default.Public,
                        Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(15.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Panel
                    ),
                    shape = RoundedCornerShape(22.dp),
                    border = BorderStroke(1.dp, Blue.copy(.3f))
                ) {
                    Row(
                        Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Security,
                            contentDescription = null,
                            tint = Cyan,
                            modifier = Modifier.size(35.dp)
                        )

                        Spacer(Modifier.width(15.dp))

                        Column {
                            Text("وضعیت اتصال", color = Muted, fontSize = 12.sp)
                            Text(
                                "قطع شده",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Box(
                            Modifier
                                .size(11.dp)
                                .background(Color.Gray, CircleShape)
                        )
                    }
                }

                Spacer(Modifier.height(18.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    InfoPanel(
                        "حجم مصرفی",
                        "—",
                        Icons.Default.BarChart,
                        Modifier.weight(1f)
                    )
                    InfoPanel(
                        "حجم باقی‌مانده",
                        "—",
                        Icons.Default.Storage,
                        Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(25.dp))

                Text(
                    "KOROSH VPN",
                    modifier = Modifier.fillMaxWidth(),
                    color = Muted,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun InfoPanel(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Panel
        ),
        border = BorderStroke(1.dp, Blue.copy(.25f))
    ) {
        Column(Modifier.padding(16.dp)) {
            Icon(
                icon,
                contentDescription = null,
                tint = Cyan,
                modifier = Modifier.size(25.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(title, color = Muted, fontSize = 12.sp)
            Spacer(Modifier.height(5.dp))
            Text(
                value,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
