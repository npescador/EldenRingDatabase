package com.nachopr.eldenringdatabase.domain.bosses.usecase

import com.nachopr.eldenringdatabase.domain.bosses.BossesRepository
import com.nachopr.eldenringdatabase.model.remote.Boss

class GetBossUseCase(
    private val bossesRepository: BossesRepository
) {

    suspend fun getBosses(): List<Boss> {
        return bossesRepository.getBosses()
    }
}