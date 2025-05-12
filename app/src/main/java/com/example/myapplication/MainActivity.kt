package com.example.myapplication

import SlidesScrn
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.data.AuthRepository
import com.example.myapplication.ui.data.domain.usecase.AuthUseCase
import com.example.myapplication.ui.data.local.DataStoreOnBoarding
import com.example.myapplication.ui.data.local.LocalStorage
import com.example.myapplication.ui.data.remote.AuthInterceptor
import com.example.myapplication.ui.data.remote.RetrofitClient
import com.example.myapplication.ui.screen.Home.HomeScreen
import com.example.myapplication.ui.screen.Otp.OtpScrn
import com.example.myapplication.ui.screen.RecoverPassword.RecoverPasswordScrn
import com.example.myapplication.ui.screen.SignIn.SignInScrn
import com.example.myapplication.ui.screen.SignUp.SignUpScrn
import com.example.myapplication.ui.screen.Welcome.SplashScreen
import com.example.myapplication.ui.theme.MatuleTheme
import kotlinx.serialization.Serializable
import org.koin.android.ext.android.get


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val dataStore = DataStoreOnBoarding(LocalContext.current)
            val authUseCase: AuthUseCase = get()
            MatuleTheme {
                NavHost(navController, startDestination = SplashScreen){

                    composable<SplashScreen>{
                        SplashScreen(
                            authUseCase = authUseCase,
                            dataStore = dataStore,
//                            onNavigationToSlidesScrn = {
//                                navController.navigate(route = Slides)
//                            },
//                            onNavigationToHome = {
//                                navController.navigate(route = Home)
//                            }
//                            onNavigationToRegistationScreen = {
//                                navController.navigate(route = Registration)
//                            }
                            onNavigateToSignIn = {
                                navController.navigate(route = SignIn)
                            }
                        )
                    }
//
//                    composable<Slides> {
//                        SlidesScrn(
//                            onNavigateToSignInScrn = {
//                                navController.navigate(route = SignIn){
//                                    popUpTo(route = Slides) {inclusive = true}
//                                }
//                            },
//                            dataStore = dataStore
//                        )
//                    }
//
                    composable<SignIn> {
                        SignInScrn(
                            navController = navController,
                            onSignInSuccess = {
                                //navController.navigate(route = Otp)
                                navController.navigate(route = Home)
                            }
                        )
                    }
//
//                    composable<RecoverPassword> {
//                        RecoverPasswordScrn()
//                    }
//
//                    composable<Registration> {
//                        SignUpScrn(
//                            onNavigationToProfile = {
//                                navController.navigate(route = Profile)
//                            },
//                            navController = navController
//                        )
//                    }
//
//                    composable<Otp> {
//                        OtpScrn {
//                            navController.navigate(route = Profile)
//                        }
//                    }

                    composable<Home> {
                        HomeScreen(
                            navController,
                            authUseCase)
                    }
                }
            }
        }
    }
}

@Serializable
object SplashScreen
@Serializable
object Registration
@Serializable
object Profile
@Serializable
object RecoverPassword
@Serializable
object SignIn
@Serializable
object Otp
@Serializable
object Slides
@Serializable
object Home