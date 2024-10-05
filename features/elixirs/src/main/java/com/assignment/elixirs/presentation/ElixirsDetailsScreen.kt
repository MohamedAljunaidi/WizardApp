package com.assignment.elixirs.presentation

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.assignment.core.bases.BaseScreen
import com.assignment.elixirs.R
import com.assignment.elixirs.domain.model.ElixirDetails
import com.assignment.extension.getActivity
import com.assignment.navigation.constants.NavigationConstants
import com.assignment.theme.component.ScaffoldTopAppbar
import com.assignment.theme.theme.Gray10
import com.assignment.theme.theme.Shapes
import com.assignment.theme.theme.Typography
import com.assignment.theme.theme.color

@Composable
internal fun ElixirsDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ElixirsDetailsViewModel = hiltViewModel(),
    onBackBtnClick: (OnBackPressedDispatcher?) -> Unit,
) {
    val elixirId =
        LocalContext.current.getActivity()?.intent?.getStringExtra(NavigationConstants.ELIXIR_ID_PARAMS)

    val elixirUiState by viewModel.elixirDetailsSuccess.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(Unit) {
        viewModel.getElixirDetails(elixirId ?: "")
    }

    BaseScreen(
        baseViewState = state,
        content = {
            ElixirDetails(
                modifier = modifier,
                elixirUiState = elixirUiState,
                onBackBtnClick = {
                    onBackBtnClick.invoke(backDispatcher)
                },
            )
        },
        onRetry = {
            viewModel.getElixirDetails(elixirId ?: "")
        }
    )

}

@Composable
fun ElixirDetails(
    modifier: Modifier = Modifier,
    elixirUiState: ElixirDetails?,
    onBackBtnClick: () -> Unit
) {

    ScaffoldTopAppbar(
        title = stringResource(id = R.string.title_elixirs_details),
        onNavigationIconClick = onBackBtnClick
    ) {

        val tabs = listOf(
            stringResource(id = R.string.title_ingredient),
            stringResource(R.string.title_inventor)
        )

        var selectedTabIndex by remember { mutableIntStateOf(0) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                Box(modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                    Card(
                        modifier = modifier,
                        shape = Shapes.medium,
                        border = BorderStroke(1.dp, Gray10),
                    ) {
                        Column(
                            modifier = modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                        ) {
                            Text(
                                text = elixirUiState?.name ?: "",
                                style = Typography.titleMedium,
                                maxLines = 2,
                                color = MaterialTheme.color.black,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = modifier.height(10.dp))

                            Text(
                                text = elixirUiState?.effect ?: "",
                                style = Typography.titleSmall,
                                color = MaterialTheme.color.subTitleColor
                            )
                            Spacer(modifier = modifier.height(10.dp))

                            Text(
                                text = elixirUiState?.difficulty ?: "",
                                style = Typography.titleSmall,
                                color = MaterialTheme.color.subTitleColor
                            )
                            Spacer(modifier = modifier.height(10.dp))

                            Text(
                                text = elixirUiState?.sideEffects ?: "",
                                style = Typography.titleSmall,
                                color = MaterialTheme.color.subTitleColor
                            )
                            Spacer(modifier = modifier.height(10.dp))

                            Text(
                                text = elixirUiState?.characteristics ?: "",
                                style = Typography.titleSmall,
                                color = MaterialTheme.color.subTitleColor
                            )
                            Spacer(modifier = modifier.height(10.dp))

                        }

                    }
                }
                Spacer(modifier = modifier.height(10.dp))
                TabRow(selectedTabIndex = selectedTabIndex,
                    contentColor = MaterialTheme.color.black,
                    indicator = { tabPositions ->
                        TabRowDefaults.PrimaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                            color = MaterialTheme.color.selectedColor
                        )
                    }

                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(title) }
                        )
                    }
                }
            }

            when (selectedTabIndex) {
                0 -> ingredientList(ingredientList = elixirUiState?.ingredients ?: listOf())
                1 -> inventorList(inventorList = elixirUiState?.inventors ?: listOf())
            }

        }
    }

}

fun LazyListScope.ingredientList(
    ingredientList: List<ElixirDetails.Ingredient?>,
) {
    items(count = ingredientList.count()) { index ->
        IngredientItem(
            ingredient = ingredientList[index],
        )
    }
}

fun LazyListScope.inventorList(
    inventorList: List<ElixirDetails.Inventor?>,
) {
    items(count = inventorList.count()) { index ->
        InventorItem(
            inventor = inventorList[index],
        )
    }
}

@Composable
private fun IngredientItem(
    modifier: Modifier = Modifier,
    ingredient: ElixirDetails.Ingredient?,
) {
    Box(modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Card(
            modifier = modifier,
            shape = Shapes.medium,
            border = BorderStroke(1.dp, Gray10),
        ) {
            Column(
                modifier = modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = ingredient?.name ?: "",
                    style = Typography.titleMedium,
                    maxLines = 2,
                    color = MaterialTheme.color.black,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }

}

@Composable
private fun InventorItem(
    modifier: Modifier = Modifier,
    inventor: ElixirDetails.Inventor?,
) {
    Box(modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Card(
            modifier = modifier,
            shape = Shapes.medium,
            border = BorderStroke(1.dp, Gray10),
        ) {
            Column(
                modifier = modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = (inventor?.firstName ?: "") + " " + (inventor?.lastName ?: ""),
                    style = Typography.titleMedium,
                    maxLines = 2,
                    color = MaterialTheme.color.black,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }

}

