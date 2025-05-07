package com.example.myapplication.ui.screen.Welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
import com.example.myapplication.ui.data.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(authUseCase: AuthUseCase,
                 onNavigationToSlidesScrn: () -> Unit,
                 onNavigationToRegistationScreen: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF48B2E7)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(R.drawable.matule_me),
            contentDescription = null,
            modifier = Modifier.scale(scaleX = 2.5f, scaleY = 2.5f))
        LaunchedEffect(Unit) {
            delay(3000)
            onNavigationToSlidesScrn()
        }
    }
}