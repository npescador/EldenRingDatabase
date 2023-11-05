package com.nachopr.eldenringdatabase.domain.bosses

import com.nachopr.eldenringdatabase.model.remote.Boss

interface BossesRepository {

    suspend fun getBosses(): List<Boss>

    suspend fun getBoss(id: String): Boss
}