package com.example.ligaenggres.core.data.source.remote.network

import com.example.ligaenggres.core.data.source.remote.response.ListClubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search_all_teams.php")
    suspend fun getList(
        @Query("l") league: String = "English Premier League"
    ): ListClubResponse
}