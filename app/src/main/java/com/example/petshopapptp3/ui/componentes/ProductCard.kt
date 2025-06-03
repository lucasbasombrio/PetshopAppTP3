package com.example.petshopapptp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Clase de producto simplificada sin conexión a la API
data class SimpleProduct(
    val name: String,
    val price: String,
    val imagePlaceholder: String = ""
)

@Composable
fun ProductCard(
    product: SimpleProduct,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        modifier = Modifier
            .width(160.dp)
            .height(220.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Imagen", color = Color.Gray)
            }

            Text(text = product.name, fontSize = 12.sp, maxLines = 1)
            Text(text = product.price, fontSize = 14.sp, color = Color.Black)

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .background(Color(0xFF7140FD), CircleShape)
                        .size(32.dp)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
