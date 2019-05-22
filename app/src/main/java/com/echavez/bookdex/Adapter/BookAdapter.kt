package com.echavez.bookdex.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.echavez.bookdex.R
import com.echavez.bookdex.entities.Book
import kotlinx.android.synthetic.main.cardview_libro.view.*

class BooksAdapter internal  constructor(context: Context) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {
     private var books: List<Book> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_libro, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(books[position])

    internal fun setBooks(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: Book) = with(itemView) {
           // Glide.with(itemView.context)
             //       .load(book.cover)
               //     .into(IV_book_portada)
            Tv_book_name.text = book.title

        }

    }
}


