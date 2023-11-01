package com.nachopr.eldenringdatabase.domain.talismans.usecase

import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.model.remote.Talismans

class GetTalismansUseCase(
    private val talismansRepository: TalismansRepository
) {

    suspend fun getTalismans(): List<Talismans> {
        return talismansRepository.getTalismans()
    }
}