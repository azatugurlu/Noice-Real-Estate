package com.azat.domain.entity

data class Property(
    val id: Int,
    val location: String,
    val size: String,
    val roomSize: String,
    val roomDefinition: String,
    val featureImage: String,
    val images: List<String>,
)
