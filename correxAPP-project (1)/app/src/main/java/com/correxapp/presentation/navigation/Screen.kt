package com.correxapp.presentation.navigation

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard")
    data object CreateExam : Screen("createExam")
    data object ExamDetails : Screen("examDetails/{examId}") { fun route(id: Long) = "examDetails/$id" }
    data object Scan : Screen("scan")
    data object Settings : Screen("settings")
    data object ResultDetails : Screen("resultDetails/{resultId}") { fun route(id: Long) = "resultDetails/$id" }
}
