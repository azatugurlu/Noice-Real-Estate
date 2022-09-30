package com.azat.noicerealestate.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
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
import com.azat.noicerealestate.screens.common.property.PropertyTitle
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
            Column {
                Row {
                    TopWelcomeView()
                }
                Row {
                    val onPropertyClicked: (Int) -> Unit = { id: Int ->
                        navController.navigate("propertyDetail/${id}")
                    }
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
            tint = Color.Blue,
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
    }
}

@Composable
fun PropertyView(property: Property, onItemSelected: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onItemSelected(property.id) }
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Column(
            Modifier.padding(bottom = 16.dp)
        ) {
            PropertyFeaturedImage(property, Modifier.height(200.dp))
            PropertyTitle(property)
        }
    }
}

@Composable
fun PropertyFeaturedImage(property: Property, modifier: Modifier) {
    GlideImage(
        imageModel = property.featureImage,
        imageOptions = ImageOptions(
            contentDescription = "Property: ${property.roomDefinition}",
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