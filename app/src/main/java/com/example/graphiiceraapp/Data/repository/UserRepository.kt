package com.example.graphiiceraapp.Data.repository

import com.example.graphiiceraapp.Constants.Resource
import com.example.graphiiceraapp.Data.models.Attendance
import com.example.graphiiceraapp.Data.models.Communities
import com.example.graphiiceraapp.Data.models.Profile

interface UserRepository {
    suspend fun getClubs(): Resource<Communities>
    suspend fun getProfile(
        univId : String
    ): Resource<Profile>
    suspend fun getAttendance(
        univId: String
    ): Resource<Attendance>
}