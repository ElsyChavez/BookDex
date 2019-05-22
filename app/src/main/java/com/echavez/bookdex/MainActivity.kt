package com.echavez.bookdex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.echavez.bookdex.Adapter.BooksAdapter
import com.echavez.bookdex.ViewModel.BookViewModel
import com.echavez.bookdex.entities.Book
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bookAdapter = BooksAdapter(this)
        rvLibritos.adapter=bookAdapter
        rvLibritos.layoutManager= LinearLayoutManager(this)

        bookViewModel= ViewModelProviders.of(this).get(BookViewModel::class.java)

        bookViewModel.allBooks.observe(this, Observer { books->
            bookAdapter.setBooks(books)
        })



        //Log.v("tags", bookViewModel.allTags.toString())
        //bookViewModel.librosFavs()
        //Log.v("favs", bookViewModel.allBooks.toString())

       // btPrueba.setOnClickListener(){
         //   bookViewModel.getBooksbyTag(etPruebis.text.toString())
           // Log.v("libsPorTag", bookViewModel.booksByTag.toString())
       // }
    }
}
