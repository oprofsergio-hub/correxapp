package com.correxapp.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LoadingDialog(show: Boolean) {
    if (show) Text("Carregando...")
}
