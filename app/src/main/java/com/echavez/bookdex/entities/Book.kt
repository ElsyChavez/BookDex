package com.echavez.bookdex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "book_table",
        foreignKeys = [
            ForeignKey(entity = Author::class, parentColumns = ["id"], childColumns = ["author"]),
            ForeignKey(entity = Tag::class, parentColumns = ["id"], childColumns = ["tag"])
        ])
data class Book(
        @PrimaryKey @ColumnInfo(name = "isbn") val isbn: String,
        @ColumnInfo(name = "cover") val cover: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "edition") val edition: String,
        @ColumnInfo(name = "editorial") val editorial: String,
        @ColumnInfo(name = "summary") val summary: String,
        @ColumnInfo(name = "author") val author: Int,
        @ColumnInfo(name = "tag") val tag: Int,  //Trae como fk de la tabla Tag
        @ColumnInfo(name = "favourite") val favourite: Boolean
)