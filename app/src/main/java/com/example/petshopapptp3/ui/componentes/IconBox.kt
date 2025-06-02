package com.example.petshopapptp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconBox(icon: ImageVector, contentDescription: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .shadow(0.0001.dp, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}
