package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.petshopapptp3.R

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Selector de modo
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                shape = RoundedCornerShape(50),
                modifier = Modifier.weight(1f)
            ) {
                Text("Profile", color = Color.Black)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8A63F8)),
                shape = RoundedCornerShape(50),
                modifier = Modifier.weight(1f)
            ) {
                Text("Seller Mode", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Banner + imagen de perfil
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFEAEAEA), shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .offset(y = 40.dp)
                    .background(Color.Gray, shape = CircleShape)
            ) {
                // Aquí iría la imagen de perfil
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Nombre
        Text(
            text = "Abduldul",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones Saved / Edit Profile
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8A63F8)),
                shape = RoundedCornerShape(20),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Saved")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                shape = RoundedCornerShape(20),
                modifier = Modifier.width(120.dp)
            ) {
                Text("Edit Profile", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Productos (repetidos para simular dos)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            ProductCard("RC Kitten", "$20,99")
            ProductCard("RC Persian", "$22,99")
        }
    }
}

@Composable
fun ProductCard(title: String, price: String) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
        ) {
            // Imagen simulada
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, fontWeight = FontWeight.Bold)
        Text(text = price)
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.End),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("+")
        }
    }
}
