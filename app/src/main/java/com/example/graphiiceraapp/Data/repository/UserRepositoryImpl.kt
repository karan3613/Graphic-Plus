package com.example.graphiiceraapp.Data.repository

import com.example.graphiiceraapp.Constants.Resource
import com.example.graphiiceraapp.Data.models.Attendance
import com.example.graphiiceraapp.Data.models.Communities
import com.example.graphiiceraapp.Data.models.Profile
import com.example.graphiiceraapp.Retrofit.apiInterface
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    val api : apiInterface
) : UserRepository{
    override suspend fun getClubs(): Resource<Communities> {
        val response = try {
            api.getClubs()
        }catch (e : Exception){
            return Resource.Error("An unknown error has occured")
        }
        return Resource.Success(response)

    }

    override suspend fun getProfile(univId: String): Resource<Profile> {
        val response = try {
            api.getProfile(univId)
        }catch (e : Exception){
            return Resource.Error("An unknown error has occured")
        }
        return Resource.Success(response)

    }

    override suspend fun getAttendance(univId: String): Resource<Attendance> {
        val response = try {
            api.getAttendance(univId)
        }catch (e : Exception){
            return Resource.Error("An unknown error has occured")
        }
        return Resource.Success(response)
    }
}