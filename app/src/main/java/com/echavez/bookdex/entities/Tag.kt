package com.echavez.bookdex.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_table")
data class Tag(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int?= 0,

        @ColumnInfo(name = "tag")
        var tag: String
)