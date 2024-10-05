package com.assignment.hometab.presentation.wizard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.hometab.R
import com.assignment.theme.component.SearchBarView
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import com.assignment.theme.component.ScaffoldTopAppbar
import com.assignment.theme.theme.Gray10
import com.assignment.theme.theme.Shapes
import com.assignment.theme.theme.Typography
import com.assignment.theme.theme.color

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WizardViewModel = hiltViewModel(),
    onWizardItemClick: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getWizardList()
    }

    BaseScreen(
        baseViewState = state,
        content = {
            WizardListScreen(
                modifier = modifier,
                onWizardItemClick = onWizardItemClick,
                viewModel = viewModel
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
    onWizardItemClick: (String) -> Unit,
    viewModel: WizardViewModel,
) {

    ScaffoldTopAppbar(
        title = stringResource(id = R.string.title_wizard_list)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            var search = viewModel.searchQuery.value
            val filteredWizards by viewModel.filteredWizards

            LazyColumn {
                item {
                    SearchBarView(
                        query = search,
                        onQueryChange = { query ->
                            search = query
                            viewModel.setSearchQuery(query)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
                items(items = filteredWizards) { wizard ->
                    WizardListItem(
                        wizards = wizard,
                        onItemClick = onWizardItemClick,
                        viewModel = viewModel
                    )
                }
            }
        }
    }

}

@Composable
private fun WizardListItem(
    modifier: Modifier = Modifier,
    wizards: WizardWithFavorite,
    onItemClick: (String) -> Unit,
    viewModel: WizardViewModel
) {
    Box(modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Card(
            shape = Shapes.medium,
            border = BorderStroke(1.dp, Gray10),
        ) {
            Row(
                modifier = modifier
                    .padding(top = 12.dp, start = 12.dp, end = 20.dp, bottom = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                        .clickable { onItemClick(wizards.wizard.id ?: "")
                        }
                ) {
                    Text(
                        text = wizards.wizard.firstName + wizards.wizard.lastName,
                        style = Typography.titleMedium,
                        maxLines = 2,
                        color = MaterialTheme.color.black,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = modifier.height(10.dp))

                    Text(
                        text = stringResource(
                            R.string.elixirs_count,
                            wizards.wizard.elixirsCount ?: "0"
                        ),
                        style = Typography.titleSmall,
                        color = MaterialTheme.color.subTitleColor
                    )
                }
                Icon(
                    imageVector = if (wizards.favorite?.isFavorite == true) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            viewModel.updateFavorite(
                                (wizards.favorite?.isFavorite?.not() ?: true),
                                wizards.wizard.id ?: ""
                            )


                        }
                )
            }

        }
    }
}


