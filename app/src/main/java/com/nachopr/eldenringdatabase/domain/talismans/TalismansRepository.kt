package com.nachopr.eldenringdatabase.domain.talismans

import com.nachopr.eldenringdatabase.data.local.database.entities.TalismanEntity
import com.nachopr.eldenringdatabase.model.local.Talisman
import com.nachopr.eldenringdatabase.model.remote.Talismans

interface TalismansRepository {

    suspend fun getTalismansFromApi(): List<Talismans>

    suspend fun getTalismanFromApi(id: String): Talismans


    suspend fun getTalismansFromDataBase(): List<Talisman>

    suspend fun getTalismanFromDataBase(id: String): Talisman

    suspend fun insertAllTalismans(talismans:List<TalismanEntity>)
    suspend fun clearAllTalisman()
}