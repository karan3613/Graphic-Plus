package com.example.graphiiceraapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphiiceraapp.Constants.Resource
import com.example.graphiiceraapp.Data.PrefDataStore.Session
import com.example.graphiiceraapp.Data.PrefDataStore.UserInfoImpl
import com.example.graphiiceraapp.Data.models.ResultState
import com.example.graphiiceraapp.Data.repository.LoginRepositoryImpl
import com.example.graphiiceraapp.Data.repository.UserRepositoryImpl
import com.example.graphiiceraapp.Screens.LoginCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepositoryImpl: LoginRepositoryImpl ,
    private  val userInfoImpl: UserInfoImpl
) : ViewModel() {

    private val _loginState = mutableStateOf(ResultState())
    val loginState: State<ResultState> = _loginState

    private val _forgotPasswordState = mutableStateOf(ResultState())
    val forgotPasswordState: State<ResultState> = _forgotPasswordState

    private val _forgotIdState = mutableStateOf(ResultState())
    val forgotIdState: State<ResultState> = _forgotIdState


    private val _isUsernameSaved = mutableStateOf(false)
    val isUsernameSaved: State<Boolean> = _isUsernameSaved

     suspend fun addUnivId(univId: String) {
            userInfoImpl.setUser(
                Session(
                    isLogin = true,
                    univId = univId
                )
            )
         _isUsernameSaved.value = true
    }


    fun verifyLogin(univId: String, password: String){
        viewModelScope.launch {
            val response = loginRepositoryImpl.getUser(univId = univId, password = password)
            when (response) {
                is Resource.Error -> {
                    _loginState.value =
                        ResultState(error = response.message ?: " An unknown error has occurred ")
                }

                is Resource.Loading -> {
                    _loginState.value = ResultState(isLoading = true)
                }

                is Resource.Success -> {
                    _loginState.value = ResultState(result = response.data)
                    _isUsernameSaved.value = true
                }
            }
        }
    }

    fun verifyForgotPassword(univId: String, email: String) {
        viewModelScope.launch {
            when (val response = loginRepositoryImpl.getPassword(univId, email)) {
                is Resource.Error -> {
                    _forgotPasswordState.value =
                        ResultState(error = response.message ?: " An unknown error has occurred ")
                }

                is Resource.Loading -> {
                    _forgotPasswordState.value = ResultState(isLoading = true)
                }

                is Resource.Success -> {
                    _forgotPasswordState.value = ResultState(result = response.data)
                }
            }
        }
    }

    fun verifyForgotId(mobileNo: String) {
        viewModelScope.launch {
            when (val response = loginRepositoryImpl.getUnivId(mobileNo)) {
                is Resource.Error -> {
                    _forgotIdState.value =
                        ResultState(error = response.message ?: " An unknown error has occurred ")
                }

                is Resource.Loading -> {
                    _forgotIdState.value = ResultState(isLoading = true)
                }

                is Resource.Success -> {
                    _forgotIdState.value = ResultState(result = response.data)
                }
            }
        }

    }
}


