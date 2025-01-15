package com.ufrn.bookstore.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.ufrn.bookstore.enums.AppRoutes
import com.ufrn.bookstore.model.BookEntity
import com.ufrn.bookstore.viewmodel.AppViewModel

@Composable
fun BookDetailsPage(viewModel: AppViewModel, navController: NavController, bookId: Long) {
    var book by remember { mutableStateOf<BookEntity?>(null) }
    val context = LocalContext.current

    // Carregar o livro do banco de dados
    LaunchedEffect(bookId) {
        viewModel.getBookById(bookId) { fetchedBook ->
            book = fetchedBook
        }
    }

    if (book != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.padding(40.dp))
            }

            item {
                // Imagem da Capa
                AsyncImage(
                    model = book!!.imageUrl,
                    contentDescription = "Capa do Livro",
                    modifier = Modifier
                        .size(160.dp)
                        .padding(8.dp)
                )
            }

            item {
                // Detalhes do Livro
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 700.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "📖 Título: ${book!!.title}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "✍️ Autor: ${book!!.author}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "🏢 Editora: ${book!!.publisher}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "🔢 ISBN: ${book!!.isbn}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            item {
                // Descrição do Livro
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 700.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "📚 Descrição", style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = book!!.description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            item {
                // Botão para editar informações do livro
                Button(
                    onClick = { navController.navigate("edit/${book!!.id}") }, // Redireciona para a página de edição
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Editar Informações do Livro")
                }

                // Botão para deletar o livro
                Button(
                    onClick = {
                        viewModel.removeBook(book!!.id) { success ->
                            if (success) {
                                // Redireciona para a lista de livros após a exclusão
                                navController.popBackStack(AppRoutes.list, inclusive = false)
                                Toast.makeText(context, "Livro deletado com sucesso", Toast.LENGTH_SHORT).show()
                            } else {
                                // Trate o erro caso o livro não seja encontrado
                                Toast.makeText(context, "Erro ao deletar o livro", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Deletar Livro")
                }
            }

        }
    }
}




