package com.example.ligaenggres.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club")
data class ClubEntity(
    @PrimaryKey
    @ColumnInfo(name = "clubId")
    var clubId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "stadium")
    val stadium: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
