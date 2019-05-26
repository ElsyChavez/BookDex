package com.echavez.bookdex.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.echavez.bookdex.Adapter.AppConstants
import com.echavez.bookdex.Adapter.BookSimpleListAdapter
import com.echavez.bookdex.Adapter.BooksAdapter
import com.echavez.bookdex.Adapter.MyBookAdapter
import com.echavez.bookdex.R
import com.echavez.bookdex.activities.MainActivity
import com.echavez.bookdex.entities.Book

class MainListFragment: Fragment(){

    private lateinit var  books :ArrayList<Book>
    private lateinit var booksAdapter : MyBookAdapter
    var listenerTool :  SearchNewBookListener? = null

    companion object {
        fun newInstance(dataset : ArrayList<Book>): MainListFragment{
            val newFragment = MainListFragment()
            newFragment.books = dataset
            return newFragment
        }
    }

    interface SearchNewBookListener{
        fun searchBook(bookName: String)

        fun managePortraitItemClick(book: Book)

        fun manageLandscapeItemClick(book: Book)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main_list, container, false)

        if(savedInstanceState != null) books = savedInstanceState.getParcelableArrayList<Book>(AppConstants.MAIN_LIST_KEY)!!

        //initRecyclerView(resources.configuration.orientation, view)
        //initSearchButton(view)

        return view
    }

    /*?
    fun initRecyclerView(orientation:Int, container: View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            moviesAdapter = BookSimpleListAdapter(, { movie:Movie->listenerTool?.manageLandscapeItemClick(movie)})
            container.movie_list_rv.adapter = moviesAdapter as MovieSimpleListAdapter
        }

        container.movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    */


    fun updateMoviesAdapter(movieList: ArrayList<Book>){ booksAdapter.changeDataSet(movieList) }
/*
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SearchNewBookListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz")
        }
    }
    */

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.MAIN_LIST_KEY, books)
        super.onSaveInstanceState(outState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }
}