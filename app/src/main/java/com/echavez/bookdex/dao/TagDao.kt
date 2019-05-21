package com.echavez.bookdex.dao
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.echavez.bookdex.entities.Tag

@Dao
interface TagDao {
    @Query("SELECT * from tag_table WHERE tag= :tag")
    fun getBook(tag: String): LiveData<Tag>

    @Query("SELECT * from tag_table ORDER BY tag ASC")
    fun getAllTags(): LiveData<List<Tag>>

    @Insert
    suspend fun insertTag(tag: Tag)


}