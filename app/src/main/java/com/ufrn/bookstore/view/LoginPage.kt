package com.ufrn.bookstore.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufrn.bookstore.R
import com.ufrn.bookstore.SharedPreferencesManager
import com.ufrn.bookstore.enums.AppRoutes

@Composable
fun LoginPage(navController: NavController) {
    val context = LocalContext.current
    val prefsManager = remember { SharedPreferencesManager(context) }

    // Salva as credenciais de teste
    LaunchedEffect(Unit) {
        if (prefsManager.getLoginCredentials().first == null) {
            prefsManager.saveLoginCredentials("admin", "admin")
        }
    }

    // Estados para os campos de texto
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            painter = painterResource(id = R.drawable.logo_books),
            modifier = Modifier.size(180.dp),
            contentScale = ContentScale.FillBounds,
            contentDescription = "App logo"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título da Página
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de E-mail
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Senha
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de Login
        Button(
            onClick = {
                val (savedEmail, savedPassword) = prefsManager.getLoginCredentials()

                if (email == savedEmail && password == savedPassword) {
                    navController.navigate(AppRoutes.main) // Navega para a tela principal do app
                } else {
                    Toast.makeText(context, "E-mail ou senha inválidos.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto clicável "Esqueci minha senha"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Esqueci minha senha",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.clickable {
                    /* Redirecionar para a tela de recuperação de senha */
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto clicável "Cadastre-se"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Não possui conta? Cadastre-se aqui",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.clickable {
                    /* Redirecionar para a tela de cadastro */
                }
            )
        }
    }
}