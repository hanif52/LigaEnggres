package com.example.ligaenggres.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ligaenggres.core.domain.usecase.ClubUseCase

class FavoriteViewModel(clubUseCase: ClubUseCase) : ViewModel() {
    val favoriteClub = clubUseCase.getFavoriteClub().asLiveData()
}

