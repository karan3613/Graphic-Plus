package com.example.graphiiceraapp.Data.models

data class Community(
    val banner: String,
    val club_description: String,
    val club_name: String,
    val event_description: String,
    val event_name: String,
    val extra_link: String,
    val form_link: String,
    val id: String,
    val mainVideo: String,
    val members: List<Member>,
    val qr_image: String,
    val social_media_link: String,
    val whatsapp_group: String
)