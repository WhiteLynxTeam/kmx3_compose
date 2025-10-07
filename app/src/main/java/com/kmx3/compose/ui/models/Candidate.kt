package com.kmx3.compose.ui.models

data class Candidate(
    val name: String,
    val age: Int,
    val photoUrl: String?,
    val resumeUrl: String?,
    val courses: List<String>,
    val achievements: Map<String, Int>
)