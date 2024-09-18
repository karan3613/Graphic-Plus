package com.example.graphiiceraapp.Screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.graphiiceraapp.Constants.AuthRoutes
import com.example.graphiiceraapp.Constants.GraphRoutes
import com.example.graphiiceraapp.Constants.UNIVID
import com.example.graphiiceraapp.Data.PrefDataStore.Session
import com.example.graphiiceraapp.Data.PrefDataStore.UserInfoImpl

import com.example.graphiiceraapp.R
import com.example.graphiiceraapp.ui.theme.LightBlue
import com.example.graphiiceraapp.viewmodels.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun LoginScreen(navController: NavHostController , viewModel: LoginViewModel = hiltViewModel()) {
 Column(
     modifier = Modifier
         .fillMaxSize()
         .background(Color.White),
     horizontalAlignment = Alignment.CenterHorizontally ,
     verticalArrangement = Arrangement.Center
 ) {
    Image(painter = painterResource(id = R.drawable.graphiclogo_login), contentDescription = null ,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(5.dp)
        )
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp))
    LoginSystem(navController , viewModel)
 }
}

data class LoginCredentials(
    val univId : String = "",
    val password : String = "" ,
    val isPasswordVisible : Boolean = false
)
@Composable
fun LoginSystem(navController: NavHostController , viewModel: LoginViewModel ) {

    var loginCredentials by remember {
        mutableStateOf(LoginCredentials())
    }
    val loginstate  =  viewModel.loginState.value

    var isDetailsWrong by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val isUsernameSaved by viewModel.isUsernameSaved
    LaunchedEffect(isUsernameSaved) {
        if (isUsernameSaved) {
            UNIVID.univ_id = loginCredentials.univId
            navController.navigate(GraphRoutes.HOME) {
                popUpTo(0)
            }
        }
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(start = 20.dp , end = 20.dp)
    ) {
        if(isDetailsWrong){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "The entered ApplicationNo or Password is incorrect" ,
                    color = Color.Red,
                    textAlign = TextAlign.Center ,
                    fontSize = 12.sp
                )
            }
        }
        LoginTextfieldComponent(
            value = loginCredentials.univId ,
            onChange = { data->
                loginCredentials = loginCredentials.copy(univId = data)
            },
            label = "ApplicationNo",
            keyboardOptions =  KeyboardOptions(),
            leadingIcon = Icons.Default.Person
        )
        LoginTextfieldComponent(
            value = loginCredentials.password,
            onChange = {data->
                       loginCredentials = loginCredentials.copy(password = data)
            },
            label = "Password",
            keyboardOptions = KeyboardOptions() ,
            leadingIcon =  Icons.Default.Lock
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            if (!loginstate.isLoading){
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightBlue
                    ),
                    onClick = {
                        viewModel.verifyLogin(loginCredentials.univId , loginCredentials.password)
                        if(loginstate.result != null){
                            UNIVID.univ_id = loginCredentials.univId
                        }
                        if (loginstate.error.isNotBlank()){
                            isDetailsWrong = true
                        }
                    }
                ) {
                    Text(text = "Login" , color = Color.White)
                }
            }
            else{
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightBlue
                    ),
                    onClick = {
                    }
                ) {
                    CircularProgressIndicator(
                        color = Color.White ,
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 5.dp ,
                        strokeCap = StrokeCap.Round
                    )
                }

            }


        }
        TextButtonComponent(
            label = "ForgotPassword?" ,
            navController = navController,
            route = AuthRoutes.FORGOT_PASSWORD
        )
        TextButtonComponent(
            label = "ForgotId",
            navController =  navController ,
            route = AuthRoutes.FORGOT_ID
        )
    }
}

@Composable
fun LoginTextfieldComponent(
    value : String ,
    onChange :(String)->Unit ,
    label : String  ,
    keyboardOptions: KeyboardOptions,
    leadingIcon : ImageVector
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp) ,
        verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            imageVector = leadingIcon , contentDescription = null
            , modifier = Modifier
                .height(35.dp)
                .width(35.dp))
        TextField(
            value = value ,
            onValueChange = onChange,
            label = {
                Text(
                    text = label,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontStyle = FontStyle.Normal,
                    fontSize = 15.sp
                )
            },
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White ,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }
}
@Composable
fun TextButtonComponent(
    label : String ,
    navController: NavHostController, route : String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = {
            navController.navigate(route = route )
        }){
            Text(
                text = label,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp
            )

        }
    }
}

