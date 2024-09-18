package com.example.graphiiceraapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.graphiiceraapp.Constants.AuthRoutes
import com.example.graphiiceraapp.Constants.GraphRoutes
import com.example.graphiiceraapp.Screens.ForgotIdScreen
import com.example.graphiiceraapp.Screens.ForgotPasswordScreen
import com.example.graphiiceraapp.Screens.LoginScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun NavGraphBuilder.AuthNavGraph(navController : NavHostController){
    navigation(startDestination = AuthRoutes.LOGIN , route = GraphRoutes.AUTHENTICATION){
        composable(AuthRoutes.LOGIN){
            LoginScreen(navController)
        }
        composable(AuthRoutes.FORGOT_PASSWORD){
            ForgotPasswordScreen(navController)
        }
        composable(AuthRoutes.FORGOT_ID){
            ForgotIdScreen(navController)
        }
    }
}