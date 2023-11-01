package com.nachopr.eldenringdatabase.domain.talismans

import com.nachopr.eldenringdatabase.model.remote.Talismans

interface TalismansRepository {

    suspend fun getTalismans(): List<Talismans>

    suspend fun getTalisman(id: String): Talismans
}