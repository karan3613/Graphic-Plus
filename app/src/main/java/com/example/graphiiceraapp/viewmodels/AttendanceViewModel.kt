package com.example.graphiiceraapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphiiceraapp.Constants.Resource
import com.example.graphiiceraapp.Constants.UNIVID
import com.example.graphiiceraapp.Data.PrefDataStore.UserInfoImpl
import com.example.graphiiceraapp.Data.models.AttendanceState
import com.example.graphiiceraapp.Data.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {
    private val _attendanceState = mutableStateOf(AttendanceState())
    val attendanceState : State<AttendanceState> = _attendanceState

    init {
        getAttendance(univId = UNIVID.univ_id)
    }
    private fun getAttendance(univId: String) {
        viewModelScope.launch {
            val result = userRepositoryImpl.getAttendance(univId)
            when(result){
                is Resource.Error -> {
                    _attendanceState.value = AttendanceState(error = result.message?: "An unknown error has occured ")
                }
                is Resource.Loading -> {
                    _attendanceState.value = AttendanceState(isLoading = true)
                }

                is Resource.Success -> {
                    _attendanceState.value = AttendanceState(attendance = result.data)
                }

            }
        }
    }
}