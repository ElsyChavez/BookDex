package com.echavez.bookdex.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.entities.Tag

@Dao
interface TagDao {
    @Query("SELECT bt.* from book_table bt LEFT JOIN tag_table tt ON bt.tag=tt.id WHERE tt.tag= :tag ORDER BY tt.tag ASC")
    fun getBooksbyTag(tag: String): LiveData<List<Book>>

    @Query("SELECT * from tag_table ORDER BY tag ASC")
    fun getAllTags(): LiveData<List<Tag>>

    @Insert
    suspend fun insertTag(tag: Tag)

    @Query("DELETE FROM tag_table")
    fun deleteAll()
}