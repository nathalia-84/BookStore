package com.ufrn.bookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ufrn.bookstore.db.AppDatabase
import com.ufrn.bookstore.enums.AppRoutes
import com.ufrn.bookstore.ui.theme.BookStoreTheme
import com.ufrn.bookstore.view.AddBookPage
import com.ufrn.bookstore.view.BookDetailsPage
import com.ufrn.bookstore.view.BookListPage
import com.ufrn.bookstore.view.EditBookPage
import com.ufrn.bookstore.view.LoginPage
import com.ufrn.bookstore.view.MainPage
import com.ufrn.bookstore.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o banco de dados e DAO
        val db = AppDatabase.getInstance(applicationContext) // Use applicationContext
        val productDao = db.bookDao()

        // Inicializa o ViewModel
        val viewModel = AppViewModel(productDao)

        enableEdgeToEdge()
        setContent {
            BookStoreTheme {
                //Inicializa o navController
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    NavHost(navController, startDestination = AppRoutes.login) {
                        composable(AppRoutes.login) {
                            LoginPage(navController)
                        }
                        composable(AppRoutes.main) {
                            MainPage(navController)
                        }
                        composable(AppRoutes.add) {
                            AddBookPage(viewModel)
                        }
                        composable(AppRoutes.list) {
                            BookListPage(
                                viewModel = viewModel,
                                onBookClick = { book ->
                                    navController.navigate("details/${book.id}") // Navegar para detalhes
                                }
                            )
                        }
                        composable(
                            route = "details/{bookId}",
                            arguments = listOf(navArgument("bookId") { type = NavType.LongType }) // Definindo o parâmetro `bookId`
                        ) { backStackEntry ->
                            val bookId = backStackEntry.arguments?.getLong("bookId") ?: return@composable
                            BookDetailsPage(viewModel, navController, bookId)
                        }
                       composable(
                            route = "edit/{bookId}",
                            arguments = listOf(navArgument("bookId") { type = NavType.LongType }) // Definindo o parâmetro `bookId`
                        ) { backStackEntry ->
                            val bookId = backStackEntry.arguments?.getLong("bookId") ?: return@composable
                            EditBookPage(viewModel,navController, bookId)
                        }
                    }
                }
            }
        }
    }
}