package com.frantic.catfactsapp.data.net.retrofit

data class CatFact(
    val used: Boolean,
    val source: String,
    val type: String,
    val deleted: Boolean,
    val _id: String,
    val __v: Int,
    val text: String,
    val updatedAt: String,
    val createdAt: String
)