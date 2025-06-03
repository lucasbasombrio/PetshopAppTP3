package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.withContext

@Composable
fun RegisterScreenWithNav(navController: NavController) {
    val context = LocalContext.current

    // Local state for the three fields + checkbox + tracking submit attempt
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreeToTerms by remember { mutableStateOf(false) }
    var attemptedSubmit by remember { mutableStateOf(false) }

    RegisterScreen(
        name = name,
        email = email,
        password = password,
        agreeToTerms = agreeToTerms,
        showErrors = attemptedSubmit,
        onNameChange = { name = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onAgreementChange = { agreeToTerms = it },
        onLoginClick = {
            // Navigate back to login if the user already has an account
            navController.navigate("login") {
                popUpTo("register") { inclusive = true }
            }
        },
        onGetStartedClick = {
            attemptedSubmit = true
            if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && agreeToTerms) {
                Toast.makeText(context, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()

                navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
            }
        }
    )
}

// ---------------------------------------------------------------------------------------
// The actual RegisterScreen composable with all the updated UI styling
// ---------------------------------------------------------------------------------------
@Composable
fun RegisterScreen(
    name: String,
    email: String,
    password: String,
    agreeToTerms: Boolean,
    showErrors: Boolean,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onAgreementChange: (Boolean) -> Unit,
    onLoginClick: () -> Unit,
    onGetStartedClick: () -> Unit
) {
    // Error flags for each field
    val nameError = showErrors && name.isBlank()
    val emailError = showErrors && email.isBlank()
    val passwordError = showErrors && password.isBlank()
    // We only highlight the checkbox in red if the user tried to submit but didn't check it
    val checkboxError = showErrors && !agreeToTerms

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                // Tweak top padding if you want to push the title slightly lower; 64.dp here matches your original
                .padding(start = 24.dp, end = 24.dp, top = 64.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // ==== 1) TWO-LINE HEADER “Create New\nAccount” ====
            Text(
                text = "Create New\nAccount",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 36.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ==== 2) SUBTITLE ====
            Text(
                text = "Water is life. Water is a basic human need. " +
                        "In various lines of life, humans need water.",
                color = Color(0xFF8C8C8C),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ==== 3) NAME FIELD ====
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                placeholder = {
                    Text(
                        text = "Name",
                        color = if (nameError) Color.Red else Color(0xFF6A2FFA),
                        fontSize = 14.sp
                    )
                },
                isError = nameError,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), // fixed height for perfect alignment
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (nameError) Color.Red else Color(0xFF6A2FFA),
                    unfocusedBorderColor = if (nameError) Color.Red else Color(0xFFE5E5E5),
                    errorBorderColor = Color.Red,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color(0xFF6A2FFA),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ==== 4) EMAIL FIELD ====
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                placeholder = {
                    Text(
                        text = "Email",
                        color = if (emailError) Color.Red else Color(0xFF6A2FFA),
                        fontSize = 14.sp
                    )
                },
                isError = emailError,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (emailError) Color.Red else Color(0xFF6A2FFA),
                    unfocusedBorderColor = if (emailError) Color.Red else Color(0xFFE5E5E5),
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

            Spacer(modifier = Modifier.height(14.dp))

            // ==== 5) PASSWORD FIELD ====
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                placeholder = {
                    Text(
                        text = "Password",
                        color = if (passwordError) Color.Red else Color(0xFF6A2FFA),
                        fontSize = 14.sp
                    )
                },
                isError = passwordError,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (passwordError) Color.Red else Color(0xFF6A2FFA),
                    unfocusedBorderColor = if (passwordError) Color.Red else Color(0xFFE5E5E5),
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

            Spacer(modifier = Modifier.height(8.dp))

            // ==== 6) “Required Fields” ERROR MESSAGE (if blank on submit) ====
            if (showErrors && (name.isBlank() || email.isBlank() || password.isBlank())) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error",
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Required Fields",
                        color = Color.Red,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ==== 7) TERMS-OF-SERVICE CHECKBOX ROW ====
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = agreeToTerms,
                    onCheckedChange = onAgreementChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF6A2FFA), // Purple background when checked
                        uncheckedColor = if (checkboxError) Color.Red else Color(0xFF6A2FFA),
                        checkmarkColor = Color.White,
                        disabledCheckedColor = Color(0xFF6A2FFA).copy(alpha = 0.3f),
                        disabledUncheckedColor = Color(0xFF6A2FFA).copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.size(20.dp) // match iOS‐style checkbox size
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "I agree to the ",
                    color = Color(0xFF8C8C8C),
                    fontSize = 14.sp
                )
                Text(
                    text = "Terms of Service",
                    color = Color(0xFF6A2FFA),
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        // TODO: Open Terms of Service URL
                    }
                )
                Text(
                    text = " and ",
                    color = Color(0xFF8C8C8C),
                    fontSize = 14.sp
                )
                Text(
                    text = "Privacy Policy",
                    color = Color(0xFF6A2FFA),
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        // TODO: Open Privacy Policy URL
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ==== 8) FLEXIBLE SPACER TO PUSH FOOTER DOWN ====
            Spacer(modifier = Modifier.weight(1f))

            // ==== 9) FOOTER: “Have an account? Login” ====
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Have an account? ",
                    color = Color(0xFF8C8C8C),
                    fontSize = 14.sp
                )
                TextButton(onClick = onLoginClick) {
                    Text(
                        text = "Login",
                        color = Color(0xFF6A2FFA),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ==== 10) “Get Started” BUTTON (50.dp height, matches fields) ====
            Button(
                onClick = onGetStartedClick,
                enabled = name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && agreeToTerms,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (
                        name.isNotBlank() &&
                        email.isNotBlank() &&
                        password.isNotBlank() &&
                        agreeToTerms
                    ) {
                        Color(0xFF6A2FFA) // Purple when enabled
                    } else {
                        Color(0xFFF0F0F0) // Light gray when disabled
                    },
                    contentColor = if (
                        name.isNotBlank() &&
                        email.isNotBlank() &&
                        password.isNotBlank() &&
                        agreeToTerms
                    ) {
                        Color.White
                    } else {
                        Color(0xFF8C8C8C)
                    }
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

