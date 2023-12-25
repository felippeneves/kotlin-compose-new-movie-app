package com.felippeneves.newmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.felippeneves.newmovieapp.core.presentation.MainScreen
import com.felippeneves.newmovieapp.ui.theme.NewMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Associa a activity com o tema configurado do Splash screen personalizado (res/values/splash.xml)
        installSplashScreen()
        setContent {
            NewMovieAppTheme {
                MainScreen(navController = rememberNavController())
            }
        }
    }
}