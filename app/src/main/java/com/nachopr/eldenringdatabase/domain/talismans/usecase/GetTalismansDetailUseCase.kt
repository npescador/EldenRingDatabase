package com.nachopr.eldenringdatabase.domain.talismans.usecase

import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.model.remote.Talismans

class GetTalismansDetailUseCase(
    private val talismansRepository: TalismansRepository
) {
    suspend fun getTalismanDetail(id: String): Talismans {
        return talismansRepository.getTalisman(id)
    }
}