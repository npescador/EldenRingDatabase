package com.nachopr.eldenringdatabase.domain.talismans.usecase

import com.nachopr.eldenringdatabase.data.local.database.entities.toDatabase
import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.model.local.Talisman
import com.nachopr.eldenringdatabase.model.local.toDomain

class GetTalismansUseCase(
    private val talismansRepository: TalismansRepository
) {
    suspend operator fun invoke(): List<Talisman> {
        val talismans = talismansRepository.getTalismansFromApi()

        return if(talismans.isNotEmpty()) {
            talismansRepository.clearAllTalisman()
            talismansRepository.insertAllTalismans(talismans.map { it.toDatabase() })
            talismans.map { it.toDomain() }
        } else {
            talismansRepository.getTalismansFromDataBase()
        }
    }
}