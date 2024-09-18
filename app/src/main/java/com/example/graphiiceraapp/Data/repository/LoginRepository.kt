package com.example.graphiiceraapp.Data.repository

import com.example.graphiiceraapp.Constants.Resource
import com.example.graphiiceraapp.Data.models.Result

interface LoginRepository {
    suspend fun getPassword(
        univId : String ,
        email : String
    ): Resource<Result>
    suspend fun getUser(
        univId : String ,
        password : String
    ): Resource<Result>
    suspend fun getUnivId(
        mobileNo : String
    ): Resource<Result>

}