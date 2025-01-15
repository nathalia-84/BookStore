package com.ufrn.bookstore.db

import androidx.room.*
import com.ufrn.bookstore.model.BookEntity

@Dao
interface BookDao {

    // Insert a new book into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    // Update an existing book
    @Update
    suspend fun updateBook(book: BookEntity)

    // Delete a specific book
    @Delete
    suspend fun deleteBook(book: BookEntity)

    // Get a book by its ID
    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBookById(id: Long): BookEntity?

    // Get all books
    @Query("SELECT * FROM books ORDER BY title ASC")
    suspend fun getAllBooks(): List<BookEntity>

    // Delete all books
    @Query("DELETE FROM books")
    suspend fun deleteAllBooks()
}