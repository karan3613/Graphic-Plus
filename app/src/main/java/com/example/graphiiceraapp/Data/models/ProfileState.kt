package com.example.graphiiceraapp.Data.models

data class ProfileState(
    val profile: Profile? = null,
    val error: String = "" ,
    val isLoading : Boolean = false
)
