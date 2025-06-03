// ProfileScreen.kt
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.petshopapptp3.R
import com.example.petshopapptp3.data.local.AppDatabase
import com.example.petshopapptp3.data.local.FavoriteProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavHostController) {
    var isSellerMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F7FD)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            ModeButton(
                text = "Profile",
                selected = !isSellerMode,
                onClick = { isSellerMode = false }
            )
            Spacer(modifier = Modifier.width(8.dp))
            ModeButton(
                text = "Seller Mode",
                selected = isSellerMode,
                onClick = { isSellerMode = true }
            )
        }

        if (isSellerMode) {
            SellerModeUI()
        } else {
            UserModeUI(navController)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ModeButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFF814DFF) else Color(0xFFE0E0E0),
            contentColor = if (selected) Color.White else Color(0xFF666666)
        ),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
        modifier = Modifier.defaultMinSize(minHeight = 1.dp)
    ) {
        Text(text = text, fontSize = 14.sp)
    }
}

@Composable
fun SellerModeUI() {
    Text("Modo vendedor aún no implementado")
}

@Composable
fun UserModeUI(navController: NavHostController) {
    val context = LocalContext.current
    var savedProducts by remember { mutableStateOf<List<FavoriteProductEntity>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val db = AppDatabase.getInstance(context)
            val dao = db.favoriteProductDao()
            savedProducts = withContext(Dispatchers.IO) { dao.getAll() }
        }
    }

    UserBanner()

    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Abduldul", fontWeight = FontWeight.Bold, fontSize = 20.sp)

    Spacer(modifier = Modifier.height(16.dp))
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        FilterButton("Saved", true)
        Button(
            onClick = { navController.navigate("settings") },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE0E0E0),
                contentColor = Color(0xFF666666)
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(text = "Edit Profile", fontSize = 14.sp)
        }
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(savedProducts) { product ->
            ProductCard(
                title = product.title,
                price = "$${product.price}",
                imageUrl = product.thumbnail
            )
        }
    }

    BottomBar()
}

@Composable
fun UserBanner() {
    Image(
        painter = painterResource(id = R.drawable.bg_profile_user),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    )

    Box(
        modifier = Modifier
            .offset(y = (-40).dp)
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_avatar),
            contentDescription = "User Avatar",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun ProductCard(title: String, price: String, imageUrl: String) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(title, fontWeight = FontWeight.Bold)
        Text(price, color = Color.Gray)
    }
}

@Composable
fun StatColumn(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontWeight = FontWeight.Bold)
        Text(text = label)
    }
}

@Composable
fun FilterButton(text: String, selected: Boolean) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFF814DFF) else Color(0xFFE0E0E0),
            contentColor = if (selected) Color.White else Color(0xFF666666)
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(text = text, fontSize = 14.sp)
    }
}

@Composable
fun BottomBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        IconButton(onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = "Home")
        }
        IconButton(onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.ic_chat), contentDescription = "Favoritos")
        }
        IconButton(onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.ic_cart), contentDescription = "Carrito")
        }
        IconButton(onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.ic_user), contentDescription = "Perfil")
        }
    }
}