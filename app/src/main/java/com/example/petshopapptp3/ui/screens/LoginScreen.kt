package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.petshopapptp3.network.LoginRequest
import com.example.petshopapptp3.network.RetrofitClient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petshopapptp3.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.coroutines.withContext




@Composable
fun LoginScreenWithNav(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    LoginScreen(
        email = email,
        password = password,
        onEmailChange = {
            email = it
            emailError = false
        },
        onPasswordChange = {
            password = it
            passwordError = false
        },
        onLoginClick = {
            emailError = email.isBlank()
            passwordError = password.isBlank()

            if (email.isNotBlank() && password.isNotBlank()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val response = try {
                        RetrofitClient.authService.login(LoginRequest(email, password))
                    } catch (e: Exception) {
                        null
                    }

                    withContext(Dispatchers.Main) {
                        if (response != null && response.isSuccessful) {
                            // Navegación a home si el login fue exitoso
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                                Log.d("LoginResponse", response?.body()?.toString() ?: "null response")
                            }
                        } else {
                            emailError = true
                            passwordError = true
                            Log.d("LoginResponse", response?.body()?.toString() ?: "null response")
                        }
                    }
                }
            }

        },
        onGoogleClick = { /* TODO */ },
        onFacebookClick = { /* TODO */ },
        onCreateAccountClick = { navController.navigate("register") },
        isEmailError = emailError,
        isPasswordError = passwordError
    )
}

@Composable
fun LoginScreen(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    isEmailError: Boolean,
    isPasswordError: Boolean
) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                // Adjust top padding as needed
                .padding(start = 24.dp, end = 24.dp,top = 30.dp ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            // 1. Title: left-aligned with space from top
            Text(
                text = "Hello,\nWelcome Back!",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 2. Subtitle: left-aligned under the title
            Text(
                text = "Water is life. Water is a basic human need. " +
                        "In various lines of life, humans need water.",
                color = Color(0xFF8C8C8C),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Email field
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                placeholder = {
                    Text(
                        text = "Email",
                        color = Color(0xFF8C8C8C),
                        fontSize = 14.sp
                    )
                },
                isError = isEmailError,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6A2FFA),
                    unfocusedBorderColor = Color(0xFFD1D5DB),
                    errorBorderColor = Color.Red,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color(0xFF6A2FFA),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // 4. Password field
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                placeholder = {
                    Text(
                        text = "Password",
                        color = Color(0xFF8C8C8C),
                        fontSize = 14.sp
                    )
                },
                isError = isPasswordError,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6A2FFA),
                    unfocusedBorderColor = Color(0xFFD1D5DB),
                    errorBorderColor = Color.Red,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color(0xFF6A2FFA),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
            Spacer(modifier = Modifier.height(28.dp))

            // 5. Separator “or”
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = Color(0xFFD1D5DB),
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "  or  ",
                    color = Color(0xFF8C8C8C),
                    fontSize = 14.sp
                )
                Divider(
                    color = Color(0xFFD1D5DB),
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            // 6. Social buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = onGoogleClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFD1D5DB)),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Google",
                        color = Color(0xFF4285F4),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }

                OutlinedButton(
                    onClick = onFacebookClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFD1D5DB)),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ffacebook),
                        contentDescription = "Facebook",
                        tint = Color(0xFF1877F3),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "facebook",
                        color = Color(0xFF1877F3),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }

            // Push the create-account row and primary button to the bottom:
            Spacer(modifier = Modifier.height(250.dp))

            // 7. "Don't have an account? Create Account" just above the Get Started button
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account? ",
                    color = Color(0xFF8C8C8C),
                    fontSize = 14.sp
                )
                TextButton(onClick = onCreateAccountClick) {
                    Text(
                        text = "Create Account",
                        color = Color(0xFF6A2FFA),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            // 8. Primary "Get Started" button pinned to bottom
            Button(
                onClick = onLoginClick,
                enabled = email.isNotBlank() && password.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (email.isNotBlank() && password.isNotBlank())
                        Color(0xFF6A2FFA)
                    else
                        Color(0xFFF0F0F0),
                    contentColor = if (email.isNotBlank() && password.isNotBlank())
                        Color.White
                    else
                        Color(0xFF8C8C8C)
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
