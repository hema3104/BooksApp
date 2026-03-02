package org.example.book.domain.repository

import org.example.book.domain.model.Book

interface BookRepository {
    suspend fun getBooks(): List<Book>
}