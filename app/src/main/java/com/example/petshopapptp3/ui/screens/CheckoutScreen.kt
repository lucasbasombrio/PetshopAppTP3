package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.petshopapptp3.ui.theme.Poppins
import com.example.petshopapptp3.ui.components.CustomTopBar

@Composable
fun CheckoutScreen(navController: NavController) {
    var selectedMethod by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Payment Method",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Choose your payment method",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 29.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(24.dp))

                PaymentOptionCard(
                    method = "Paypal",
                    selected = selectedMethod == "Paypal",
                    onSelect = { selectedMethod = "Paypal" }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PaymentOptionCard(
                    method = "Bank Transfer",
                    selected = selectedMethod == "Bank Transfer",
                    onSelect = { selectedMethod = "Bank Transfer" }
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        navController.navigate("paymentSuccess")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    enabled = selectedMethod != null,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedMethod != null)
                            Color(0xFF7140FD)
                        else
                            Color(0xFFE5E4E3),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Checkout",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun PaymentOptionCard(
    method: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    val borderColor = if (selected) Color(0xFF7140FD) else Color.LightGray
    val textColor = if (selected) Color(0xFF7140FD) else Color.Gray

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(BorderStroke(2.dp, borderColor), RoundedCornerShape(20.dp))
            .clickable { onSelect() }
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = method,
            fontFamily = Poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = textColor
        )

        CustomRadioButton(
            selected = selected,
            onClick = onSelect
        )

    }
}

//Botón circular personalizado para seleccionar el método de pago
@Composable
fun CustomRadioButton(selected: Boolean, onClick: () -> Unit) {
    val borderColor = if (selected) Color(0xFF7140FD) else Color.Gray
    val borderWidth = if (selected) 4.dp else 1.dp
    val outerSize = 24.dp

    Box(
        modifier = Modifier
            .size(outerSize)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(outerSize - 4.dp)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = CircleShape
                )
                .background(Color.Transparent, CircleShape),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    val navController = rememberNavController()
    PetshopAppTP3Theme {
        CheckoutScreen(navController)
    }
}
