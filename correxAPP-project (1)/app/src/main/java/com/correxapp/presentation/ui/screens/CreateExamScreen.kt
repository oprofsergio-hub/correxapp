package com.correxapp.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.correxapp.domain.model.Exam

@Composable
fun CreateExamScreen(nav: NavController) {
    var name by remember { mutableStateOf("Prova 1") }
    var questions by remember { mutableStateOf("10") }
    var alternatives by remember { mutableStateOf("5") }
    Column(Modifier.padding(16.dp)) {
        Text("Criar Prova", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nome") })
        OutlinedTextField(value = questions, onValueChange = { questions = it }, label = { Text("Total de Questões") })
        OutlinedTextField(value = alternatives, onValueChange = { alternatives = it }, label = { Text("Alternativas por questão") })
        Spacer(Modifier.height(16.dp))
        Text("Dica: o gabarito será inicializado como A para todas as questões (placeholder).")
    }
}
