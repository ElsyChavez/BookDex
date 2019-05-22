package com.echavez.bookdex.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.echavez.bookdex.database.BookDexDatabase
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.entities.Tag
import com.echavez.bookdex.repository.BookRepository
import com.echavez.bookdex.repository.TagRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application:Application):AndroidViewModel(application) {
    private val bookRepository: BookRepository
    private val tagRepository: TagRepository
    var allBooks: LiveData<List<Book>>
    val allTags: LiveData<List<Tag>>
    lateinit var booksByTag: LiveData<List<Book>>

    init {
        val booksDao = BookDexDatabase.getDatabase(application, viewModelScope).bookDao()
        bookRepository = BookRepository(booksDao)
        val tagDao = BookDexDatabase.getDatabase(application, viewModelScope).tagDao()
        tagRepository = TagRepository(tagDao)
        allBooks = bookRepository.allBooks
        allTags = tagRepository.allTags
    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insert(book)
    }

    fun getBooksbyTag(tag: String) = viewModelScope.launch(Dispatchers.IO) {
        booksByTag = tagRepository.getBooksByTag(tag)
    }

    fun librosFavs() = viewModelScope.launch(Dispatchers.IO) {
        allBooks = bookRepository.favoritesBooks
    }
}