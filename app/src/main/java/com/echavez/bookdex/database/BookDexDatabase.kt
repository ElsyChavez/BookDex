package com.example.library.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.library.dao.AuthorDao
import com.example.library.dao.BookDao
import com.example.library.dao.TagDao
import com.example.library.entities.Author
import com.example.library.entities.Book
import com.example.library.entities.Tag


@Database(entities = arrayOf(Book::class, Author::class, Tag::class), version = 1)
public abstract class BookDexDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun authorDao(): AuthorDao
    abstract fun tagDao(): TagDao

    companion object {
        @Volatile
        private var INSTANCE: BookDexDatabase? = null

        fun getDatabase(context: Context): BookDexDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDexDatabase::class.java,
                    "BookDex_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
