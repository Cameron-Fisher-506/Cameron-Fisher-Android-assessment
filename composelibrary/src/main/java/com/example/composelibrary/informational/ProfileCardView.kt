package com.example.composelibrary.informational

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileCardView() {
    Surface(Modifier.wrapContentSize()) {
        Card(
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clickable { }
        ) {

        }
    }
}

@Preview
@Composable
fun PreviewProfileCardView() {
    ProfileCardView()
}