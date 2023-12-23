package com.example.ligaenggres.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Club(
    val clubId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "stadium")
    val stadium: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
) : Parcelable