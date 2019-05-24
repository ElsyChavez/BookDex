package com.echavez.bookdex.ViewModel

import android.app.Application
import android.app.DownloadManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.echavez.bookdex.database.BookDexDatabase
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.entities.Tag
import com.echavez.bookdex.entities.joinedBook
import com.echavez.bookdex.repository.AuthorRepository
import com.echavez.bookdex.repository.BookRepository
import com.echavez.bookdex.repository.TagRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application:Application):AndroidViewModel(application) {
    private val bookRepository: BookRepository
    private val tagRepository: TagRepository
    private val authorRepository: AuthorRepository
    var allBooks: LiveData<List<Book>>
    val allTags: LiveData<List<Tag>>
    var favBooks: LiveData<List<Book>>
    var libroActivity = joinedBook("", "","", "", "", "","", "","",false)

    init {
        val booksDao = BookDexDatabase.getDatabase(application, viewModelScope).bookDao()
        bookRepository = BookRepository(booksDao)
        val tagDao = BookDexDatabase.getDatabase(application, viewModelScope).tagDao()
        tagRepository = TagRepository(tagDao)
        val authorDao = BookDexDatabase.getDatabase(application, viewModelScope).authorDao()
        authorRepository = AuthorRepository(authorDao)

        allBooks = bookRepository.allBooks
        allTags = tagRepository.allTags
        favBooks = bookRepository.favoritesBooks

    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.insert(book)
    }

    fun getBooksbyTag(tag: String): LiveData<List<Book>>{
        return tagRepository.getBooksByTag(tag)
    }
    fun getBooksbyAuthor(author: String) : LiveData<List<Book>>{
        return authorRepository.getBooksByAuthor(author)
    }
    fun marcarODesmarcarFav(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookRepository.marcarODesmarcarFav(book)
    }
    fun getJoinedBook(book: Book): LiveData<joinedBook>{
       return bookRepository.getJoinedBook(book)
    }
}