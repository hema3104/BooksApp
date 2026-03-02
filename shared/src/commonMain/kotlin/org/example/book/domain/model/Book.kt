package org.example.book.domain.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val image: String,
    val description: String,
    var isWishlisted: Boolean = false
)