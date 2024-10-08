package com.assignment.wizardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.assignment.direction.wizard.HomeDestinationEnum
import com.assignment.direction.wizard.HomeNavigator
import com.assignment.extension.navigateToDirection
import com.assignment.theme.theme.WizardTheme
import com.assignment.theme.theme.color
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp() {
        val context = LocalContext.current
        LaunchedEffect(true) {
            delay(3000L)
                HomeNavigator.navigateToDirection(
                    context,
                    destination = HomeDestinationEnum.Home
                )
            finish()
        }
        WizardTheme {
            SplashScreen()
        }
    }

    @Composable
    fun SplashScreen() {
        Surface(
            color = MaterialTheme.color.white,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "ic_logo",
                )
            }
        }
    }
}
