package com.correxapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.correxapp.presentation.ui.screens.*

@Composable
fun AppNavigation() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Screen.Dashboard.route) {
        composable(Screen.Dashboard.route) { DashboardScreen(nav) }
        composable(Screen.CreateExam.route) { CreateExamScreen(nav) }
        composable(Screen.Scan.route) { ScanScreen(nav) }
        composable(Screen.Settings.route) { SettingsScreen(nav) }
    }
}
