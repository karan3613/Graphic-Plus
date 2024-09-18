package com.example.graphiiceraapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphiiceraapp.Constants.Resource
import com.example.graphiiceraapp.Constants.UNIVID
import com.example.graphiiceraapp.Data.PrefDataStore.UserInfoImpl
import com.example.graphiiceraapp.Data.models.ProfileState
import com.example.graphiiceraapp.Data.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val userInfoImpl: UserInfoImpl
) : ViewModel() {
    private val _profileState = mutableStateOf(ProfileState())
    val profileState : State<ProfileState> = _profileState

    init{
        getProfile(UNIVID.univ_id)
    }
    private fun getProfile(univId: String) {
        viewModelScope.launch {
            when(val result = userRepositoryImpl.getProfile(univId)){
                is Resource.Error -> {
                    _profileState.value = ProfileState(error = result.message?: "An unknown error has occured ")
                }
                is Resource.Loading -> {
                    _profileState.value = ProfileState(isLoading =  true)
                }

                is Resource.Success -> {
                    _profileState.value = ProfileState(profile = result.data)
                }

            }
        }
    }
}