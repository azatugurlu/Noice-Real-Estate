package com.azat.noicerealestate.screens.propertydetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.azat.noicerealestate.screens.common.MyApplicationTopBar
import com.azat.noicerealestate.screens.common.property.PropertyHeader
import com.azat.noicerealestate.screens.common.property.PropertyTitle
import com.azat.noicerealestate.screens.home.PropertyFeaturedImage
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PropertyDetail(navController: NavController, propertyId: String?) {
    val propertyDetailViewModel = hiltViewModel<PropertyDetailViewModel>()
    val property = propertyDetailViewModel.propertyDetailViewState.value.property
    Column {
        Row {
            MyApplicationTopBar(
                title = property?.location,
                includeBackButton = (navController.previousBackStackEntry != null)) {
                navController.navigateUp()
            }
            Spacer(modifier = Modifier.padding(top = 40.dp))
        }
        Row(modifier = Modifier.verticalScroll(rememberScrollState())) {
            if (property != null) {
                Column {
                    val pagerState = rememberPagerState()
                    HorizontalPager(
                        count = property.images.size,
                        state = pagerState) { currentPage ->

                        PropertyFeaturedImage(property.images[currentPage],
                            property.roomDefinition,
                            Modifier.height(250.dp))
                    }
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp),
                    )
                    PropertyTitle(property)
                    Spacer(modifier = Modifier.height(8.dp))
                    PropertyHeader(property)
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()) {
                        Text(text = property.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }


                }
            }
        }



    }
}