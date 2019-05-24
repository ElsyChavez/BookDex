package com.echavez.bookdex.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.echavez.bookdex.Adapter.BooksAdapter
import com.echavez.bookdex.R
import com.echavez.bookdex.ViewModel.BookViewModel
import com.echavez.bookdex.entities.Book
import com.echavez.bookdex.entities.joinedBook
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var bookViewModel: BookViewModel
    private var flag = true
    private var switch = true
    private lateinit var bookAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bookAdapter = BooksAdapter(this, { book: Book -> bookFavorito(book) }, { book: Book -> triggerActivity(book) })


        //var bookAdapter = BooksAdapter(this) { book:Book->bookOnClicked(book)}
        rvLibritos.adapter = bookAdapter
        rvLibritos.layoutManager = LinearLayoutManager(this)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)



        bookViewModel.allBooks.observe(this, Observer { books ->
            bookAdapter.setBooks(books)
        })
        initClicksListeners()
    }

    private fun initClicksListeners(){
        btFavoritos.setOnClickListener(){
            bookViewModel.favBooks.observe(this, Observer { favBooks ->
                bookAdapter.setBooks(favBooks)

            })
        }
        btAllBooks.setOnClickListener(){
            bookViewModel.allBooks.observe(this, Observer { books ->
                bookAdapter.setBooks(books)
            })
        }
        BtBuscar.setOnClickListener() {
            var flag = 0
            bookViewModel.getBooksbyTag(ETbuscar.text.toString()).observe(this, Observer { books: List<Book> ->
                if (books.isEmpty()) {
                    flag = 1
                }
                bookAdapter.setBooks(books)
            })
            if (flag == 1) {

                bookViewModel.getBooksbyAuthor(ETbuscar.text.toString()).observe(this, Observer { books: List<Book> ->
                    bookAdapter.setBooks(books)
                })

            }
        }
    }

    private fun triggerActivity(book: Book) {
        val libroBundle = Bundle()
        libroBundle.putParcelable("LIBRO", book)
        startActivity(Intent(this, BookViewerActivity::class.java).putExtras(libroBundle))

    }

    private fun bookFavorito(book: Book) {
        bookViewModel.favBooks.observe(this, Observer { favBooks ->
            bookAdapter.setBooks(favBooks)

        })
        bookViewModel.marcarODesmarcarFav(book)
    }

}
