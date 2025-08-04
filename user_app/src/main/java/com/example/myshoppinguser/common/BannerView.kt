package com.example.myshoppinguser.common

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myshoppinguser.presentation.viewmodel.MyViewModel
import com.example.myshoppinguser.ui.theme.customPink
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.BalloonIndicatorType
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType

@Composable
fun BannerViewItems(viewModel: MyViewModel = hiltViewModel()) {
    val bannerState = viewModel.getBannerState.collectAsState().value


    var bannerList by remember {
        mutableStateOf<List<String>>(emptyList())
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getBannerImages()
    }



    Log.d("TAG", "BannerViewItems: ${bannerState.data}")

    Column(modifier = Modifier.fillMaxWidth()) {
        var pageCount by remember { mutableStateOf(5) }
        val pagerState = rememberPagerState(0){
            bannerState.data.size
        }
        HorizontalPager(
            modifier = Modifier.height(140.dp)
                .fillMaxWidth(),
            pageSpacing = 24.dp,
            state = pagerState
        ){
            AsyncImage(
                model = bannerState.data[it],
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp),)

            )
        }

        Spacer(modifier  = Modifier.height(10.dp))
        DotsIndicator(
            dotCount = bannerState.data.size,
            type = ShiftIndicatorType(dotsGraphic = DotGraphic( color = customPink, size = 8.dp)),
            pagerState = pagerState
        )
    }
}

