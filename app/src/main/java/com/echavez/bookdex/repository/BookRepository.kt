package com.echavez.bookdex.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.echavez.bookdex.dao.BookDao
import com.echavez.bookdex.entities.Book

class BookRepository(private val bookDao: BookDao) {

    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()

    val favoritesBooks: LiveData<List<Book>> = bookDao.getBooksByFavourite()

    @WorkerThread
    suspend fun insert(book: Book) {
        bookDao.insertBook(book)
    }

    @WorkerThread
    suspend fun marcarODesmarcarFav(book: Book) {
        var flag= !book.favourite
        bookDao.favourite(book.isbn, flag)
    }
}