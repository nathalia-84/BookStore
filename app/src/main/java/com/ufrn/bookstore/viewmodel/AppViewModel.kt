package com.ufrn.bookstore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufrn.bookstore.db.BookDao
import com.ufrn.bookstore.model.BookEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(private val bookDao: BookDao) : ViewModel() {

    fun addBook(book: BookEntity, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                bookDao.insertBook(book)
                withContext(Dispatchers.Main) {
                    onResult(true) // Livro adicionado com sucesso
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(false) // Operação falhou
                }
            }
        }
    }

    fun fetchAllBooks(onResult: (List<BookEntity>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val books = bookDao.getAllBooks()
            withContext(Dispatchers.Main) {
                onResult(books)
            }
        }
    }

    fun getBookById(bookId: Long, onResult: (BookEntity?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val book = bookDao.getBookById(bookId)
            withContext(Dispatchers.Main) {
                onResult(book)
            }
        }
    }

    fun removeBook(bookId: Long, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val book = bookDao.getBookById(bookId)
            if (book != null) {
                bookDao.deleteBook(book)
                withContext(Dispatchers.Main) {
                    onResult(true) // Livro deletado com sucesso
                }
            } else {
                withContext(Dispatchers.Main) {
                    onResult(false) // Livro não encontrado
                }
            }
        }
    }

    fun updateBook(book: BookEntity, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingBook = bookDao.getBookById(book.id)
            if (existingBook != null) {
                bookDao.updateBook(book)
                withContext(Dispatchers.Main) {
                    onResult(true) // Livro atualizado com sucesso
                }
            } else {
                withContext(Dispatchers.Main) {
                    onResult(false) // Livro não encontrado
                }
            }
        }
    }

}