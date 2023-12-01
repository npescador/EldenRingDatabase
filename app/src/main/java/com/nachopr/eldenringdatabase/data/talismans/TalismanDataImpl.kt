package com.nachopr.eldenringdatabase.data.talismans

import com.nachopr.eldenringdatabase.data.local.database.EldenRingDatabase
import com.nachopr.eldenringdatabase.data.local.database.entities.TalismanEntity
import com.nachopr.eldenringdatabase.data.talismans.remote.TalismansRemoteImpl
import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.model.local.Talisman
import com.nachopr.eldenringdatabase.model.local.toDomain
import com.nachopr.eldenringdatabase.model.remote.Talismans

class TalismanDataImpl(
    private val talismansRemoteImpl: TalismansRemoteImpl,
    private val eldenRingDatabase: EldenRingDatabase
) : TalismansRepository {

    override suspend fun getTalismansFromApi(): List<Talismans> {
        return talismansRemoteImpl.getTalismans()
    }

    override suspend fun getTalismanFromApi(id: String): Talismans {
        return talismansRemoteImpl.getTalisman(id)
    }

    override suspend fun getTalismansFromDataBase(): List<Talisman> {
        val talismansFromDatabase: List<TalismanEntity> = eldenRingDatabase.talismanDao().getAllTalisman()
        return talismansFromDatabase.map { it.toDomain() }

    }

    override suspend fun getTalismanFromDataBase(id: String): Talisman {
        val talismanFromDatabase: TalismanEntity = eldenRingDatabase.talismanDao().getTalisman(id)
        return talismanFromDatabase.toDomain()
    }

    override suspend fun insertAllTalismans(talismans: List<TalismanEntity>) {
        return eldenRingDatabase.talismanDao().insertAllTalisman(talismans)
    }

    override suspend fun clearAllTalisman() {
        eldenRingDatabase.talismanDao().deleteAllTalisman()
    }
}