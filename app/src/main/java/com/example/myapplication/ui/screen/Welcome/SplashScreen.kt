package com.example.myapplication.ui.screen.Welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigateToSignIn: () -> Unit,
    onNavigateToSlides: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val token by viewModel.token.collectAsState()
    val completed by viewModel.isOnBoardingCompleted.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF48B2E7)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.matule_me),
            contentDescription = null,
            modifier = Modifier.scale(2.5f)
        )

        LaunchedEffect(Unit) {
            delay(3000)

            if (!completed) {
                viewModel.completeOnBoarding()
                onNavigateToSlides()
            } else if (token.isNotEmpty()) {
                onNavigateToHome()
            } else {
                onNavigateToSignIn()
            }
        }
    }
}
