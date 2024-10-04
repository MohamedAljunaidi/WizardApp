package com.assignment.core.extensions

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.assignment.core.R
import com.assignment.core.model.ResultException
import com.assignment.core.model.ServerExceptionType
import com.assignment.theme.theme.color

@Composable
fun ResultException.HandleErrorState(
    dialogDismissCallBack: (() -> Unit)? = null,
    dialogTryAgainCallBack: (() -> Unit)? = null,
) {
    when (this.errorModel?.errorType ?: ServerExceptionType.UnExpectedError) {
        ServerExceptionType.Forbidden -> {
            ShowGeneralErrorDialog(
                onDismiss = {
                    dialogDismissCallBack?.invoke()
                },
                onTryAgain = {
                    dialogTryAgainCallBack?.invoke()
                },
            )
        }

        ServerExceptionType.ServerError, ServerExceptionType.UnExpectedError -> {
            ShowGeneralErrorDialog(
                onDismiss = {
                    dialogDismissCallBack?.invoke()
                },
                onTryAgain = {
                    dialogTryAgainCallBack?.invoke()
                },

                )
        }

        ServerExceptionType.ClientError -> {
            ShowGeneralErrorDialog(

                onDismiss = {
                    dialogDismissCallBack?.invoke()
                },
                onTryAgain = {
                    dialogTryAgainCallBack?.invoke()
                },
                message = this.errorModel?.errorDescription
            )
        }

        ServerExceptionType.NoInternetConnection -> {
            ShowNetworkConnectionErrorDialog {
                dialogTryAgainCallBack?.invoke()
            }
        }
    }
}

@Composable
fun ShowGeneralErrorDialog(
    onDismiss: (() -> Unit)? = null,
    onTryAgain: (() -> Unit)? = null,
    message: String? = null
) {

    val shouldShowDialog = remember { mutableStateOf(true) }

    if (shouldShowDialog.value) {

        AlertDialog(
            onDismissRequest = {
                onDismiss?.invoke()
                shouldShowDialog.value = false
            },
            title = {
                Text(
                    text = stringResource(id = R.string.error_general_title),
                    color = MaterialTheme.color.black
                )
            },
            text = {
                Text(
                    text = message ?: stringResource(id = R.string.error_general_message),
                    color = MaterialTheme.color.black
                )
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.color.white,
                        contentColor = MaterialTheme.color.black
                    ),
                    onClick = {
                        onTryAgain?.invoke()
                        onDismiss?.invoke()
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(
                        text = stringResource(id = if (onTryAgain != null) R.string.dialog_try_again else R.string.dialog_ok),
                    )
                }
            },
            dismissButton = {
                if (onTryAgain != null) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.color.white,
                            contentColor = MaterialTheme.color.black
                        ),
                        onClick = {
                            onDismiss?.invoke()
                            shouldShowDialog.value = false

                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.dialog_ok),
                        )
                    }
                }
            }
        )
    }

}

@Composable
fun ShowNetworkConnectionErrorDialog(
    onDismiss: (() -> Unit)? = null,
    onTryAgain: (() -> Unit)? = null
) {
    val shouldShowDialog = remember { mutableStateOf(true) }
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                onDismiss?.invoke()
                shouldShowDialog.value = false
            },
            title = {
                Text(text = stringResource(id = R.string.error_network_connection_title),   color = MaterialTheme.color.black)
            },
            text = {
                Text(text = stringResource(id = R.string.error_network_connection_message),   color = MaterialTheme.color.black)
            },
            confirmButton = {
                Button(  colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.color.white,
                    contentColor = MaterialTheme.color.black
                ),
                    onClick = {
                        onTryAgain?.invoke()
                        onDismiss?.invoke()
                        shouldShowDialog.value = true
                    }
                ) {
                    Text(text = stringResource(id = R.string.dialog_try_again))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }

}
