package com.example.ligaenggres.core.data.source.remote

import android.util.Log
import com.example.ligaenggres.core.data.source.remote.network.ApiResponse
import com.example.ligaenggres.core.data.source.remote.network.ApiService
import com.example.ligaenggres.core.data.source.remote.response.ClubResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun getAllClub(): Flow<ApiResponse<List<ClubResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.teams
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.teams))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(dispatcher)
    }
}

