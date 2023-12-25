package com.example.ligaenggres.core.data

import com.example.ligaenggres.core.data.source.local.LocalDataSource
import com.example.ligaenggres.core.data.source.remote.RemoteDataSource
import com.example.ligaenggres.core.data.source.remote.network.ApiResponse
import com.example.ligaenggres.core.domain.repository.IClubRepository
import com.example.ligaenggres.core.utils.AppExecutors
import com.example.ligaenggres.core.utils.DataMapper
import com.example.ligaenggres.core.data.source.remote.response.ClubResponse
import com.example.ligaenggres.core.domain.model.Club
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IClubRepository {

    override fun getAllClub(): Flow<Resource<List<Club>>> =
        object : NetworkBoundResource<List<Club>, List<ClubResponse>>() {
            override fun loadFromDB(): Flow<List<Club>> {
                return localDataSource.getAllClub().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Club>?): Boolean = data.isNullOrEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<ClubResponse>>> =
                remoteDataSource.getAllClub()

            override suspend fun saveCallResult(data: List<ClubResponse>) {
                val clubList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertClub(clubList)
            }
        }.asFlow()

    override fun getFavoriteClub(): Flow<List<Club>> {
        return localDataSource.getFavoriteClub().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteClub(club: Club, state: Boolean) {
        val clubEntity = DataMapper.mapDomainToEntity(club)
        appExecutors.diskIO().execute { localDataSource.setFavoriteClub(clubEntity, state) }
    }
}

