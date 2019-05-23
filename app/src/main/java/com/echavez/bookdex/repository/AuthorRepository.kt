package com.echavez.bookdex.repository
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.echavez.bookdex.dao.AuthorDao
import com.echavez.bookdex.dao.AuthorDao_Impl
import com.echavez.bookdex.entities.Author

import com.echavez.bookdex.entities.Book

class AuthorRepository(private val authorDao: AuthorDao) {


    @WorkerThread
    suspend fun insert(author: Author){
        authorDao.insertAuthor(author)
    }
    @WorkerThread
    fun getBooksByAuthor(author: String): LiveData<List<Book>>{
        return authorDao.getBooksByAuthor(author)
    }
}