package org.example.book.data.remote

import io.ktor.client.call.*
import io.ktor.client.request.*
import org.example.book.data.remote.dto.OpenLibraryResponse

class BookApi {

    private val client = KtorClient.client

    suspend fun getBooks(): OpenLibraryResponse {

        return client.get(
            "http://openlibrary.org/search.json?q=fiction&limit=20"
        ).body()
    }
}