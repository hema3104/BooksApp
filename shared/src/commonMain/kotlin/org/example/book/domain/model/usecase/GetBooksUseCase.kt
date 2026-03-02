package org.example.book.domain.usecase

import org.example.book.domain.repository.BookRepository

class GetBooksUseCase(
    private val repo: BookRepository
) {
    suspend operator fun invoke() = repo.getBooks()
}
