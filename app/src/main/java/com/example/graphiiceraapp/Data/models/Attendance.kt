package com.example.graphiiceraapp.Data.models

data class Attendance(
    val id: String,
    val subjects: List<Subject>,
    val univ_id: String
)