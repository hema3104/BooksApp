package org.example.book.data.repository

import org.example.book.data.remote.BookApi
import org.example.book.domain.model.Book
import org.example.book.domain.repository.BookRepository

class BookRepositoryImpl(
    private val api: BookApi
) : BookRepository {
    override suspend fun getBooks(): List<Book> {

        return try {

            api.getBooks().docs.map { doc ->

                Book(
                    id = doc.key,
                    title = doc.title,
                    author = doc.author_name?.firstOrNull() ?: "Unknown",
                    image =
                        if (doc.cover_i != null)
                            "http://covers.openlibrary.org/b/id/${doc.cover_i}-M.jpg"
                        else
                            "",
                    description = ""
                )
            }

        } catch (e: Exception) {

            e.printStackTrace()

            emptyList()
        }
    }
}