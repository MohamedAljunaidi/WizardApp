package com.assignment.theme.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.assignment.theme.theme.color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopAppbar(
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    title: String,
    subTitle: String? = null,
    onNavigationIconClick: () -> Unit,
    navigationIcon: Painter = rememberVectorPainter(image = Icons.AutoMirrored.Outlined.ArrowBack),
    snackbarHost: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        containerColor = containerColor,
        contentColor = contentColor,
        snackbarHost = snackbarHost,
        topBar = {
            Surface(shadowElevation = 1.dp) {
                TopAppBar(
                    title = {
                        Column {
                            Text(text = title)
                            if (subTitle != null)
                                Text(text = subTitle, style = MaterialTheme.typography.labelSmall)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.color.topAppBar,
                        navigationIconContentColor = MaterialTheme.color.black,
                        titleContentColor = MaterialTheme.color.black,
                        actionIconContentColor = MaterialTheme.color.black,
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                onNavigationIconClick.invoke()
                                delay(200)
                            }
                        }) {
                            Icon(
                                painter = navigationIcon,
                                contentDescription = "navigationIcon"
                            )
                        }
                    },
                )
            }
        },
        bottomBar = bottomBar,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopAppbar(
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    title: String,
    snackbarHost: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,

    ) {
    Scaffold(
        snackbarHost = snackbarHost,
        containerColor = containerColor,
        contentColor = contentColor,
        topBar = {
            Surface(
                shadowElevation = 2.dp
            ) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = title)
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.color.topAppBar,
                        navigationIconContentColor = MaterialTheme.color.black,
                        titleContentColor = MaterialTheme.color.black,
                        actionIconContentColor = MaterialTheme.color.black,
                    )
                )
            }
        },
        bottomBar = bottomBar,
        content = content
    )
}
