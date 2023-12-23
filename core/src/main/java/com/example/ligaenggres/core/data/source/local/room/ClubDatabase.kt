package com.example.ligaenggres.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ligaenggres.core.data.source.local.entity.ClubEntity

@Database(entities = [ClubEntity::class], version = 1, exportSchema = false)
abstract class ClubDatabase : RoomDatabase() {

    abstract fun clubDao(): ClubDao

}