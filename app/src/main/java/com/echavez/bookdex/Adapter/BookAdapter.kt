package com.echavez.bookdex.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.bumptech.glide.Glide
//import com.bumptech.glide.Glide
import com.echavez.bookdex.R
import com.echavez.bookdex.ViewModel.BookViewModel
import com.echavez.bookdex.entities.Book
import kotlinx.android.synthetic.main.cardview_libro.view.*

class BooksAdapter internal  constructor(context: Context, val clickListenerBoton: (Book) -> Unit, val clickListenerViewHolder: (Book)->Unit ) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {
    private var books: List<Book> = emptyList()
    public var switch = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_libro, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(books[position], clickListenerBoton,clickListenerViewHolder )

    internal fun setBooks(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: Book, clickListenerBoton: (Book) -> Unit, clickListenerViewHolder:(Book) -> Unit) = with(itemView) {
           Glide.with(itemView.context)
                .load(book.cover)
                .placeholder(R.drawable.ic_launcher_background)
                .into(Iv_book_portada)
            Tv_book_name.text = book.title

            setFavorite.setOnClickListener {
                clickListenerBoton(book)

            }
            this.setOnClickListener(){
                clickListenerViewHolder(book)
            }

        }
    }
}


