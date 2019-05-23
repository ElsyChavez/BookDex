package com.echavez.bookdex.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.echavez.bookdex.R

//vista interna, detalle del libro proximamente
class BookViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_viewer)
    }
}
