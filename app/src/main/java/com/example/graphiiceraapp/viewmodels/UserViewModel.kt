package com.example.graphiiceraapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphiiceraapp.Constants.Resource
import com.example.graphiiceraapp.Constants.UNIVID
import com.example.graphiiceraapp.Data.PrefDataStore.Session
import com.example.graphiiceraapp.Data.PrefDataStore.UserInfoImpl
import com.example.graphiiceraapp.Data.models.AttendanceState
import com.example.graphiiceraapp.Data.models.CommunitiesState
import com.example.graphiiceraapp.Data.models.Community
import com.example.graphiiceraapp.Data.models.CommunityState
import com.example.graphiiceraapp.Data.models.ProfileState
import com.example.graphiiceraapp.Data.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val userInfoImpl: UserInfoImpl
) : ViewModel() {

    private val _communityState = mutableStateOf(CommunityState())
    val communityState : State<CommunityState> = _communityState


    private val _profileState = mutableStateOf(ProfileState())
    val profileState : State<ProfileState> = _profileState



    private  val _communitiesState = mutableStateOf(CommunitiesState())
    val communitiesState : State<CommunitiesState> = _communitiesState



    init {
         val user = userInfoImpl.getUser().stateIn(
            scope = viewModelScope ,
            started = SharingStarted.WhileSubscribed() ,
            initialValue = Session(isLogin = false , univId = "")
        )
        getProfile(univId = UNIVID.univ_id)
        getCommunities()
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