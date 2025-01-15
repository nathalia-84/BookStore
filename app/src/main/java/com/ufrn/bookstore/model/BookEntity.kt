package com.ufrn.bookstore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val author: String,
    val publisher: String,
    val isbn: String,
    val description: String,
    val imageUrl: String
) {
    // Construtor secundário sem o ID
    constructor(
        title: String,
        author: String,
        publisher: String,
        isbn: String,
        description: String,
        imageUrl: String
    ) : this(
        id = 0,
        title = title,
        author = author,
        publisher = publisher,
        isbn = isbn,
        description = description,
        imageUrl = imageUrl
    )
}
