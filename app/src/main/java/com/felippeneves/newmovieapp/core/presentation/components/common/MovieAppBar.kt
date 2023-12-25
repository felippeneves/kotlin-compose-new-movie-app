package com.felippeneves.newmovieapp.core.presentation.components.common

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.ui.theme.black
import com.felippeneves.newmovieapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    textColor: Color = white,
    backgroundColor: Color = black
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = title),
                color = textColor
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = backgroundColor
        ),
        modifier = modifier
    )
}

@Preview
@Composable
private fun MovieAppBarPreview() {
    MovieAppBar(title = R.string.movie_app_bar_test)
}