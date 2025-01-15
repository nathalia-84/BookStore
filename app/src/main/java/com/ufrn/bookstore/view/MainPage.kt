package com.ufrn.bookstore.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ufrn.bookstore.enums.AppRoutes
import com.ufrn.bookstore.model.ButtonData

@Composable
fun MainPage(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        // Logo do app
        Image(
            painter = painterResource(id = com.ufrn.bookstore.R.drawable.logo_books),
            modifier = Modifier.size(180.dp),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App logo"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título da Página
        Text(
            text = "O que você quer fazer?",
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Funções a serem executadas ao clicar nos botões
        fun onAddBookClick() {
            navController.navigate(AppRoutes.add)
        }

        fun onListBooksClick() {
            navController.navigate(AppRoutes.list)
        }

        // Lista de botões a serem exibidos na tela
        val buttons = listOf(
            ButtonData("Adicionar Livro", { onAddBookClick() }),
            ButtonData("Listar meus Livros", { onListBooksClick() })
        )

        // Grid para exibir botões
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(buttons.size) { index ->
                val buttonData = buttons[index]
                Button(
                    onClick = buttonData.onClick,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .aspectRatio(2f)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = buttonData.label,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(8.dp),
                            maxLines = 2,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
