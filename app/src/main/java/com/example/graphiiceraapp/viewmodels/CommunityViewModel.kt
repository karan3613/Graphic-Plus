package com.example.graphiiceraapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphiiceraapp.Constants.Resource
import com.example.graphiiceraapp.Data.PrefDataStore.UserInfoImpl
import com.example.graphiiceraapp.Data.models.CommunitiesState
import com.example.graphiiceraapp.Data.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl,
) : ViewModel() {
    private  val _communitiesState = mutableStateOf(CommunitiesState())
    val communitiesState : State<CommunitiesState> = _communitiesState

    init {
        getCommunities()
    }
    private fun getCommunities() {
        viewModelScope.launch {
            val result = userRepositoryImpl.getClubs()
            when(result){
                is Resource.Error -> {
                    _communitiesState.value = CommunitiesState(error = result.message?: "An unknown error has occured ")
                }
                is Resource.Loading -> {
                    _communitiesState.value = CommunitiesState(isLoading = true)
                }

                is Resource.Success -> {
                    _communitiesState.value = CommunitiesState(communities =  result.data)
                }

            }
        }
    }

}