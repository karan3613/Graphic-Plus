package com.example.graphiiceraapp.Data.repository

import com.example.graphiiceraapp.Constants.Resource
import com.example.graphiiceraapp.Data.models.Result
import com.example.graphiiceraapp.Retrofit.apiInterface
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api : apiInterface
) : LoginRepository {
    override suspend fun getPassword(
        univId: String,
        email: String
    ): Resource<Result> {
        val response = try{
            api.getPassword(univId,email)
        }
        catch (e : Exception){
            return Resource.Error("An unknown error has ouccured")
        }
        return Resource.Success(response)

    }

    override suspend fun getUser(univId: String, password: String): Resource<Result> {
        val response = try{
            api.getUser(univId , password)
        }catch (e : Exception){
            return Resource.Error("An unknown error has ouccured")
        }
        return Resource.Success(response)
    }

    override suspend fun getUnivId(mobileNo: String): Resource<Result> {
        val response = try{
            api.getUnivId(mobileNo)
        }catch (e : Exception){
            return Resource.Error("An unknown error has ouccured")
        }
        return Resource.Success(response)

    }
}