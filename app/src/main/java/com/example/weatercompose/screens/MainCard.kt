package com.example.weatercompose.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.weatercompose.R
import com.example.weatercompose.data.Model
import com.example.weatercompose.ui.theme.CardBg50
import kotlinx.coroutines.launch

@ExperimentalGlideComposeApi
@Composable
fun MainCard(model: Model?) {

    Column(
        modifier = Modifier
            .padding(5.dp, top = 50.dp, bottom = 5.dp)
            .alpha(0.5f)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBg50),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            if (model == null) {
                Log.d("MainCard", " null")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Loading...",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.Black
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 15.dp, start = 15.dp),
                            text = model.lastTime,
                            style = TextStyle(fontSize = 15.sp),
                            color = Color.Black
                        )
                        GlideImage(
                            model = "http://${model.icon}",
                            contentDescription = "icon",
                            modifier = Modifier
                                .requiredSize(50.dp)
                                .padding(top = 15.dp, end = 15.dp),
                            alignment = Alignment.Center,
                        )
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(),
                            text = model.city,
                            style = TextStyle(fontSize = 24.sp),
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(),
                            text = model.currentTemp,
                            style = TextStyle(fontSize = 32.sp),
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier.padding(),
                            text = model.condition,
                            style = TextStyle(fontSize = 16.sp),
                            color = Color.Black
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = {

                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.search),
                                    contentDescription = "img",
                                    modifier = Modifier
                                        .alpha(0.5f)
                                        .size(24.dp)
                                )
                            }

                            Text(
                                modifier = Modifier.padding(),
                                text = "${model.minTemp}/${model.maxTemp}",
                                style = TextStyle(fontSize = 16.sp),
                                color = Color.Black
                            )

                            IconButton(onClick = {

                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.reload),
                                    contentDescription = "img",
                                    modifier = Modifier
                                        .alpha(0.3f)
                                        .size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TabLayout(hour: List<Model>?, days: List<Model>?) {

    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState(pageCount = { tabList.size })
    val tabIndex = pagerState.currentPage
    val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .background(CardBg50)
            .alpha(0.5f)
            .clip(RoundedCornerShape(10.dp))
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex])
                )
            },
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    coroutine.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = {
                    Text(
                        text = text, color = Color.Black
                    )
                })
            }
        }
        HorizontalPager(
            pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> {
                    LazyColumn(
                    ) {
                        itemsIndexed(hour ?: emptyList()) { index, item ->
                            Log.d("TabLayout", "Hour item $index: ${item.lastTime}")
                            ListItem(item)
                        }
                    }
                }

                1 -> {
                    LazyColumn(
                    ) {
                        itemsIndexed(days ?: emptyList()) { index, item ->
                            Log.d("TabLayout", "Day item $index: ${item.lastTime}")
                            ListItem(item)
                        }
                    }
                }
            }
        }
    }
}