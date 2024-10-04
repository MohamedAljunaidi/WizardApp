package com.assignment.hometab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.extension.SetNavGraph
import com.assignment.hometab.navigation.homeNavigation
import com.assignment.navigation.constants.NavigationConstants
import com.assignment.theme.theme.WizardTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.assignment.theme.theme.color

@AndroidEntryPoint
class HomeTabActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WizardTheme {
                navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ) { innerPadding ->
                    SetNavGraph(
                        navController = navController,
                        startDestination = NavigationConstants.HOME_PATH,
                    ) {
                        homeNavigation(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.Profile)


    val startDestination = BottomNavItem.Home
    var selectedTab by remember {
        mutableIntStateOf(items.indexOf(startDestination))
    }
    Surface(
        border = BorderStroke(.5.dp, MaterialTheme.color.white),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        NavigationBar(
            tonalElevation = 8.dp,
            containerColor = MaterialTheme.color.fieldColor
        ) {
            items.forEachIndexed { index, screen ->
                val isSelected = index == selectedTab

                NavigationBarItem(
                    icon = { Icon(screen.icon, contentDescription = screen.name) },
                    label = { Text(screen.name) },
                    selected = isSelected,
                    onClick = {
                        selectedTab = index
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },

                    colors = NavigationBarItemDefaults
                        .colors(
                            selectedIconColor = MaterialTheme.color.selectedColor,
                            selectedTextColor = MaterialTheme.color.selectedColor,
                            unselectedIconColor = MaterialTheme.color.unSelectedColor,
                            unselectedTextColor = MaterialTheme.color.unSelectedColor,
                            indicatorColor = MaterialTheme.color.fieldColor
                        )
                )
            }
        }
    }
}

sealed class BottomNavItem(val name: String, val route: String, val icon: ImageVector) {
    data object Home : BottomNavItem("Home", NavigationConstants.HOME_PATH, Icons.Default.Home)
    data object Profile :
        BottomNavItem("Favorite", NavigationConstants.FAVORITE_PATH, Icons.Default.Favorite)
}