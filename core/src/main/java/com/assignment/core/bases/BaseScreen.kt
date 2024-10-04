package com.assignment.core.bases

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.assignment.core.extensions.HandleErrorState

@Composable
fun BaseScreen(
    baseViewState: BaseViewState,
    content: @Composable () -> Unit,
    onRetry: () -> Unit = {}
) {
    when (baseViewState) {
        is BaseViewState.Loading -> {
            LoadingScreen()
        }

        is BaseViewState.Error -> {

            baseViewState.resultException.HandleErrorState(
                dialogTryAgainCallBack = {
                    onRetry.invoke()
                }
            )
            content()
        }

        else -> {
            content()
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
