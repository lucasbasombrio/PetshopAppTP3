package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petshopapptp3.ui.theme.Poppins
import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme
import com.example.petshopapptp3.ui.componentes.CustomTopBar

@Composable
fun ChangePasswordScreen(navController: NavController) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Change Password",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "New Password",
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
            PasswordTextField(
                value = newPassword,
                onValueChange = { newPassword = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Confirm Password",
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
            PasswordTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    navController.navigate("securitySettings")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7140FD),
                    contentColor = Color.White
                ),
                enabled = true
            ) {
                Text(
                    text = "Change Password",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        visualTransformation = VisualTransformation.None,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xFFE5E4E3),
            focusedBorderColor = Color(0xFFE5E4E3),
            cursorColor = Color(0xFFB3B1B0),
            unfocusedTextColor = Color(0xFFB3B1B0),
            focusedTextColor = Color(0xFFB3B1B0)
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun ChangePasswordScreenPreview() {
    PetshopAppTP3Theme {
        val navController = rememberNavController()
        ChangePasswordScreen(navController)
    }
}
