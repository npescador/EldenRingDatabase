package com.nachopr.eldenringdatabase.model.remote

import com.google.gson.annotations.SerializedName

data class BossesResponse(
    @SerializedName("data") val bosses: List<Boss>
)

data class BossResponse(
    @SerializedName("data") val boss: Boss
)

data class Boss(
    val id: String,
    val name: String,
    val image: String? = null,
    val region: String,
    val description: String,
    val location: String,
    val drops: List<String>,
    val healthPoints: String
)