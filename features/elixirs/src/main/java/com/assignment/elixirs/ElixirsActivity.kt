package com.assignment.elixirs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.assignment.elixirs.navigation.elixirsNavigation
import com.assignment.extension.SetNavGraph
import com.assignment.theme.theme.WizardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElixirsActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WizardTheme {
                navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    SetNavGraph(navController = navController) {
                        elixirsNavigation(
                            navController = navController
                        )

                    }
                }
            }
        }
    }
}