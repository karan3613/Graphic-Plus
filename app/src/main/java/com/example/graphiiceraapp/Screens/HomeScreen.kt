package com.example.graphiiceraapp.Screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.graphiiceraapp.Constants.GraphRoutes
import com.example.graphiiceraapp.Constants.HomeRoutes
import com.example.graphiiceraapp.Constants.UNIVID
import com.example.graphiiceraapp.viewmodels.UserViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewmodel: UserViewModel = hiltViewModel()
){

    val drawerState  = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
                        ModalDrawerSheet(
                            modifier = Modifier.background(Color.White)
                        ){
                            Spacer(modifier = Modifier.height(15.dp))
                            navItems.forEachIndexed{index, item ->
                                NavigationDrawerItem(
                                    label = { Text(text = item.title , fontWeight = FontWeight.SemiBold) },
                                    selected = (index==selectedItemIndex),
                                    onClick = {
                                        selectedItemIndex = index
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector =if(selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.title )
                                    },
                                    modifier = Modifier
                                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                                        .width(240.dp)
                                )
                            }
                        }
                        },
        drawerState = drawerState,
            ) {
        Scaffold(
            topBar = {
                HomeTopBar(drawerState)
            },
            bottomBar = {
                HomeBottomBar(navController)
            } ,

            ){
            HomeScreenDesign(modifier = Modifier.padding(it) , viewmodel , navController = navController)
        }
    }

}

val navItems = listOf(
    NavigationItem("Notice" , Icons.Filled.Info ,Icons.Outlined.Info ),
    NavigationItem("Change Password" , Icons.Filled.Lock , Icons.Outlined.Lock),
    NavigationItem("Logout" , Icons.Filled.ExitToApp , Icons.Outlined.ExitToApp)
)

@Composable
fun HomeScreenDesign(modifier: Modifier, viewmodel: UserViewModel , navController: NavHostController) {
    val profileState = viewmodel.profileState.value
    val communitiesState = viewmodel.communitiesState.value
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (profileState.profile != null) {
            val profile = profileState.profile
            Card(
                modifier = Modifier
                    .height(110.dp)
                    .width(250.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(Color.White)
                    .clickable {
                        navController.navigate(HomeRoutes.PROFILE)
                    },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 20.dp,
                ),
                border = BorderStroke(2.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )

            )
            {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(
                        modifier = Modifier.size(80.dp),
                        shape = CircleShape
                    ) {
                        AsyncImage(model = profile.image, contentDescription = "User_Image" ,
                            contentScale = ContentScale.FillBounds)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier
                    ) {
                        Text(
                            text = (profile.firstname) + " " + (profile.lastname),
                            color = Color.Black,
                            fontFamily = FontFamily.Serif
                        )
                        Text(
                            text = profile.univ_rollNo,
                            color = Color.Black,
                            fontFamily = FontFamily.Serif

                        )
                        Text(
                            text = profile.course,
                            color = Color.Black,
                            fontFamily = FontFamily.Serif
                        )

                    }
                }
            }
        }

        if (profileState.error.isNotBlank()) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = profileState.error,
                fontSize = 20.sp,
                color = Color.Red,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
        }
        if (profileState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                color = Color.Red,
                strokeCap = StrokeCap.Round,
                strokeWidth = 5.dp
            )
        }
        LazyRow(
            modifier = Modifier
        ) {
            communitiesState.communities?.let {
                items(it.size) { index ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .height(400.dp)
                            .width(300.dp)
                            .clickable {
                                UNIVID.communityIndex = index
                                navController.navigate(HomeRoutes.CLUB)
                            },
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        )
                    ) {
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = it[index].banner,
                            contentScale = ContentScale.FillBounds,
                            contentDescription = "Club Banner"
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun HomeBottomBar(navController: NavHostController) {
    var selected by remember {
        mutableStateOf(Icons.Default.Home)
    }
    BottomAppBar(
        containerColor = Color.Red,
        modifier = Modifier.height(60.dp)
    ) {
        IconButton(onClick = {
            selected = Icons.Default.Home
            navController.navigate(GraphRoutes.HOME){
                popUpTo(0)
            }
        },
            modifier = Modifier.weight(1f)
        ){
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.Default.Home , contentDescription = "HomeScreen" ,
                tint = if(selected == Icons.Default.Home) Color.White else Color.Black
            )
        }
        IconButton(onClick = {
            selected = Icons.Default.AccountBox
            navController.navigate(GraphRoutes.ATTENDANCE)

        },
            modifier = Modifier.weight(1f)
        ){
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.Default.AccountBox, contentDescription = "AttendanceScreen" ,
                tint = if(selected == Icons.Default.AccountBox) Color.White else Color.Black
            )
        }
    }

}

data class NavigationItem(
    val title : String ,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector
)


sealed class BottomNavigationScreens(val name : String){
    data object Home : BottomNavigationScreens("Home")
    data object Attendance : BottomNavigationScreens("Attendance")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
 TopAppBar(
     title = {},
     colors = TopAppBarDefaults.topAppBarColors(
         containerColor = Color.Red,
         navigationIconContentColor = Color.Black,
         titleContentColor = Color.White
     ),
     navigationIcon = {
         IconButton(onClick = {
             scope.launch {
                 drawerState.open()
             }
             }) {
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.Default.Settings, contentDescription = "Navigation")
         }
     }
    )
}
