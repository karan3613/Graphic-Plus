package com.example.graphiiceraapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.graphiiceraapp.Constants.GraphRoutes
import com.example.graphiiceraapp.R
import com.example.graphiiceraapp.viewmodels.SplashViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
 fun SplashScreen(navController: NavHostController, splashViewModel: SplashViewModel) {
    val userStatus =  splashViewModel.user.collectAsState()
    if(userStatus.value.isLogin){
        navController.navigate(GraphRoutes.HOME){
            popUpTo(GraphRoutes.SPLASH)
        }
    }
    else{
        navController.navigate(GraphRoutes.AUTHENTICATION){
            popUpTo(0)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    , contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id  = R.drawable.graphic_splash ) ,
            contentDescription = null ,
            )
        }
}