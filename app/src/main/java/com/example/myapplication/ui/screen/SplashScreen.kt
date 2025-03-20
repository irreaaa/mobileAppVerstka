package com.example.myapplication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
import com.example.myapplication.ui.data.domain.usecase.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


@Composable
fun SplashScreen(authUseCase: AuthUseCase,
                 onNavigationToProfileScreen: () -> Unit,
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
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null)
        LaunchedEffect(Unit) {
            authUseCase.token.collect{
                if(it != "") {
                    onNavigationToProfileScreen()
                    return@collect
                }
                onNavigationToRegistationScreen()
            }
        }
    }
}