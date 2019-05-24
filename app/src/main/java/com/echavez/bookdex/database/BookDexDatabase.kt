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


@Database(entities = arrayOf(Book::class, Author::class, Tag::class), version = 7)
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
            authorDao.deleteAll()

            var aut= Author(1, "Gabriel", "Garcia Marquez")
            authorDao.insertAuthor(aut)
            aut= Author(2, "Fidor", "Dotstovieski")
            authorDao.insertAuthor(aut)
            aut= Author(3, "Andrzej ", "Sapkowski")
            authorDao.insertAuthor(aut)
            aut = Author(4, "Dante", "Alighieri")
            authorDao.insertAuthor(aut)
            aut = Author(5,"Homero","")
            authorDao.insertAuthor(aut)
            aut = Author(6,"Dan","Brown")
            authorDao.insertAuthor(aut)
            aut = Author(7,"Robert","Kiyosaki")
            authorDao.insertAuthor(aut)
            aut = Author(8,"Sun","Tzu")
            authorDao.insertAuthor(aut)
            aut = Author(9,"Jordan","Belfort")
            authorDao.insertAuthor(aut)

            var tag = Tag( 1,"Ficcion" )
            tagDao.insertTag(tag)
            tag= Tag(2, "Politica" )
            tagDao.insertTag(tag)
            tag= Tag(3,"Epopeya")
            tagDao.insertTag(tag)
            tag = Tag(4,"Novela")
            tagDao.insertTag(tag)
            tag = Tag(5,"Economia")
            tagDao.insertTag(tag)
            tag = Tag(6,"Ficcción")
            tagDao.insertTag(tag)


            var book = Book("4213432324", "https://www.abc.es/media/familia/2018/04/06/PORTADA-ELPRINCIPITO-kwpB--620x349@abc.JPG", "El principito", "1ra", "El castillo", "Cuenta la hisotira de un pequeño niño que..", 1,1, true)
            bookDao.insertBook(book)
            book = Book("2131244523", "http://4.bp.blogspot.com/-YYFDLFIvMGg/VYxRCfAibDI/AAAAAAAAAbc/oOVeVofRQBs/s1600/1984.jpg", "1984", "1ra", "El castillo", "En un futuro cercano distopico donde la tecnologia..", 1,2, false)
            bookDao.insertBook(book)
            book = Book("9235256788", "https://imagessl2.casadellibro.com/a/l/t5/72/9788498890372.jpg", "El último deseo", "1ra", "Casa de timbre", "breves que preceden la serie principal de Geralt de Rivia", 3,2, true)
            bookDao.insertBook(book)
            book = Book("6548523432", "https://imagessl3.casadellibro.com/a/l/t5/33/9788498890433.jpg", "Espada del destino", "1ra", "Casa de timbre", "breves que preceden la serie principal de Geralt de Rivia", 3,1, false)
            bookDao.insertBook(book)
            book = Book("9789500398152","https://images-na.ssl-images-amazon.com/images/I/510SxGcsw-L._SX331_BO1,204,203,200_.jpg", "La Divina Comedia","1ra","LOSADA S.A., EDITORIAL","La travesia de Dante por los 9 circulos del infierno",4,3,true)
            bookDao.insertBook(book)
            book = Book("1502735180","https://i.ebayimg.com/images/g/jVYAAOSw4A5YqQGU/s-l500.jpg","La Iliada","1ra","La Biblioteca digital","Es una epopeya griega, atribuida tradicionalmente a Homero",5,3,false)
            bookDao.insertBook(book)
            book = Book("9788071459316","https://imagessl8.casadellibro.com/a/l/t5/08/9788408176008.jpg","Angeles y Demonios","1ra","Planeta","El mayor enemigo de la iglesia amenaza con destruirla desde sus cimientos",6,4,false)
            bookDao.insertBook(book)
            book = Book("9788466332125","https://imagessl5.casadellibro.com/a/l/t5/25/9788466332125.jpg","Padre rico padre pobre","1ra","Debolsillo (Punto de lectura)","Libro de finanzas personales número 1 en todo el mundo",7,5,true)
            bookDao.insertBook(book)
            book = Book("9781590302255","https://images-na.ssl-images-amazon.com/images/I/51YhPgNl8UL._SX302_BO1,204,203,200_.jpg","El arte de la guerra","1ra","Shambhala","El conflicto es parte de la vida que no se puede evitar",8,6,false)
            bookDao.insertBook(book)
            book = Book("0340953756","https://pictures.abebooks.com/isbn/9780340953754-es.jpg","El lobo de Wallstreet","1ra","Hodder Paperback","Es un libro de memorias del excorredor de bolsa estadounidense Jordan Belfort",9,5,true)
            bookDao.insertBook(book)
        }
    }
}
