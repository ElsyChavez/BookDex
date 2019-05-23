package com.echavez.bookdex.repository
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.echavez.bookdex.dao.TagDao
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.entities.Tag

class TagRepository(private val tagDao: TagDao) {
    val allTags: LiveData<List<Tag>> = tagDao.getAllTags()

    @WorkerThread
    suspend fun insert(tag: Tag){
        tagDao.insertTag(tag)
    }
    @WorkerThread
    fun getBooksByTag(tag: String): LiveData<List<Book>>{
        return tagDao.getBooksbyTag(tag)
    }

}