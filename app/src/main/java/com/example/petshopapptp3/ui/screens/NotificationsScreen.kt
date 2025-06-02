package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshopapptp3.R
import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme

@Composable
fun NotificationsScreen(onBack: () -> Unit) {
    var isSellerMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .size(36.dp)
                    .background(Color(0xFFF8F8F8), shape = CircleShape)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Notification",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Toggle Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF8F8F8), RoundedCornerShape(24.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { isSellerMode = false },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isSellerMode) Color(0xFF7140FD) else Color.Transparent,
                    contentColor = if (!isSellerMode) Color.White else Color.Black
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text("Activity")
            }
            Button(
                onClick = { isSellerMode = true },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSellerMode) Color(0xFF7140FD) else Color.Transparent,
                    contentColor = if (isSellerMode) Color.White else Color.Black
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text("Seller Mode")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            if (!isSellerMode) {
                items(4) {
                    NotificationItem(title = "SALE 50%", subtitle = "Check the details!", image = R.drawable.propaganda_producto)
                }
            } else {
                items(8) {
                    NotificationItem(
                        title = if (it % 2 == 0) "You Got New Order!" else listOf("Momon", "Ola", "Raul", "Vito")[it % 4],
                        subtitle = if (it % 2 == 0) "Please arrange delivery" else "Liked your Product",
                        image = R.drawable.propaganda_producto
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationItem(title: String, subtitle: String, image: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationsScreenPreview() {
    PetshopAppTP3Theme {
        NotificationsScreen(onBack = {})
    }
}