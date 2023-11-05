package com.nachopr.eldenringdatabase.data.bosses.remote

import com.nachopr.eldenringdatabase.data.remote.EldenRingService
import com.nachopr.eldenringdatabase.model.remote.Boss
import com.nachopr.eldenringdatabase.model.remote.BossResponse
import com.nachopr.eldenringdatabase.model.remote.BossesResponse
import com.nachopr.eldenringdatabase.model.remote.Weapon
import com.nachopr.eldenringdatabase.model.remote.WeaponsResponse

class BossesRemoteImpl(
    private val eldenRingService: EldenRingService
) {

    suspend fun getBosses(): BossesResponse {
        return eldenRingService.getBosses()
    }

    suspend fun getWeapon(id: String): Boss {
        return eldenRingService.getBoss(id).boss
    }
}