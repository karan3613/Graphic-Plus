package com.example.graphiiceraapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.graphiiceraapp.ui.theme.LightBlue
import com.example.graphiiceraapp.viewmodels.LoginViewModel


@Composable
fun ForgotPasswordScreen(navController: NavHostController, AuthViewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }
    var applicationNo by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = applicationNo,
            onValueChange = {
                applicationNo = it
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "phone no")
            },
            label = {
                Text(
                    text = "Application No"
                )
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
            },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "phone no")
            },
            label = {
                Text(
                    text = "Email"
                )
            }
        )
        ShowDatePickerDialog(context = context)
        Spacer(modifier = Modifier
            .height(5.dp)
            .fillMaxWidth())
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape ,
            colors = ButtonDefaults.buttonColors(
                containerColor = LightBlue
            )
        ){
            Text(
                text = "Reset"
            )
        }
    }
}

