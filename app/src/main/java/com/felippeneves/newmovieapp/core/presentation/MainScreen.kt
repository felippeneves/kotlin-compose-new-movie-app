package com.felippeneves.newmovieapp.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.felippeneves.newmovieapp.core.presentation.navigation.BottomNavigationBar
import com.felippeneves.newmovieapp.core.presentation.navigation.DetailsScreenNav
import com.felippeneves.newmovieapp.core.presentation.navigation.NavigationGraph
import com.felippeneves.newmovieapp.core.presentation.navigation.currentRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            if (currentRoute(navController = navController) != DetailsScreenNav.DetailsScreen.route) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavigationGraph(navController = navController)
        }
    }
}