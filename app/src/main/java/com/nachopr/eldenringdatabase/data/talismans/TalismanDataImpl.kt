package com.nachopr.eldenringdatabase.data.talismans

import com.nachopr.eldenringdatabase.data.talismans.remote.TalismansRemoteImpl
import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.model.remote.Talismans

class TalismanDataImpl(
    private val talismansRemoteImpl: TalismansRemoteImpl
): TalismansRepository {

    override suspend fun getTalismans(): List<Talismans> {
        return talismansRemoteImpl.getTalismans()
    }

    override suspend fun getTalisman(id: String): Talismans {
        return talismansRemoteImpl.getTalisman(id)
    }
}