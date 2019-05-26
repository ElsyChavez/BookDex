package com.echavez.bookdex.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.echavez.bookdex.R
import com.echavez.bookdex.entities.Book
import kotlinx.android.synthetic.main.cardview_libro.view.*
import kotlinx.android.synthetic.main.list_item_book.view.*

class BookSimpleListAdapter internal  constructor(context: Context, val clickListenerBoton: (Book) -> Unit, val clickListenerViewHolder: (Book)->Unit ) : RecyclerView.Adapter<BookSimpleListAdapter.ViewHolder>() {

    private var books: List<Book> = emptyList()
    public var switch = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false)
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
            title_list_item.text = book.title
            autor_list_item.text = book.author.toString()
            editorial_list_item.text = book.editorial

            setFavorite.setOnClickListener {
                clickListenerBoton(book)

            }
            this.setOnClickListener(){
                clickListenerViewHolder(book)
            }

        }
    }
}