package com.echavez.bookdex.entities

import android.os.Parcel
import android.os.Parcelable
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
        @ColumnInfo(name = "author") val author: Int?,  //Fk tabla Author
        @ColumnInfo(name = "tag") val tag: Int?,  //Trae como fk de la tabla Tag
        @ColumnInfo(name = "favourite") val favourite: Boolean
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(isbn)
        parcel.writeString(cover)
        parcel.writeString(title)
        parcel.writeString(edition)
        parcel.writeString(editorial)
        parcel.writeString(summary)
        parcel.writeValue(author)
        parcel.writeValue(tag)
        parcel.writeByte(if (favourite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}