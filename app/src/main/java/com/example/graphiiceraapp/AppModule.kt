package com.example.graphiiceraapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.graphiiceraapp.Constants.Constants.BASE_URL
import com.example.graphiiceraapp.Data.PrefDataStore.UserInfo
import com.example.graphiiceraapp.Data.PrefDataStore.UserInfoImpl
import com.example.graphiiceraapp.Data.repository.LoginRepositoryImpl
import com.example.graphiiceraapp.Data.repository.UserRepositoryImpl
import com.example.graphiiceraapp.Retrofit.apiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Objects
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Singleton
    @Provides
    fun provideLoginRepository(
        api: apiInterface
    ) = LoginRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideUserRepository(
        api : apiInterface
    ) = UserRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideApi(): apiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("user_data")}
        )
    }


    @Provides
    fun provideUserPref(dataStore: DataStore<Preferences>) : UserInfo = UserInfoImpl(dataStore)


}


