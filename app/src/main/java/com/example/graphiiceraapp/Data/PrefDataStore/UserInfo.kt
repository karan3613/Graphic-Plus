package com.example.graphiiceraapp.Data.PrefDataStore

import kotlinx.coroutines.flow.Flow

interface UserInfo {
    fun getUser() : Flow<Session>
    suspend fun setUser(user : Session)
}