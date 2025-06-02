package com.example.petshopapptp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.petshopapptp3.ui.screens.AccountSettingsScreen
import com.example.petshopapptp3.ui.screens.CartScreen
import com.example.petshopapptp3.ui.screens.ChangeEmailScreen
import com.example.petshopapptp3.ui.screens.ChangePasswordScreen
import com.example.petshopapptp3.ui.screens.CheckoutScreen
import com.example.petshopapptp3.ui.screens.ForgotPasswordScreen
import com.example.petshopapptp3.ui.screens.HomeScreen
import com.example.petshopapptp3.ui.screens.LoginScreen
import com.example.petshopapptp3.ui.screens.OnboardingScreen
import com.example.petshopapptp3.ui.screens.PaymentSuccessScreen
import com.example.petshopapptp3.ui.screens.ProductDetailScreen
import com.example.petshopapptp3.ui.screens.ProfileScreen
import com.example.petshopapptp3.ui.screens.RegisterScreen
import com.example.petshopapptp3.ui.screens.SecuritySettingsScreen
import com.example.petshopapptp3.ui.theme.PetshopAppTP3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetshopAppTP3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("onboarding") { OnboardingScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("forgotPassword") { ForgotPasswordScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable(
            route = "product/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) {
            ProductDetailScreen(navController)
        }
        composable("cart") { CartScreen(navController) }
        composable("checkout") { CheckoutScreen(navController) }
        composable("paymentSuccess") { PaymentSuccessScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("accountSettings") { AccountSettingsScreen(navController) }
        composable("securitySettings") { SecuritySettingsScreen(navController) }
        composable("changePassword") { ChangePasswordScreen(navController) }
        composable("changeEmail") { ChangeEmailScreen(navController) }
    }
}
