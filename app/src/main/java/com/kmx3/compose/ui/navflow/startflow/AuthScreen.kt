package com.kmx3.compose.ui.navflow.startflow

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmx3.compose.R

@Composable
fun AuthScreen(
    state: AuthScreenState, events: AuthScreenEvents,
//    onLoginClick: (String, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    BackHandler(enabled = true) {
        showDialog = true
    }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        // Логотип (png/svg ресурс)
        Image(
            painter = painterResource(R.drawable.ic_logo_miel_bordo), // замените на ваш ресурс
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Добро пожаловать!",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = state.login,
            onValueChange = events::onLoginEntered,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Логин") },
            shape = RoundedCornerShape(10.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.pass,
            onValueChange = events::onPassEntered,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Пароль") },
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF990042)),
            onClick = events::onAuth,
            enabled = !state.isLoading
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(16.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
                Text("Войти")
            }
        }

        if (state.error != null) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Выполняя вход в аккаунт вы соглашаетесь с Условиями и Политикой конфиденциальности",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
    if (showDialog) {
        val activity = LocalActivity.current
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = Color.White,
            title = { Text("Подтверждение выхода") },
            text = { Text("Вы уверены, что хотите выйти из приложения?") },
            confirmButton = {
                Button(
                    modifier = Modifier
                        .height(36.dp),
                    onClick = { activity?.finishAffinity() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        "Да",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            },
            dismissButton = {
                Button(
                    modifier = Modifier
                        .height(36.dp),
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        "Нет",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        )
    }
}

data class AuthScreenState(
    val login: String,
    val pass: String,
    val error: String?,
    val canGoNext: Boolean,
    val isLoading: Boolean = false
)

interface AuthScreenEvents {
    fun onLoginEntered(name: String)
    fun onPassEntered(name: String)
    fun onAuth()
}

@Preview(showBackground = true)
@Composable
fun PreviewAuthScreen() {
    AuthScreen(
        AuthScreenState(login = "", pass = "", isError = false, canGoNext = false),
        object : AuthScreenEvents {
            override fun onLoginEntered(name: String) {}
            override fun onPassEntered(name: String) {}
            override fun onAuth() {}
        }
    )
}
