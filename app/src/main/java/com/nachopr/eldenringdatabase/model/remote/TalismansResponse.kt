package com.nachopr.eldenringdatabase.model.remote

import com.google.gson.annotations.SerializedName
import com.nachopr.eldenringdatabase.model.local.Talisman

data class TalismansResponse(
    @SerializedName("data") val talismans: List<Talismans>
)

data class TalismanResponse(
    @SerializedName("data") val talisman: Talismans
)

data class Talismans(
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val effect: String
)



