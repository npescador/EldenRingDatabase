package com.nachopr.eldenringdatabase.model.remote

import com.google.gson.annotations.SerializedName

data class WeaponsResponse(
    @SerializedName("data") val weapons: List<Weapon>
)

data class WeaponResponse(
    @SerializedName("data") val weapon: Weapon
)

data class Weapon(
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val attack: List<Attack>,
    val defence: List<Defends>,
    val scalesWith: List<ScalesWith>,
    val requiredAttributes: List<RequiredAttributes>,
    val category: String,
    val weight: String
)

data class Attack(
    val name: String,
    val amount: Long
)

data class Defends(
    val name: String,
    val amount: String
)

data class ScalesWith(
    val name: String,
    val scaling: String
)

data class RequiredAttributes(
    val name: String,
    val amount: String
)
