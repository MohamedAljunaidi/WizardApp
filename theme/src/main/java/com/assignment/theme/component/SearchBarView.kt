package com.assignment.theme.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.assignment.theme.theme.color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { } ,
        active = false,
        onActiveChange = { },
        placeholder = { Text("Search (ex: Fred)") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.color.fieldColor,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = Color.Gray,
                unfocusedTextColor = Color.Gray,
                cursorColor = Color.Black,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {}
}
