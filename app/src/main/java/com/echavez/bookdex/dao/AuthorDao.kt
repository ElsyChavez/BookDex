package com.echavez.bookdex.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.echavez.bookdex.entities.Author

@Dao
interface AuthorDao {
    @Query("SELECT * from author_table WHERE id= :id")
    fun getAuthor(id: Int): LiveData<Author>

    @Query("SELECT * from author_table ORDER BY id ASC")
    fun getAllAuthors(): LiveData<List<Author>>

    @Insert
    suspend fun insertAuthor(author: Author)

}