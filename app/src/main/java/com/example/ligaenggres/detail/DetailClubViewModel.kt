package com.example.ligaenggres.detail

import androidx.lifecycle.ViewModel
import com.example.ligaenggres.core.domain.model.Club
import com.example.ligaenggres.core.domain.usecase.ClubUseCase

class DetailClubViewModel(private val clubUseCase: ClubUseCase) : ViewModel() {
    fun setFavoriteClub(club: Club, newStatus:Boolean) =
        clubUseCase.setFavoriteClub(club, newStatus)

}

