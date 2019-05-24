package com.echavez.bookdex.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "joinedBookTable")
data class joinedBook(

        @PrimaryKey @ColumnInfo(name = "isbn") val isbn: String,
        @ColumnInfo(name = "cover") val cover: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "edition") val edition: String,
        @ColumnInfo(name = "editorial") val editorial: String,
        @ColumnInfo(name = "summary") val summary: String,
        @ColumnInfo(name = "name_author") val name_author: String,
        @ColumnInfo(name = "lastName_author") val lastName_author: String,
        @ColumnInfo(name = "tag") val tag: String,
        @ColumnInfo(name = "favourite") val favourite: Boolean
)