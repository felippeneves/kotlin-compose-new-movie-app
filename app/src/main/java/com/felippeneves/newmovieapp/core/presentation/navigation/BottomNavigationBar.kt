package com.felippeneves.newmovieapp.core.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.felippeneves.newmovieapp.ui.theme.black
import com.felippeneves.newmovieapp.ui.theme.white
import com.felippeneves.newmovieapp.ui.theme.yellow

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.PopularMovie,
        BottomNavItem.SearchMovie,
        BottomNavItem.FavoriteMovie
    )

    NavigationBar(
        contentColor = yellow,
        containerColor = black
    ) {
        //Objeto que contem a rota da tela atual
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { destination ->
            NavigationBarItem(
                icon = { Icon(imageVector = destination.icon, contentDescription = null) },
                label = { Text(text = destination.title) },
                selected = currentRoute == destination.route,
                onClick = {
                    navController.navigate(destination.route) {
                        //Garante que não há mais de uma instância por tela
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = yellow,
                    selectedTextColor = yellow,
                    indicatorColor = black,
                )

            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}

