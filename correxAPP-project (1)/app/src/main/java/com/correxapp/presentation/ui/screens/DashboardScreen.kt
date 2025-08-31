package com.correxapp.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.correxapp.presentation.navigation.Screen

@Composable
fun DashboardScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        Text("correxAPP - Dashboard", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { nav.navigate(Screen.CreateExam.route) }) {
            Text("Criar Prova")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { nav.navigate(Screen.Scan.route) }) {
            Text("Escanear Folha de Respostas")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { nav.navigate(Screen.Settings.route) }) {
            Text("Configurações")
        }
    }
}
