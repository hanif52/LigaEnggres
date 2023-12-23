package com.example.ligaenggres.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ClubResponse(

	@field:SerializedName("idTeam")
	val idTeam: String,

	@field:SerializedName("strTeam")
	val strTeam: String,

	@field:SerializedName("strTeamBadge")
	val strTeamBadge: String,

	@field:SerializedName("strStadium")
	val strStadium: String,

	@field:SerializedName("strDescriptionEN")
	val strDescriptionEN: String,
)
