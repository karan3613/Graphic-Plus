package com.example.graphiiceraapp.BottomNavigation

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

sealed  class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
 ) {
    data object Home : BottomBarScreen (
        route = "Home" ,
        title = "Home",
        icon =  Icons.Default.Home,
    )
    data object Attendance : BottomBarScreen(
        route = "Attendance",
        title = "Attendance",
        icon = Icons.Default.DateRange
    )
}
