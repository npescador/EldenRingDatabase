package com.nachopr.eldenringdatabase.domain.weapons.usecase

import com.nachopr.eldenringdatabase.domain.weapons.WeaponRepository
import com.nachopr.eldenringdatabase.model.remote.Weapon

class GetWeaponDetailUseCase(
    private val weaponRepository: WeaponRepository
) {
    suspend fun getWeaponDetail(id: String): Weapon {
        return weaponRepository.getWeapon(id)
    }
}