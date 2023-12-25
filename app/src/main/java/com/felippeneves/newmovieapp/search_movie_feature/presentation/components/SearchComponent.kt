package com.felippeneves.newmovieapp.search_movie_feature.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.search_movie_feature.presentation.MovieSearchEvent
import com.felippeneves.newmovieapp.ui.theme.black
import com.felippeneves.newmovieapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChangeEvent: (MovieSearchEvent) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = {
            onQueryChangeEvent(MovieSearchEvent.EnteredQuery(it))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp),
        trailingIcon = {
            IconButton(onClick = {
                onSearch(query)
            }) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search_movies))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            //Seta o teclado para abrir com letras maiúsculas
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = white,
            cursorColor = white,
            placeholderColor = white,
            unfocusedTrailingIconColor = white,
            focusedTrailingIconColor = white,
            containerColor = black
        )
    )
}

@Preview
@Composable
private fun SearchComponentPreview() {
    SearchComponent(
        query = "",
        onSearch = {},
        onQueryChangeEvent = {}
    )

}