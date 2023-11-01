package com.nachopr.eldenringdatabase.data.talismans.remote

import com.nachopr.eldenringdatabase.data.remote.EldenRingService
import com.nachopr.eldenringdatabase.model.remote.Talismans

class TalismansRemoteImpl(
    private val eldenRingService: EldenRingService
) {

    suspend fun getTalismans(): List<Talismans> {
        return eldenRingService.getTalismans().talismans
    }

    suspend fun getTalisman(id: String): Talismans {
        return eldenRingService.getTalisman(id).talisman
    }
}