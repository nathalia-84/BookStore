package com.ufrn.bookstore.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ufrn.bookstore.model.BookEntity
import com.ufrn.bookstore.viewmodel.AppViewModel
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ufrn.bookstore.view.components.BookList

@Composable
fun BookListPage(
    viewModel: AppViewModel,
    onBookClick: (BookEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    // Lista de livros
    var bookList by remember { mutableStateOf<List<BookEntity>>(emptyList()) }

    // Carregar os livros a partir da ViewModel
    LaunchedEffect(Unit) {
        viewModel.fetchAllBooks { books ->
            bookList = books
        }
    }

    // Exibir a Lista de Livros
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(40.dp))
        Text(
            text = "Lista de Livros",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (bookList.isEmpty()) {
            // Exibe um texto informando que não há livros cadastrados
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {
                Text(
                    text = "Nenhum livro cadastrado.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        } else {
            // Renderiza os livros por meio da BookList
            BookList(
                books = bookList,
                onBookClick = onBookClick
            )
        }
    }
}

