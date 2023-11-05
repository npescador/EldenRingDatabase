package com.nachopr.eldenringdatabase.data.bosses

import com.nachopr.eldenringdatabase.data.talismans.remote.TalismansRemoteImpl
import com.nachopr.eldenringdatabase.domain.bosses.BossesRepository
import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.model.remote.Boss
import com.nachopr.eldenringdatabase.model.remote.Talismans

class BossDataImpl(
    private val bossesRepository: BossesRepository
): BossesRepository {
    override suspend fun getBosses(): List<Boss> {
        return bossesRepository.getBosses()
    }

    override suspend fun getBoss(id: String): Boss {
        return bossesRepository.getBoss(id)
    }

}