package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme

@Composable
fun PaymentSuccessScreen(navController: NavController? = null) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top: 90dp
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 90.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Payment\nSuccess!",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 56.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "your order is being prepared by the shop, the courier will send it to your address",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    navController?.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Go Home",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentSuccessPreview() {
    PetshopAppTP3Theme {
        PaymentSuccessScreen()
    }
}
