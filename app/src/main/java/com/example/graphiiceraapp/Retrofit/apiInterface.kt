package com.example.graphiiceraapp.Retrofit

import com.example.graphiiceraapp.Data.models.Attendance
import com.example.graphiiceraapp.Data.models.Communities
import com.example.graphiiceraapp.Data.models.Profile
import com.example.graphiiceraapp.Data.models.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface apiInterface {

    @GET("/auth/forgotPassword/{univId}/{email}")
    suspend fun getPassword(
        @Path("univId") univId: String,
        @Path("email") email : String
        ) : Result

    @GET("/auth/login/{univId}/{password}")
    suspend fun getUser(
        @Path("univId") univId: String,
        @Path("password") password : String ,
    ) : Result

    @GET("/auth/forgotId/{mobileNo}")
    suspend fun getUnivId(
        @Path("mobileNo") mobileNo : String ,
    ) : Result

    @GET("/community/clubs")
    suspend fun getClubs(): Communities

    @GET("/user/{univId}")
    suspend fun getProfile(
        @Path("univId") univId: String
    ) : Profile

    @GET("/attendance/{univId}")
    suspend fun getAttendance(
        @Path("univId") univId: String
    ) : Attendance
}