package com.example.composelibrary.informational

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelibrary.R

@Composable
fun ProfileCardView(
    @DrawableRes drawableResourceId: Int,
    title: String,
    subTitle: String,
    statsMap: Map<String, Int>,
    onClick: () -> Unit
) {
    Surface(Modifier.wrapContentSize()) {
        Card(
            Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clickable { },
            colors = CardDefaults.cardColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Row(
                Modifier
                    .fillMaxWidth(1f)
                    .padding(15.dp),
            ) {
                ProfileImage(drawableResourceId, onClick)
                ProfileDescription(title, subTitle, statsMap)
            }
        }
    }
}

@Composable
fun ProfileImage(@DrawableRes drawableResourceId: Int, onClick: () -> Unit) {
    Image(
        painterResource(drawableResourceId),
        stringResource(R.string.dummy_text),
        Modifier
            .size(110.dp)
            .clickable { onClick.invoke() }
    )
}

@Composable
fun ProfileDescription(
    title: String,
    subTitle: String,
    statesMap: Map<String, Int>
) {
    Column(Modifier.padding(15.dp, 0.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge)
        Text(subTitle, style = MaterialTheme.typography.titleMedium)
        StatesView(statesMap)
    }
}

@Composable
fun StatesView(statsMap: Map<String, Int>) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth(1f)
                .padding(start = 10.dp, top = 5.dp, end = 10.dp),
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ) {
            for((key, _) in statsMap) {
                Text(key, style = MaterialTheme.typography.titleSmall)
            }
        }
        Row(
            Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 5.dp),
            Arrangement.SpaceAround,
            Alignment.CenterVertically
        ) {
            for((_, value) in statsMap) {
                Text(value.toString(), style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileCardView() {
    val statsMap: Map<String, Int> = mapOf(
        "Years" to 4,
        "Coffees" to 3,
        "Bugs" to 9
    )
    ProfileCardView(R.drawable.ic_launcher_background, "Title", "SubTitle", statsMap) {

    }
}