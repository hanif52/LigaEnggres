package com.example.ligaenggres.core.domain.repository

import com.example.ligaenggres.core.data.Resource
import com.example.ligaenggres.core.domain.model.Club
import kotlinx.coroutines.flow.Flow

interface IClubRepository {

    fun getAllClub(): Flow<Resource<List<Club>>>

    fun getFavoriteClub(): Flow<List<Club>>

    fun setFavoriteClub(club: Club, state: Boolean)

}