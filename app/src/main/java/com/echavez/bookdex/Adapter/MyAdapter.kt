package com.echavez.bookdex.Adapter

import com.echavez.bookdex.entities.Book

object AppConstants{
    val dataset_saveinstance_key = "CLE"
    val MAIN_LIST_KEY = "key_list_books"
}

interface MyBookAdapter {
    fun changeDataSet(newDataSet : List<Book>)
}