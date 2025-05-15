package com.example.myapplication.ui.screen.Welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
import com.example.myapplication.domain.usecase.AuthUseCase
import com.example.myapplication.data.local.DataStoreOnBoarding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull


@Composable
fun SplashScreen(
    authUseCase: AuthUseCase,
    dataStore: DataStoreOnBoarding,
    onNavigateToSignIn: () -> Unit,
    onNavigateToSlides: () -> Unit,
    onNavigateToHome: () -> Unit

) {
    val context = LocalContext.current
    val token by authUseCase.token.collectAsState(initial = "")

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
            modifier = Modifier.scale(scaleX = 2.5f, scaleY = 2.5f)
        )

        LaunchedEffect(Unit) {
            delay(3000)

            val completed = dataStore.onBoardingCompleted.firstOrNull() ?: false

            if (completed) {
                if (token.isNotEmpty()) {
                    onNavigateToHome()
                } else {
                    onNavigateToSignIn()
                }
            } else {
                onNavigateToSlides()
            }
        }
    }
}


//@Composable
//fun SplashScreen(authUseCase: AuthUseCase,
//                 dataStore: DataStoreOnBoarding,
//    //onNavigationToSlidesScrn: () -> Unit,
//    //onNavigationToRegistationScreen: () -> Unit,
//    //onNavigationToHome: () -> Unit,
//                 onNavigateToSignIn: () -> Unit
//){
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF48B2E7)),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ){
//        Image(
//            painter = painterResource(R.drawable.matule_me),
//            contentDescription = null,
//            modifier = Modifier.scale(scaleX = 2.5f, scaleY = 2.5f))
////        LaunchedEffect(Unit) {
////            delay(3000)
////
////            val completed = dataStore.onBoardingCompleted.first()
////
////            if (completed) {
////                onNavigationToRegistationScreen()
////            } else {
////                onNavigationToSlidesScrn()
////            }
////        }
//        LaunchedEffect(Unit) {
//            delay(3000)
//
//            val completed = dataStore.onBoardingCompleted.firstOrNull() ?: false
//
//            if (completed) {
//                onNavigateToSignIn()
//                //onNavigationToRegistationScreen()
//            } else {
//                onNavigateToSignIn()
//                //onNavigationToRegistationScreen()
//            }
//        }
//
//    }
//}