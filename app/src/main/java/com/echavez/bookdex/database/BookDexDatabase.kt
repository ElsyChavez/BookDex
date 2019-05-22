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

        fun getDatabase(context: Context, scope: CoroutineScope): BookDexDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDexDatabase::class.java,
                        "BookDex_database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(BookDexDatabaseCallback(scope))
                        .build()
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
                        populateDatabase(database.bookDao(), database.tagDao(), database.authorDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(bookDao: BookDao , tagDao: TagDao, authorDao: AuthorDao) {
            bookDao.deleteAll()
            tagDao.deleteAll()

            var tag = Tag( null,"Ficcion" )
            tagDao.insertTag(tag)
            tag= Tag(0, "Politica" )
            tagDao.insertTag(tag)

            var book = Book("4213432324", "https://www.abc.es/media/familia/2018/04/06/PORTADA-ELPRINCIPITO-kwpB--620x349@abc.JPG", "El principito", "1ra", "El castillo", "Cuenta la hisotira de un pequeño niño que..", 1, true)
            bookDao.insertBook(book)
            book = Book("2131244523", "http://4.bp.blogspot.com/-YYFDLFIvMGg/VYxRCfAibDI/AAAAAAAAAbc/oOVeVofRQBs/s1600/1984.jpg", "1984", "1ra", "El castillo", "En un futuro cercano distopico donde la tecnologia..", 2, false)
            bookDao.insertBook(book)
            book = Book("9235256788", "https://imagessl2.casadellibro.com/a/l/t5/72/9788498890372.jpg", "El último deseo", "1ra", "Casa de timbre", "breves que preceden la serie principal de Geralt de Rivia", 2, true)
            bookDao.insertBook(book)
            book = Book("6548523432", "https://imagessl3.casadellibro.com/a/l/t5/33/9788498890433.jpg", "Espada del destino", "1ra", "Casa de timbre", "breves que preceden la serie principal de Geralt de Rivia", 1, false)
            bookDao.insertBook(book)


        }
    }
}
