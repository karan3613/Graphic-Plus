package com.example.graphiiceraapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.graphiiceraapp.ui.theme.LightGreen
import com.example.graphiiceraapp.ui.theme.LightPink
import com.example.graphiiceraapp.ui.theme.LightYellow
import com.example.graphiiceraapp.viewmodels.ProfileViewModel
import com.example.graphiiceraapp.viewmodels.UserViewModel


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel  = hiltViewModel()
){
    val profileState = viewModel.profileState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        profileState.profile?.let {profile->
            UserImageComponent(profile.image)
            val name : String = (profile.firstname)+ " " + (profile.middlename)+ " " +(profile.lastname)
            UserInfoComponent("Name" , LightPink , name)
            UserInfoComponent(Info = "Father Name", rowColor = LightGreen, UserInfo = profile.fathername)
            UserInfoComponent(Info = "Univ Roll No", rowColor = LightYellow , UserInfo = profile.univ_rollNo)
            UserInfoComponent(Info = "Branch", rowColor = LightPink , UserInfo = profile.branch )
            UserInfoComponent(Info = "Course", rowColor = LightGreen, UserInfo = profile.course)
            UserInfoComponent(Info = "Section", rowColor = LightYellow, UserInfo = profile.section)
            UserInfoComponent(Info = "Roll No", rowColor = LightPink , UserInfo = profile.rollNo )
            UserInfoComponent(Info = "Email", rowColor = LightGreen, UserInfo = profile.email )
            UserInfoComponent(Info = "DOB", rowColor = LightYellow , UserInfo = profile.dob)
            UserInfoComponent(Info = "Grade 10", rowColor = LightPink, UserInfo = profile.grade10 )
            UserInfoComponent(Info = "Grade 12", rowColor = LightGreen , UserInfo =  profile.grade12)
        }
        if(profileState.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                color = Color.Red ,
                strokeCap = StrokeCap.Round ,
                strokeWidth = 5.dp
            )
        }
        if(profileState.error.isNotBlank()){
            Text(modifier = Modifier.fillMaxSize(),
                text = profileState.error ,
                fontSize = 20.sp ,
                color = Color.Red ,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
        }

    }

}

@Composable
fun UserInfoComponent(Info: String, rowColor: Color, UserInfo: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(rowColor)
            .padding(start = 30.dp)
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "$Info :",
            color = Color.Black ,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = UserInfo,
            color = Color.Black ,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal
        )

    }


}

@Composable
fun UserImageComponent(s: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier.size(180.dp),
            shape = CircleShape
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = s,
                contentDescription = "User Image" ,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}
