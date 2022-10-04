package com.azat.noicerealestate.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.azat.domain.entity.Property
import com.azat.noicerealestate.R
import com.azat.noicerealestate.screens.common.LoadingAnimation
import com.azat.noicerealestate.screens.common.property.PropertyHeader
import com.azat.noicerealestate.screens.common.property.PropertyTitle
import com.azat.noicerealestate.ui.theme.UltraLightGrey
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun Home(navController: NavController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    if (homeViewModel.homeViewState.value.isLoading) {
        Loading()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(60.dp)
        ) {
            Column(Modifier.background(UltraLightGrey)) {
                val onPropertyClicked: (Int) -> Unit = { id: Int ->
                    navController.navigate("propertyDetail/${id}")
                }
                Row {
                    TopWelcomeView()
                }
                Row {
                    PropertiesList(homeViewModel.homeViewState.value.properties, onPropertyClicked)
                }
            }
        }
    }
}

@Composable
fun TopWelcomeView() {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(id = R.string.welcome_people),
            style = typography.headlineLarge,
            modifier = Modifier.padding(top = 16.dp, start = 8.dp)
        )
    }
    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
        Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = stringResource(id = R.string.profile),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(12.dp)
                .size(52.dp)
        )
    }
}

@Composable
fun PropertiesList(
    propertiesList: List<Property>,
    onItemSelected: (Int) -> Unit
) {
    val properties = remember { propertiesList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp)
    ) {
        items(
            count = properties.size,
            itemContent = {
                PropertyView(property = properties[it], onItemSelected)
            }
        )
        item {
            Text(text = stringResource(id = R.string.recent_properties),
                style = typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
            RecentProperties(propertiesList, onItemSelected)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PropertyView(property: Property, onItemSelected: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onItemSelected(property.id) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        colors = CardDefaults.cardColors(
                containerColor =  Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            Modifier.padding(bottom = 16.dp)
        ) {
            PropertyHeader(property)
            Spacer(modifier = Modifier.height(8.dp))
            PropertyFeaturedImage(property.featureImage, property.roomDefinition, Modifier.height(200.dp))
            PropertyTitle(property)
        }
    }
}

@Composable
fun PropertyFeaturedImage(link: String, description: String, modifier: Modifier) {
    GlideImage(
        imageModel = link,
        imageOptions = ImageOptions(
            contentDescription = "Property: $description",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
        modifier = modifier
    )
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LoadingAnimation(24.dp, 300)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecentProperties(
    propertiesList: List<Property>,
    onItemSelected: (Int) -> Unit
) {
    Column {
        val properties = remember { propertiesList }
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = propertiesList.size,
            state = pagerState
        ) { currentPage ->
            PropertyView(property = properties[currentPage], onItemSelected)
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        )
    }
}