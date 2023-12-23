package com.example.ligaenggres.core.data.source.local.room

import androidx.room.*
import com.example.ligaenggres.core.data.source.local.entity.ClubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubDao {

    @Query("SELECT * FROM club")
    fun getAllClub(): Flow<List<ClubEntity>>

    @Query("SELECT * FROM club where isFavorite = 1")
    fun getFavoriteClub(): Flow<List<ClubEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClub(tourism: List<ClubEntity>)

    @Update
    fun updateFavoriteClub(tourism: ClubEntity)
}
