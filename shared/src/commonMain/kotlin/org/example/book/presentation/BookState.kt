package org.example.book.presentation

import org.example.book.domain.model.Book

data class BookState(
    val books: List<Book> = emptyList(),
    val wishlist: List<Book> = emptyList(),
    val search: String = ""
)

