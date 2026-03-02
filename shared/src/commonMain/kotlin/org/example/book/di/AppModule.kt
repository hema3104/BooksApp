package org.example.book.di

import org.example.book.data.remote.BookApi
import org.example.book.data.repository.BookRepositoryImpl
import org.example.book.domain.repository.BookRepository
import org.example.book.domain.usecase.GetBooksUseCase
import org.example.book.domain.usecase.ToggleWishlistUseCase
import org.example.book.presentation.BookViewModel
import org.koin.dsl.module

val appModule = module {

    // API
    single { BookApi() }

    // Repository
    single<BookRepository> {
        BookRepositoryImpl(get())
    }

    // UseCases
    single { GetBooksUseCase(get()) }
    single { ToggleWishlistUseCase() }

    // ViewModel
    single {
        BookViewModel(get(), get())
    }
}

