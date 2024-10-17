package com.example.gameapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class EntityGame(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "thumbnail") val thumbnail: String? = null,
    @ColumnInfo(name = "short_description") val short_description: String? = null,
    @ColumnInfo(name = "game_url") val game_url: String? = null,
    @ColumnInfo(name = "genre") val genre: String? = null,
    @ColumnInfo(name = "platform") val platform: String? = null,
    @ColumnInfo(name = "publisher") val publisher: String? = null,
    @ColumnInfo(name = "developer") val developer: String? = null,
    @ColumnInfo(name = "release_date") val release_date: String? = null,
    @ColumnInfo(name = "freetogame_profile_url") val freetogame_profile_url: String? = null
)