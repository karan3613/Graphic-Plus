package com.example.graphiiceraapp.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphiiceraapp.Data.PrefDataStore.Session

import com.example.graphiiceraapp.Data.PrefDataStore.UserInfoImpl
import com.example.graphiiceraapp.Data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userInfoImpl: UserInfoImpl
) : ViewModel() {
    val user = userInfoImpl.getUser().stateIn(
        scope = viewModelScope ,
        started = SharingStarted.WhileSubscribed() ,
        initialValue = Session(isLogin = false , univId = "")
    )
}