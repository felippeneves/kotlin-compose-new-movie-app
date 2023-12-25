package com.felippeneves.newmovieapp.core.presentation.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.felippeneves.newmovieapp.R
import com.felippeneves.newmovieapp.ui.theme.white
import com.felippeneves.newmovieapp.ui.theme.yellow

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    retry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            color = white
        )
        Button(
            onClick = retry,
            colors = ButtonDefaults.buttonColors(containerColor = yellow)
        ) {
            Text(text = stringResource(id = R.string.reload))
        }
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(
        message = "Error",
        retry = {},
    )
}