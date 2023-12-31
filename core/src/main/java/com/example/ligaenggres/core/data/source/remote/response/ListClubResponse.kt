package com.example.ligaenggres.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListClubResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,
    
    @field:SerializedName("teams")
    val teams: List<ClubResponse>
)