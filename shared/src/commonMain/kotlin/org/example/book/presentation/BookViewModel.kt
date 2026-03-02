package org.example.book.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.book.domain.model.Book
import org.example.book.domain.usecase.GetBooksUseCase
import org.example.book.domain.usecase.ToggleWishlistUseCase

class BookViewModel(
    private val getBooks: GetBooksUseCase,
    private val toggleWishlist: ToggleWishlistUseCase
) {

    private val scope = CoroutineScope(
        Dispatchers.Default + SupervisorJob()
    )

    private val _state = MutableStateFlow(BookState())
    val state: StateFlow<BookState> = _state

    private var allBooks: List<Book> = emptyList()
    private var isLoaded = false

    // LOAD BOOKS
    fun loadBooks() {

        if (isLoaded) return

        scope.launch {

            try {

                val books = getBooks()

                allBooks = books

                _state.value = _state.value.copy(
                    books = books
                )

                isLoaded = true

            } catch (e: Exception) {

                e.printStackTrace()

                _state.value = _state.value.copy(
                    books = emptyList()
                )
            }
        }
    }

    // SEARCH TEXT ONLY (NO FILTER)
    fun updateSearch(query: String) {

        val filteredBooks = if (query.isBlank()) {

            // Show full list again
            allBooks

        } else {

            allBooks.filter { book ->

                book.title.contains(query, ignoreCase = true) ||
                        book.author.contains(query, ignoreCase = true)
            }
        }

        _state.value = _state.value.copy(
            search = query,
            books = filteredBooks
        )
    }


    // WISHLIST
    fun toggle(book: Book) {

        val updatedBooks = allBooks.map {

            if (it.id == book.id) {
                it.copy(isWishlisted = !it.isWishlisted)
            } else it
        }

        allBooks = updatedBooks

        val wish = updatedBooks.filter {
            it.isWishlisted
        }

        _state.value = _state.value.copy(
            books = updatedBooks,
            wishlist = wish
        )
    }
}