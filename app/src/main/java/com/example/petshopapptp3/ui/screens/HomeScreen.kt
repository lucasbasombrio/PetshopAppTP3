package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petshopapptp3.R
import com.example.petshopapptp3.ui.components.BottomNavigationBar
import com.example.petshopapptp3.ui.components.IconBox
import com.example.petshopapptp3.ui.components.ProductCard
import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme

data class Product(
    val name: String,
    val price: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val bestSellers = listOf(
        Product("RC Kitten", "$20,99", ""),
        Product("RC Persian", "$22,99", "")
    )
    val bottomSheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedIndex = 0, // Estamos en la pantalla Home
                onTabSelected = { index ->
                    when (index) {
                        0 -> navController.navigate("home")
                        1 -> navController.navigate("notifications")
                        2 -> navController.navigate("cart")
                        3 -> navController.navigate("profile") // Redirige al perfil
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.clickable { showSheet = true }
                ) {
                    Text(
                        text = "Location \u25BE",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                        text = "Jebres, Surakarta",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconBox(
                        icon = Icons.Default.Search,
                        contentDescription = "Search",
                        onClick = { navController.navigate("search") }
                    )
                    IconBox(
                        icon = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        onClick = { navController.navigate("notifications") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF7140FD)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(start = 24.dp, end = 16.dp)
                        .height(140.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .offset(x = (65).dp, y = (-30).dp)
                                .background(Color(0xFFFEB47A).copy(alpha = 0.55f), shape = CircleShape)
                                .align(Alignment.TopStart)
                        )
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .offset(x = 10.dp, y = (-25).dp)
                                .background(Color(0xFFFEB47A).copy(alpha = 0.55f), shape = CircleShape)
                                .align(Alignment.TopEnd)
                        )
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .offset(x = (-40).dp, y = 20.dp)
                                .background(Color(0xFFFEB47A).copy(alpha = 0.55f), shape = CircleShape)
                                .align(Alignment.BottomStart)
                        )
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .offset(x = (90).dp, y = 65.dp)
                                .background(Color(0xFFFEB47A).copy(alpha = 0.55f), shape = CircleShape)
                                .align(Alignment.CenterStart)
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(end = 20.dp)
                                .align(Alignment.CenterEnd),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "Royal Canin\nAdult Pomeraniann",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Get an interesting promo\nhere, without conditions",
                                color = Color.White,
                                fontSize = 12.sp,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.propaganda_producto),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.CenterStart)
                        .offset(x = (-60).dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Category
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Category",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFF7140FD),
                    modifier = Modifier.clickable { }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(12.dp))
                        .size(44.dp)
                ) {
                    Icon(Icons.Default.ArrowForward, contentDescription = "Swap", tint = Color.Black)
                }

                FilterChip(text = "Food", selected = true)
                FilterChip(text = "Toys")
                FilterChip(text = "Accessories")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Best Seller
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Best Seller",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFF8E60F8),
                    modifier = Modifier.clickable { }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                bestSellers.forEach { product ->
                    ProductCard(product)
                }
            }
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ir a Login (prueba)")
            }
            Spacer(modifier = Modifier.height(32.dp))


        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = bottomSheetState,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            containerColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text("Location", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search your Location") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Track your Location", fontWeight = FontWeight.Bold)
                        Text(
                            "automatically selects your current location",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FilterChip(text: String, selected: Boolean = false) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (selected) Color(0xFF7140FD) else Color(0xFFF8F8F8)
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else Color(0xFFB3B1B0),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    PetshopAppTP3Theme {
        HomeScreen(navController = rememberNavController())
    }
}