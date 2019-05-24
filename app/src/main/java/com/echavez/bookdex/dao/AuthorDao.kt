package com.echavez.bookdex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.echavez.bookdex.entities.Author
import com.echavez.bookdex.entities.Book

@Dao
interface AuthorDao {
    @Query("SELECT bt.* FROM book_table bt LEFT JOIN author_table at ON bt.author=at.id WHERE at.name=:author OR at.last_name=:author ORDER BY bt.title ASC")
    fun getBooksByAuthor(author: String): LiveData<List<Book>>

    @Insert
    suspend fun insertAuthor(author: Author)

    @Query("DELETE FROM author_table")
    suspend fun deleteAll()
}