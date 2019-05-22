package com.echavez.bookdex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey @ColumnInfo(name = "isbn") val isbn: String,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "edition") val edition: String,
    @ColumnInfo(name = "editorial") val editorial: String,
    @ColumnInfo(name = "summary") val summary: String,
   //@ColumnInfo(name = "authors") val authors: List<Author>,
    @ColumnInfo(name = "tag") val tag: Long,  //Trae como fk de la tabla Tag
    @ColumnInfo(name = "favourite") val favourite: Boolean
)