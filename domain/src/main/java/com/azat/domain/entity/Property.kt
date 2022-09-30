package com.azat.domain.entity

data class Property(
    val id: Int,
    val price: String,
    val type: String,
    val buildingYear: String,
    val location: String,
    val size: String,
    val roomSize: String,
    val roomDefinition: String,
    val description: String,
    val featureImage: String,
    val images: List<String>,
)
