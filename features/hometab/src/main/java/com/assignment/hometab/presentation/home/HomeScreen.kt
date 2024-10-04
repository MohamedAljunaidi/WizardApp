package com.assignment.hometab.presentation.home

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.theme.component.SearchBarView
import com.assignment.hometab.domain.home.model.Wizard
import com.assignment.theme.component.ScaffoldTopAppbar
import com.assignment.theme.theme.Gray10
import com.assignment.theme.theme.Shapes
import com.assignment.theme.theme.Typography
import com.assignment.theme.theme.color

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val wizardsUiState by viewModel.wizardSuccess.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()


    BaseScreen(
        baseViewState = state,
        content = {
            WizardListScreen(
                modifier = modifier,
                wizardUiState = wizardsUiState,

                onWizardItemClick = {},

                )
        },
        onRetry = {
            viewModel.getWizardList()
        }
    )

}

@Composable
fun WizardListScreen(
    modifier: Modifier = Modifier,
    wizardUiState: List<Wizard>?,
    onWizardItemClick: (Wizard) -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    ScaffoldTopAppbar(
        title = "Wizard List",
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) { _ ->
            Column {
                val filteredWizards = wizardUiState?.filter { item ->
                    item.firstName?.contains(
                        searchQuery,
                        ignoreCase = true
                    ) == true || item.lastName?.contains(searchQuery, ignoreCase = true) == true
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
                    items(items = filteredWizards) { wizard ->
                        WizardListItem(
                            wizards = wizard,
                            onItemClick = onWizardItemClick
                        )
                    }
                }
            }
        }
    }

}

@Composable
private fun WizardListItem(
    modifier: Modifier = Modifier,
    wizards: Wizard,
    onItemClick: (Wizard) -> Unit
) {
    Box(modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Card(
            modifier = modifier
                .clickable { onItemClick(wizards) },
            shape = Shapes.medium,
            border = BorderStroke(1.dp, Gray10),
        ) {
            Column(
                modifier = modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = wizards.firstName + wizards.lastName,
                    style = Typography.titleMedium,
                    maxLines = 2,
                    color = MaterialTheme.color.black,
                    overflow = TextOverflow.Ellipsis,

                    )
                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = wizards.elixirsCount + " " + "elixirs",
                    style = Typography.labelMedium,
                    color = Gray10
                )
            }

        }
    }
}


