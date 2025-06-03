package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

import androidx.compose.ui.unit.dp




@Composable
fun LoginScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Pantalla: Login",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(
                onClick = { navController.navigate("addPayment") }
            ) {
                Text(text = "payment")
            }
            Button(
                onClick = { navController.navigate("changePassword") }
            ) {
                Text(text = "password")
            }
            Button(
                onClick = { navController.navigate("changeEmail") }
            ) {
                Text(text = "email")
            }
        }
    }
}

