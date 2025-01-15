package com.ufrn.bookstore.view

import androidx.compose.runtime.Composable
import com.ufrn.bookstore.viewmodel.AppViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufrn.bookstore.model.BookEntity

@Composable
fun EditBookPage(viewModel: AppViewModel, navController: NavController, bookId: Long) {
    val context = LocalContext.current


    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }


    // Recupera o livro com id bookID do banco de dados
    LaunchedEffect(bookId) {
        viewModel.getBookById(bookId) { book ->
            book?.let {
                title = it.title
                author = it.author
                publisher = it.publisher
                isbn = it.isbn
                description = it.description
                imageUrl = it.imageUrl
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Titulo da Pagina
        Text(
            text = "Editar Informações do Livro",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("Editora") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("URL da Capa") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri)
        )

        Spacer(modifier = Modifier.height(16.dp))


        // Botão de de salvar alterações
        Button(
            onClick = {
                if (title.isBlank() || author.isBlank() || publisher.isBlank() || isbn.isBlank() || description.isBlank() || imageUrl.isBlank()) {

                    Toast.makeText(context, "Todos os campos são obrigatórios.", Toast.LENGTH_SHORT).show()
                } else {

                    val updatedBook = BookEntity(
                        id = bookId,
                        title = title,
                        author = author,
                        publisher = publisher,
                        isbn = isbn,
                        description = description,
                        imageUrl = imageUrl
                    )


                    viewModel.updateBook(updatedBook) { success ->
                        if (success) {
                            Toast.makeText(context, "Livro atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Erro ao atualizar o livro!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Alterações")
        }
    }
}
