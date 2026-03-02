package org.example.book.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class OpenLibraryResponse(
    val docs: List<BookDoc>
)

@Serializable
data class BookDoc(
    val key: String,
    val title: String,
    val author_name: List<String>? = null,
    val cover_i: Int? = null
)
