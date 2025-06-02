package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Settings Page",
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Account", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        SettingsItem("Account", Icons.Default.Person) { /* navController.navigate("account") */ }
        SettingsItem("Address", Icons.Default.Home) { /* navController.navigate("address") */ }
        SettingsItem("Notification", Icons.Default.Notifications) { /* navController.navigate("notifications") */ }
        //SettingsItem("Payment Method", Icons.Default.Payment) { /* navController.navigate("payment") */ }
        SettingsItem("Privacy", Icons.Default.Info) { /* navController.navigate("privacy") */ }
        SettingsItem("Security", Icons.Default.Lock) { /* navController.navigate("security") */ }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Help", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        SettingsItem("Contact Us", Icons.Default.Phone) { /* navController.navigate("contact") */ }
       // SettingsItem("FAQ", Icons.Default.Description) { /* navController.navigate("faq") */ }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = { /* logout aún no implementado */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF8855DD)
            )
        ) {
            Text(text = "Log Out", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SettingsItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier
                .size(32.dp)
                .padding(end = 16.dp),
            tint = Color.Gray
        )
        Text(text = text, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Arrow")
    }
}
