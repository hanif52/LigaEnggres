package com.example.ligaenggres.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListtClubResponse(

	@field:SerializedName("teams")
	val teams: List<TeamsItem>
)