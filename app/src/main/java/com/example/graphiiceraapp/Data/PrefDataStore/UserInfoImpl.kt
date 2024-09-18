package com.example.graphiiceraapp.Data.PrefDataStore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UserInfoImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserInfo  {
    override fun getUser(): Flow<Session> {
        return dataStore.data.catch {
            Log.e("UserInfoImpl", "Error reading preferences", it)
            emit(emptyPreferences())
        }.map { pref ->
            Session(
                isLogin = pref[IS_LOGIN] ?: false,
                univId = pref[USER_ID] ?: ""
            )
        }
    }

    companion object{
        val USER_ID = stringPreferencesKey("user_id")
        val IS_LOGIN = booleanPreferencesKey("user_isLogin")
    }

    override suspend fun setUser(user: Session) {
        dataStore.edit {pref->
            pref[USER_ID] = user.univId
            pref[IS_LOGIN] = user.isLogin
        }
    }

}