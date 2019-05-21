package com.echavez.bookdex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.echavez.bookdex.dao.AuthorDao
import com.echavez.bookdex.dao.BookDao
import com.echavez.bookdex.dao.TagDao
import com.echavez.bookdex.entities.Author
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.entities.Tag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

        private class BookDexDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.bookDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(bookDao: BookDao) {
            bookDao.deleteAll()
            var book = Book("4213432324", "https://www.abc.es/media/familia/2018/04/06/PORTADA-ELPRINCIPITO-kwpB--620x349@abc.JPG", "El principito", "1ra", "El castillo", "Cuenta la hisotira de un pequeño niño que..", true)
            bookDao.insertBook(book)
        }
    }
}
