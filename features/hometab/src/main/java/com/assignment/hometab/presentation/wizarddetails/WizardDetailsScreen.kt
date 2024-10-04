package com.assignment.hometab.presentation.wizarddetails

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.theme.component.SearchBarView
import com.assignment.theme.component.ScaffoldTopAppbar
import com.assignment.theme.theme.Gray10
import com.assignment.theme.theme.Shapes
import com.assignment.theme.theme.Typography
import com.assignment.theme.theme.color

@Composable
internal fun WizardDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: WizardDetailsViewModel = hiltViewModel(),
    onBackBtnClick: (OnBackPressedDispatcher?) -> Unit,
    wizardId: String?
) {
    val wizardUiState by viewModel.wizardDetailsSuccess.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    LaunchedEffect(Unit) {
        viewModel.getWizardDetails(wizardId ?: "")
    }
    BaseScreen(
        baseViewState = state,
        content = {
            WizardDetails(
                modifier = modifier,
                wizardUiState = wizardUiState,
//                onWizardItemClick = {},
                onBackBtnClick = {
                    onBackBtnClick.invoke(backDispatcher)
                },
            )
        },
        onRetry = {
            viewModel.getWizardDetails(wizardId ?: "")
        }
    )

}

@Composable
fun WizardDetails(
    modifier: Modifier = Modifier,
    wizardUiState: WizardDetails?,
//    onWizardItemClick: (Wizard) -> Unit,
    onBackBtnClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    ScaffoldTopAppbar(
        title = (wizardUiState?.firstName ?: "Details") + " " + (wizardUiState?.lastName ?: ""),
        subTitle = (wizardUiState?.elixirsCount ?: "0") + " " + "elixirs",
        onNavigationIconClick = onBackBtnClick
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) { _ ->
            val filteredElixirs = wizardUiState?.elixirs?.filter { item ->
                item?.name?.contains(
                    searchQuery,
                    ignoreCase = true
                ) == true
            } ?: emptyList()

            LazyColumn {
                item {
                    SearchBarView(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
                items(items = filteredElixirs) { elixirs ->
                    ElixirListItem(
                        elixirs = elixirs,
//                            onItemClick = onWizardItemClick
                    )
                }
            }
        }
    }

}

@Composable
private fun ElixirListItem(
    modifier: Modifier = Modifier,
    elixirs: WizardDetails.Elixir?,
//    onItemClick: (Wizard) -> Unit
) {
    Box(modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Card(
            modifier = modifier,
//                .clickable { onItemClick(wizards) },
            shape = Shapes.medium,
            border = BorderStroke(1.dp, Gray10),
        ) {
            Column(
                modifier = modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = elixirs?.name ?: "",
                    style = Typography.titleMedium,
                    maxLines = 2,
                    color = MaterialTheme.color.black,
                    overflow = TextOverflow.Ellipsis,

                    )
                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = elixirs?.name ?: "",
                    style = Typography.labelMedium,
                    color = Gray10
                )
            }

        }
    }
}


