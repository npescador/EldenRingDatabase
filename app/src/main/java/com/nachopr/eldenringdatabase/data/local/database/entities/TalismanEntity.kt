package com.nachopr.eldenringdatabase.data.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nachopr.eldenringdatabase.model.remote.Talismans

@Entity(tableName = "talisman_table")
data class TalismanEntity (
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "effect") val effect: String
)

fun Talismans.toDatabase() = TalismanEntity(id = id, name = name, image = image, description = description, effect = effect)