package com.echavez.bookdex.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.echavez.bookdex.R
import com.echavez.bookdex.entities.Book
import kotlinx.android.synthetic.main.activity_book_viewer.*

//vista interna, detalle del libro proximamente
class BookViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_viewer)
    }

    fun init(book: Book){
        Glide.with(this)
                .load(book.cover)
                .placeholder(R.drawable.ic_launcher_background)
                .into(Iv_book_cover)
        tv_book_namae.text = book.title
        tv_author_name.text = book.author.toString()
        tv_book_edicion.text = book.edition
        tv_book_editorial.text = book.editorial
        tv_book_isbn.text = book.isbn
        tv_book_resumen.text = book.summary
    }
}
