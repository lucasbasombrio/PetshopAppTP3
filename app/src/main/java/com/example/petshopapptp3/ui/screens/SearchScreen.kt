package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.example.petshopapptp3.ui.components.FilterChip
import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme

@Composable
fun SearchScreen(onBack: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Back + Title
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .background(Color(0xFFF8F8F8), shape = CircleShape)
                        .padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text("Search", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Search Box
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search your Product") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Chips
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(12.dp))
                    .size(44.dp)
            ) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Swap", tint = Color.Black)
            }
            FilterChip(text = "Food", selected = true)
            FilterChip(text = "Toys")
            FilterChip(text = "Accessories")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Recent", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        repeat(3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Royal Canin Persian 500g",
                    color = Color.Gray
                )
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Close, contentDescription = "Remove")
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    PetshopAppTP3Theme {
        SearchScreen(onBack = {})
    }
}