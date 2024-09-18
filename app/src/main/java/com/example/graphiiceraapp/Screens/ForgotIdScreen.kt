package com.example.graphiiceraapp.Screens

import android.app.DatePickerDialog
import android.content.Context
import android.health.connect.datatypes.units.Length
import android.net.http.HttpException
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.graphiiceraapp.ui.theme.LightBlue
import com.example.graphiiceraapp.viewmodels.LoginViewModel
import java.util.Calendar


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun ForgotIdScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var mobileNo by remember {
        mutableStateOf("")
    }
    var dob by remember {
        mutableStateOf("")
    }
    val forgotIdState =  viewModel.forgotIdState.value
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
            value = mobileNo,
            onValueChange = {
                mobileNo = it
            },
            leadingIcon = {
                Icon(Icons.Default.Call, contentDescription = "phone no")
            },
            label = {
                Text(
                    text = "Mobile No"
                )
            }
        )
        dob =  ShowDatePickerDialog(context = context)
        Spacer(modifier = Modifier
            .height(5.dp)
            .fillMaxWidth())
        Button(
            onClick = {
                viewModel.verifyForgotId(mobileNo)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape ,
            colors = ButtonDefaults.buttonColors(
                containerColor = LightBlue
            )
        ){
            if(forgotIdState.isLoading){
              CircularProgressIndicator(
                  modifier = Modifier.size(5.dp),
                  color = Color.White ,
                  strokeWidth = 2.dp ,
                  strokeCap = StrokeCap.Round ,
              )
            }
            else if( forgotIdState.result != null){
                Text(text = forgotIdState.result.result)
            }
            else{
                Text(text  = "verify" , color = Color.White , fontSize = 10.sp)
            }
        }

    }
}

val dobIcon = Icons.Default.Add// Replace with your desired DoB icon (or use painterResource for custom icons)

@Composable
fun ShowDatePickerDialog(context: Context) : String {
    var selectedDate by remember { mutableStateOf("") }
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(context,
        { view, year, monthOfYear, dayOfMonth ->
           selectedDate = "$dayOfMonth/$monthOfYear/$year"
        },
        year, month, day
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = selectedDate ,
        onValueChange = {
            selectedDate = it
        }, // Disable text editing
        readOnly = true, // Make text field read-only
        label = {
            Text(
                text = "DOB" ,
                )
                },
        leadingIcon = {
                Icon(dobIcon, contentDescription = "Date of Birth")
        },
        trailingIcon = {
            IconButton(onClick = {datePickerDialog.show()}) {
                Icon(Icons.Default.DateRange, contentDescription = "Open date picker")
            }
        }
    )
    return selectedDate
    }
