package com.echavez.bookdex.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.echavez.bookdex.database.BookDexDatabase
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application:Application):AndroidViewModel(application) {
    private val repository:BookRepository
    val allBooks: LiveData<List<Book>>


    init {
        val booksDao = BookDexDatabase.getDatabase(application).bookDao()
        repository = BookRepository(booksDao)
        allBooks = repository.allBooks
    }

    fun insert(book:Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(book)
    }
}