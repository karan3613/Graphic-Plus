package com.example.graphiiceraapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.graphiiceraapp.Constants.UNIVID
import com.example.graphiiceraapp.Data.models.Community
import com.example.graphiiceraapp.viewmodels.CommunityViewModel
import com.example.graphiiceraapp.viewmodels.UserViewModel

@Composable
fun ClubScreen(viewModel: CommunityViewModel = hiltViewModel()) {
    val communityState = viewModel.communitiesState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        communityState.communities?.let {communities->
            val community : Community = communities.get(UNIVID.communityIndex)
            Text(
                text = community.club_name ,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )
            Text(
                text = "(${community.club_description})",
                fontSize = 20.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp))
            Text(
                text  = community.event_name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )
            Text(
                text = community.event_description,
                fontSize = 15.sp,
                modifier = Modifier.padding(top = 15.dp),
                color = Color.Black
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))
            LinksComponent(link = "Click here to register :", info = community.form_link)
            LinksComponent(link = "Connect on Instagram", info = community.social_media_link)
            LinksComponent(link = "Connect on What'sApp", info = community.extra_link)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))
        }
        }


}



@Composable
fun LinksComponent(link : String , info : String){
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = link,
        fontSize = 15.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(5.dp))
    Text(
        text = info ,
        color = Color.Blue
    )
}