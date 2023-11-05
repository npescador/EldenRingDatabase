package com.nachopr.eldenringdatabase.domain.bosses.usecase

import com.nachopr.eldenringdatabase.domain.bosses.BossesRepository
import com.nachopr.eldenringdatabase.model.remote.Boss

class GetBossesDetailUseCase(
    private val bossesRepository: BossesRepository
) {
    suspend fun getBossDetail(id: String): Boss {
        return bossesRepository.getBoss(id)
    }
}