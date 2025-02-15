package com.example.myapplication


import MatuleTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.ui.screen.SignIn.SignInScrn
import com.example.myapplication.ui.screen.SignIn.SignUpScrn
import com.example.myapplication.ui.screen.SignIn.RecoverPasswordScrn



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleTheme{
                RecoverPasswordScrn()
            }
        }
    }
}
