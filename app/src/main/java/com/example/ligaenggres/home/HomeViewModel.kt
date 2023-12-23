package com.example.ligaenggres.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ligaenggres.core.domain.usecase.ClubUseCase

class HomeViewModel(clubUseCase: ClubUseCase) : ViewModel() {
    val club = clubUseCase.getAllClub().asLiveData()
}