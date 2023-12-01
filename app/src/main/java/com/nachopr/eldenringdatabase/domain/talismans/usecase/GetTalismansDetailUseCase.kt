package com.nachopr.eldenringdatabase.domain.talismans.usecase

import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.model.local.Talisman
import com.nachopr.eldenringdatabase.model.local.toDomain

class GetTalismansDetailUseCase(
    private val talismansRepository: TalismansRepository
) {
    suspend operator fun invoke(id: String): Talisman {

        return talismansRepository.getTalismanFromDataBase(id)
            ?: talismansRepository.getTalismanFromApi(id).toDomain()
    }
}