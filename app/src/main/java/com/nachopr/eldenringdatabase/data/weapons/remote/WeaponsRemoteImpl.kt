package com.nachopr.eldenringdatabase.data.weapons.remote

import com.nachopr.eldenringdatabase.data.remote.EldenRingService
import com.nachopr.eldenringdatabase.model.remote.Weapon
import com.nachopr.eldenringdatabase.model.remote.WeaponsResponse

class WeaponsRemoteImpl(
    private val eldenRingService: EldenRingService
) {

    suspend fun getWeapons(): WeaponsResponse {
        return eldenRingService.getWeapons()
    }

    suspend fun getWeapon(id: String): Weapon {
        return eldenRingService.getWeapon(id).weapon
    }
}