package com.example.petshopapptp3.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(selectedIndex: Int = 0, onTabSelected: (Int) -> Unit = {}) {
    NavigationBar(containerColor = Color(0xFFF8F8F8)) {
        val items = listOf(Icons.Default.Home, Icons.Default.Notifications, Icons.Default.ShoppingCart, Icons.Default.Person)

        items.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = if (index == selectedIndex) Color(0xFF7140FD) else Color.Gray
                    )
                }
            )
        }
    }
}