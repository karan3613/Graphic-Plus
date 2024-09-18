package com.example.graphiiceraapp.Data.models

data class AttendanceState(
    val attendance: Attendance? = null ,
    val error : String = ""  ,
    val isLoading : Boolean = false
)
