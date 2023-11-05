package com.nachopr.eldenringdatabase.domain.weapons

import com.nachopr.eldenringdatabase.model.remote.Weapon

interface WeaponRepository {

    suspend fun getWeapons(): List<Weapon>

    suspend fun getWeapon(id: String): Weapon
}