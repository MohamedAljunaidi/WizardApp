package com.assignment.hometab.presentation.favorite

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.theme.component.ScaffoldTopAppbar
import com.assignment.theme.theme.color

@Composable
internal fun FavoriteRoute(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onBackBtnClick: (OnBackPressedDispatcher?) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    BaseScreen(
        baseViewState = state,
        content = {
            FavoriteScreen(
                onBackBtnClick = {
                    onBackBtnClick.invoke(backDispatcher)
                },
            )
        }
    )

}

@Composable
fun FavoriteScreen(
    onBackBtnClick: () -> Unit,
) {
    ScaffoldTopAppbar(
        title = "Favorite List",
        containerColor = MaterialTheme.color.secondaryBackground,
        onNavigationIconClick = onBackBtnClick
    ) {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            LazyColumn {
                item {
                    Box(modifier = Modifier.padding(16.dp)){

                        OutlinedTextField(
                            value = "",
                            enabled = false,
                            onValueChange = { },
                            label = { Text("Search") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {


                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }
    }

}
