package com.example.graphiiceraapp


import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    NavGraph(navController = navController)
}


