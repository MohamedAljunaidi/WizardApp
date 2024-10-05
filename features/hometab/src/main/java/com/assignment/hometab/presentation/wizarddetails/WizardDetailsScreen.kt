package com.assignment.hometab.presentation.wizarddetails

import android.content.Context
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.hometab.R
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
    onElixirItemClick: (Context, String) -> Unit,
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
                viewModel = viewModel,
                onElixirItemClick = onElixirItemClick,
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
    viewModel: WizardDetailsViewModel,
    onElixirItemClick: (Context, String) -> Unit,
    onBackBtnClick: () -> Unit
) {

    ScaffoldTopAppbar(
        title = (wizardUiState?.firstName
            ?: stringResource(id = R.string.title_wizard_details)) + " " + (wizardUiState?.lastName
            ?: ""),
        subTitle = stringResource(id = R.string.elixirs_count, wizardUiState?.elixirsCount ?: ")"),
        onNavigationIconClick = onBackBtnClick
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            var search = viewModel.searchQuery.value
            val filteredElixirs by viewModel.filteredElixirs

            LazyColumn {
                item {
                    SearchBarView(
                        query = search,
                        onQueryChange = {query ->
                            search = query
                            viewModel.setSearchQuery(query)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
                items(items = filteredElixirs) { elixirs ->
                    ElixirListItem(
                        elixirs = elixirs,
                        onItemClick = onElixirItemClick
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
    onItemClick: (Context, String) -> Unit
) {
    val context = LocalContext.current
    Box(modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Card(
            modifier = modifier
                .clickable { onItemClick(context, elixirs?.id ?: "") },
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

            }

        }
    }
}


