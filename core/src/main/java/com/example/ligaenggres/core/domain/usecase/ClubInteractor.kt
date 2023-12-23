package com.example.ligaenggres.core.domain.usecase

import com.example.ligaenggres.core.domain.model.Club
import com.example.ligaenggres.core.domain.repository.IClubRepository

class ClubInteractor(private val clubRepository: IClubRepository): ClubUseCase {

    override fun getAllClub() = clubRepository.getAllClub()

    override fun getFavoriteClub() = clubRepository.getFavoriteClub()

    override fun setFavoriteClub(club: Club, state: Boolean) = clubRepository.setFavoriteClub(club, state)
}