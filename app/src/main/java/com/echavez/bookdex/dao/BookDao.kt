package com.echavez.bookdex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.echavez.bookdex.entities.Author
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.entities.Tag
import com.echavez.bookdex.entities.joinedBook

@Dao
interface BookDao {

    //GET
    @Query("SELECT * from book_table WHERE isbn= :isbn")
    fun getBookByIsbn(isbn: String): LiveData<Book>

    @Query("SELECT * from book_table WHERE title= :title")
    fun getBookByTitle(title: String): LiveData<Book>

    @Query("SELECT * from book_table WHERE editorial= :editorial")
    fun getBookByEditorial(editorial: String): LiveData<List<Book>>

    //@Query("SELECT * from book_table WHERE authors= :authors")
    //fun getBookByAuthors(authors: List<Author>): List<LiveData<Book>>


    @Query("SELECT * from book_table WHERE favourite = :fav")
    fun getBooksByFavourite(fav:Boolean): LiveData<List<Book>>


    //Get all
    @Query("SELECT * from book_table ORDER BY title ASC")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * from book_table WHERE isbn=0")
    fun initList(): LiveData<List<Book>>


    //Insert
    @Insert
    fun insertBook(book: Book)

    //Check or unchek as favourite
    @Query("UPDATE book_table SET favourite = :preference WHERE isbn = :isbn")
    fun favourite(isbn: String, preference: Boolean)

    @Query("DELETE FROM book_table")
    suspend fun deleteAll()

    @Query("SELECT bt.isbn, bt.cover, bt.title, bt.edition, bt.editorial, bt.summary, at.name as name_author , at.last_name as lastName_author, tt.tag as tag ,bt.favourite FROM book_table bt JOIN author_table at ON bt.author=at.id JOIN tag_table tt ON bt.tag=tt.id WHERE bt.isbn = :isbn")
    fun getJoinedBook(isbn: String): LiveData<joinedBook>
}