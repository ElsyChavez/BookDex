package com.echavez.bookdex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.echavez.bookdex.dao.AuthorDao
import com.echavez.bookdex.dao.BookDao
import com.echavez.bookdex.dao.TagDao
import com.echavez.bookdex.entities.Author
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.entities.Tag


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
