package com.echavez.bookdex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "author_table")
data class Author(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int?=0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "last_name") val last_name: String
)