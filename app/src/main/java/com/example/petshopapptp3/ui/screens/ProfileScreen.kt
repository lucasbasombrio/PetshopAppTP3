package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController
import com.example.petshopapptp3.R

@Composable
fun ProfileScreen(navController: NavHostController) {
    var isSellerMode by remember { mutableStateOf(false) }

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
                onClick = { isSellerMode = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSellerMode) Color.LightGray else Color(0xFF8A63F8)
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier.weight(1f)
            ) {
                Text("Profile", color = if (isSellerMode) Color.Black else Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { isSellerMode = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSellerMode) Color(0xFF8A63F8) else Color.LightGray
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier.weight(1f)
            ) {
                Text("Seller Mode", color = if (isSellerMode) Color.White else Color.Black)
            }
        }

        if (isSellerMode) {
            Spacer(modifier = Modifier.height(24.dp))

            // Banner + logo vendedor
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_profile_seller),
                    contentDescription = "Fondo seller",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_seller),
                    contentDescription = "Logo seller",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(y = 40.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Pittashop",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatColumn("109", "Followers")
                StatColumn("992", "Following")
                StatColumn("80", "Sales")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FilterButton("Product", selected = true)
                FilterButton("Sold", selected = false)
                FilterButton("Stats", selected = false)
            }

        } else {
            Spacer(modifier = Modifier.height(24.dp))

            // Banner + avatar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_profile_user),
                    contentDescription = "Fondo usuario",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_avatar),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(y = 40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Abduldul",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                ProductCard("RC Kitten", "$20,99")
                ProductCard("RC Persian", "$22,99")
            }
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
            // Imagen simulada o real
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

@Composable
fun StatColumn(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = number, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = label)
    }
}

@Composable
fun FilterButton(label: String, selected: Boolean) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFF8A63F8) else Color.LightGray
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(label, color = if (selected) Color.White else Color.Black)
    }
}
