package com.azat.noicerealestate.screens.propertydetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.azat.noicerealestate.screens.common.MyApplicationTopBar
import com.azat.noicerealestate.screens.common.property.PropertyTitle
import com.azat.noicerealestate.screens.home.PropertyFeaturedImage

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
                    PropertyFeaturedImage(property,Modifier
                        .height(250.dp))
                    PropertyTitle(property)
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