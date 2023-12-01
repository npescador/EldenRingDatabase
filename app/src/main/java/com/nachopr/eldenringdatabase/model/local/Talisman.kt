package com.nachopr.eldenringdatabase.model.local

import com.nachopr.eldenringdatabase.data.local.database.entities.TalismanEntity
import com.nachopr.eldenringdatabase.model.remote.Talismans

data class Talisman(
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val effect: String
)

fun TalismanEntity.toDomain() = Talisman(id, name, image, description, effect)
fun Talismans.toDomain() = Talisman(id,name, image, description,effect)