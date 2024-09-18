package com.example.graphiiceraapp.Data.models

data class ResultState(
    val isLoading: Boolean = false,
    val result: Result? = null,
    val error : String  = ""
    )
