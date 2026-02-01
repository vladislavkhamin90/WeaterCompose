package com.example.weatercompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.weatercompose.data.model.Model
import com.example.weatercompose.ui.theme.CardBg50


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListItem(item: Model) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .background(CardBg50),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                    .weight(1f)
            ) {
                Text(text = item.lastTime, color = Color.Black)
                Text(text = item.condition, color = Color.Black)
            }
            Text(
                modifier = Modifier.padding(end = 100.dp),
                text = item.currentTemp.ifEmpty { "${item.maxTemp}/${item.minTemp}" },
                fontSize = 24.sp,
            )
            GlideImage(
                model = "https:${item.icon}",
                contentDescription = "icon",
                modifier = Modifier
                    .requiredSize(35.dp),
                alignment = Alignment.CenterEnd,
            )
        }
    }
}
