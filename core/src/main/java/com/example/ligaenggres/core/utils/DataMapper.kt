package com.example.ligaenggres.core.utils

import com.example.ligaenggres.core.data.source.local.entity.ClubEntity
import com.example.ligaenggres.core.data.source.remote.response.ClubResponse
import com.example.ligaenggres.core.domain.model.Club

object DataMapper {
    fun mapResponsesToEntities(input: List<ClubResponse>): List<ClubEntity> {
        val clubList = ArrayList<ClubEntity>()
        input.map {
            val club = ClubEntity(
                clubId = it.idTeam,
                description = it.strDescriptionEN,
                name = it.strTeam,
                stadium = it.strStadium,
                image = it.strTeamBadge
            )
            clubList.add(club)
        }
        return clubList
    }

    fun mapEntitiesToDomain(input: List<ClubEntity>): List<Club> =
        input.map {
            Club(
                clubId = it.clubId,
                description = it.description,
                name = it.name,
                stadium = it.stadium,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Club) = ClubEntity(
        clubId = input.clubId,
        description = input.description,
        name = input.name,
        stadium = input.stadium,
        image = input.image
    )
}