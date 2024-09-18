package com.example.graphiiceraapp

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.graphiiceraapp.BottomNavigation.BottomBarScreen
import com.example.graphiiceraapp.Constants.AuthRoutes
import com.example.graphiiceraapp.Constants.GraphRoutes
import com.example.graphiiceraapp.Constants.HomeRoutes
import com.example.graphiiceraapp.Screens.AttendanceScreen
import com.example.graphiiceraapp.Screens.ClubScreen
import com.example.graphiiceraapp.Screens.ForgotIdScreen
import com.example.graphiiceraapp.Screens.ForgotPasswordScreen
import com.example.graphiiceraapp.Screens.HomeScreen
import com.example.graphiiceraapp.Screens.LoginScreen
import com.example.graphiiceraapp.Screens.NoticeScreen
import com.example.graphiiceraapp.Screens.ProfileScreen
import com.example.graphiiceraapp.Screens.SettingsScreen
import com.example.graphiiceraapp.Screens.SplashScreen
import com.example.graphiiceraapp.viewmodels.LoginViewModel
import com.example.graphiiceraapp.viewmodels.SplashViewModel
import com.example.graphiiceraapp.viewmodels.UserViewModel


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavGraph(navController: NavHostController){
    val splashViewModel  = hiltViewModel<SplashViewModel>()
    NavHost(navController = navController,
            startDestination = GraphRoutes.SPLASH ){
        AuthNavGraph(navController)
        navigation(startDestination = BottomBarScreen.Home.route , route = GraphRoutes.HOME){
            composable(route = BottomBarScreen.Home.route) {

                HomeScreen(navController)
            }
            composable(route = HomeRoutes.PROFILE){
                ProfileScreen()
            }
            composable(route = HomeRoutes.CLUB) {
                ClubScreen()
            }
        }
        navigation(startDestination = BottomBarScreen.Attendance.route , route = GraphRoutes.ATTENDANCE){
            composable(route = BottomBarScreen.Attendance.route){
                AttendanceScreen(navController)
            }
        }
        navigation(startDestination = "Splash" , route = GraphRoutes.SPLASH){
            composable("Splash"){
                SplashScreen(navController , splashViewModel)
            }
        }
    }
}


