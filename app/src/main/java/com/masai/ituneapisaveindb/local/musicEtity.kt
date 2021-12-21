package com.masai.ituneapisaveindb.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music_db")
data class MusicEntity(
    @ColumnInfo(name = "artistName") val artistName: String,
    @ColumnInfo(name = "artistImageUrl") val artistImageUrl: String
) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int? = null
}