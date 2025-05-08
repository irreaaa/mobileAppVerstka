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
import com.example.myapplication.ui.data.remote.RetrofitClient
import com.example.myapplication.ui.screen.Otp.OtpScrn
import com.example.myapplication.ui.screen.RecoverPassword.RecoverPasswordScrn
import com.example.myapplication.ui.screen.SignIn.SignInScrn
import com.example.myapplication.ui.screen.SignUp.SignUpScrn
import com.example.myapplication.ui.screen.Welcome.SplashScreen
import com.example.myapplication.ui.theme.MatuleTheme
import kotlinx.serialization.Serializable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val authRepository = AuthRepository(RetrofitClient.retrofit)
            val localStorage = LocalStorage(applicationContext)
            val dataStore = DataStoreOnBoarding(LocalContext.current)
            val authUseCase = AuthUseCase(localStorage, authRepository )

            val navController = rememberNavController()
            MatuleTheme {
                NavHost(navController, startDestination = SplashScreen){

                    composable<SplashScreen>{
                        SplashScreen(
                            authUseCase = authUseCase,
                            dataStore = dataStore,
                            onNavigationToSlidesScrn = {
                                navController.navigate(route = Slides)
                            },
                            onNavigationToRegistationScreen = {
                                navController.navigate(route = SignIn)
                            }
                        )
                    }

                    composable<Slides> {
                        SlidesScrn(
                            onNavigateToSignInScrn = {
                                navController.navigate(route = SignIn){
                                    popUpTo(route = Slides) {inclusive = true}
                                }
                            },
                            dataStore = dataStore
                        )
                    }

                    composable<SignIn> {
                        SignInScrn(
                            authUseCase = authUseCase,
                            navController = navController,
                            onSignInSuccess = {
                                navController.navigate(route = Otp)
                            }
                        )
                    }

                    composable<RecoverPassword> {
                        RecoverPasswordScrn()
                    }

                    composable<Registration> {
                        SignUpScrn(
                            onNavigationToProfile = {
                                navController.navigate(route = Profile)
                            },
                            navController = navController
                        )
                    }

                    composable<Otp> {
                        OtpScrn {
                            navController.navigate(route = Profile)
                        }
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