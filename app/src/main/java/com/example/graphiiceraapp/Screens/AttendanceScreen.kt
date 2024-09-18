package com.example.graphiiceraapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.graphiiceraapp.Data.models.Subject
import com.example.graphiiceraapp.viewmodels.AttendanceViewModel


@Composable
fun AttendanceScreen(navHostController: NavHostController
                     , viewModel: AttendanceViewModel = hiltViewModel()) {
    val attendanceState = viewModel.attendanceState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
    ) {
        if(attendanceState.attendance?.subjects != null){
            Text(
                text = "Your overall attendance is : ",
                color = Color.Blue,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = avg(attendanceState.attendance.subjects).toString(),
                color = Color.Red,
                fontSize = 20.sp ,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))
            LazyRow(
                modifier = Modifier.fillMaxSize()
            ) {
                items(5){
                    TableColumnComponent(it ,attendanceState.attendance.subjects)
                }
            }
        }
        if(attendanceState.error.isNotBlank()){
            Text(modifier = Modifier.fillMaxSize(),
                    text = attendanceState.error ,
                    fontSize = 20.sp ,
                    color = Color.Red ,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold
            )
        }
        if(attendanceState.isLoading){
            Text(text = "Loading" , color = Color.Red , fontSize = 30.sp , fontFamily = FontFamily.Serif)
        }
    }
}

@Composable
fun TableColumnComponent(i: Int, subjects: List<Subject>) {
    val heading = listOf("Subject Name" , "Subject Faculty" , "Subject Code" , "Total Classes" , "PresentClasses" , "Percentage"  )
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally ,
    ) {
        Text(
            text = heading[i],
            fontSize = 20.sp ,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            color = Color.Black
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            items(subjects.size){
                TableInfoItem(i , it , subjects)
            }
        }


    }

}

@Composable
fun TableInfoItem(i: Int, it: Int, subjects: List<Subject>) {
    when (i) {
        0 -> {
            subjects[it].subject_name?.let { name ->
                Text(
                    text = name,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
        1 -> {
            subjects[it].subject_faculty?.let { faculty ->
                Text(
                    text = faculty,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
        2 -> {
            subjects[it].subject_code?.let { code ->
                Text(
                    text = code,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
        3->{
            subjects[it].total_classes?.let { code ->
                Text(
                    text = code.toString(),
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = Color.Blue
                )
            }
        }
        4->{
            subjects[it].present_classes?.let { code ->
                Text(
                    text = code.toString(),
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
            }
        }
        // Handle other cases similarly for other fields
    }

}

fun avg(subjects: List<Subject>): Float {
    var sum : Float = 0.0F
    var index = 0
    while(index<subjects.size){
        sum +=(subjects[index].present_classes.toFloat()/ subjects[index].total_classes.toFloat()) * 100
        index++
    }
    return sum/subjects.size
}

