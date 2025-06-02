package com.example.petshopapptp3.ui.screens

import androidx.compose.foundation.Image
import com.example.petshopapptp3.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme
import com.example.petshopapptp3.ui.theme.Poppins
import com.example.petshopapptp3.ui.components.CustomTopBar
import androidx.compose.ui.res.painterResource

@Composable
fun AddNewPaymentScreen(navController: NavController) {
    val focusManager = LocalFocusManager.current

    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    val isFormValid = cardNumber.isNotBlank() &&
            cardName.isNotBlank() &&
            expiryDate.isNotBlank() &&
            cvv.isNotBlank()

    val isCVVError = cvv.isBlank()

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Payment Method",
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Add New Payment",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(20.dp))

            PaymentTextField(
                value = cardNumber,
                onValueChange = {
                    cardNumber = it.filter { char -> char.isDigit() }.take(16)
                },
                label = "Card Number",
                isError = false,
                keyboardType = KeyboardType.Number,
                visualTransformation = CardNumberVisualTransformation()
            )

            PaymentTextField(
                value = cardName,
                onValueChange = {
                    cardName = it
                },
                label = "Card Name",
                isError = false,
                keyboardType = KeyboardType.Text
            )

            PaymentTextField(
                value = expiryDate,
                onValueChange = {
                    expiryDate = it.filter { char -> char.isDigit() }.take(4)
                },
                label = "Expired",
                isError = false,
                keyboardType = KeyboardType.Number,
                visualTransformation = DateVisualTransformation()
            )


            PaymentTextField(
                value = cvv,
                onValueChange = {
                    cvv = it.filter { char -> char.isDigit() }.take(3)
                },
                label = "CVV",
                isError = isCVVError,
                keyboardType = KeyboardType.NumberPassword,
                visualTransformation = PasswordVisualTransformation()
            )


            if (isCVVError) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.req),
                        contentDescription = "Error icon",
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .size(width = 117.dp, height = 19.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }


            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (isFormValid) {
                        navController.navigate("checkout")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                enabled = isFormValid,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid)
                        Color(0xFF7140FD)
                    else
                        Color(0xFFE5E4E3),
                )
            ) {
                Text(
                    text = "Save",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun PaymentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val errorRed = Color(0xFFFF4D4F)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                letterSpacing = 0.sp
            )
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        visualTransformation = visualTransformation,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF7140FD),
            focusedLabelColor = Color(0xFF7140FD),
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            errorBorderColor = errorRed,
            errorLabelColor = errorRed,
            cursorColor = Color(0xFF7140FD),
            focusedTextColor = Color(0xFF7140FD),
            unfocusedTextColor = Color(0xFF7140FD),
        )
    )
}

//Para transformar el número de tarjeta y que aparezcan los /
fun CardNumberVisualTransformation(): VisualTransformation {
    return object : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val trimmed = text.text.take(16)
            val formatted = trimmed.chunked(4).joinToString(" ")
            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when {
                        offset <= 4 -> offset
                        offset <= 8 -> offset + 1
                        offset <= 12 -> offset + 2
                        offset <= 16 -> offset + 3
                        else -> 19
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return when {
                        offset <= 4 -> offset
                        offset <= 9 -> offset - 1
                        offset <= 14 -> offset - 2
                        offset <= 19 -> offset - 3
                        else -> 16
                    }
                }
            }
            return TransformedText(AnnotatedString(formatted), offsetMapping)
        }
    }
}

//Para transformar la fecha de expiración
fun DateVisualTransformation(): VisualTransformation {
    return object : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val trimmed = text.text.take(4)
            val formatted = when (trimmed.length) {
                0, 1, 2 -> trimmed
                else -> "${trimmed.take(2)}/${trimmed.drop(2)}"
            }
            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return if (offset <= 2) offset else offset + 1
                }
                override fun transformedToOriginal(offset: Int): Int {
                    return if (offset <= 2) offset else offset - 1
                }
            }
            return TransformedText(AnnotatedString(formatted), offsetMapping)
        }
    }
}




@Composable
@Preview(showSystemUi = true)
fun AddNewPaymentScreenPreview() {
    PetshopAppTP3Theme {
        val navController = rememberNavController()
        AddNewPaymentScreen(navController)
    }
}