package com.echavez.bookdex.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.echavez.bookdex.dao.BookDao
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.entities.joinedBook

class BookRepository(private val bookDao: BookDao) {

    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()
    val favoritesBooks: LiveData<List<Book>> = bookDao.getBooksByFavourite(true)
    val initList: LiveData<List<Book>> = bookDao.initList()

    @WorkerThread
     fun insert(book: Book) {
        bookDao.insertBook(book)
    }

    @WorkerThread
    fun marcarODesmarcarFav(book: Book) {
        var flag= !book.favourite
        bookDao.favourite(book.isbn, flag)
    }

    @WorkerThread
    fun getJoinedBook(book: Book): LiveData<joinedBook>{
        return bookDao.getJoinedBook(book.isbn)
    }

}