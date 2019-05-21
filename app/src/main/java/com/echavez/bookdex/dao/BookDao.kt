package com.echavez.bookdex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.echavez.bookdex.entities.Book

@Dao
interface BookDao {
    @Query("SELECT * from book_table WHERE isbn= :isbn")
    fun getBook(isbn: String): LiveData<Book>

    /*
    @Query("SELECT * from book_table WHERE isbn= :isbn")
    fun getBookByISBN(isbn: String): Book
    */

    @Query("SELECT * from book_table ORDER BY isbn ASC")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert
    suspend fun insertBook(book: Book)

    @Query("UPDATE book_table SET favourite = :preference WHERE isbn = :isbn")
    fun favourite(isbn: String, preference: Boolean)


}