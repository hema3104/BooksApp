package org.example.book.domain.usecase

import org.example.book.domain.model.Book

class ToggleWishlistUseCase {

    operator fun invoke(book: Book) {
        book.isWishlisted = !book.isWishlisted
    }
}