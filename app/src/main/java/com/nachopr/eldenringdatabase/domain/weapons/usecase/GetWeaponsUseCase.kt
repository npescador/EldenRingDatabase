package com.nachopr.eldenringdatabase.domain.weapons.usecase

import com.nachopr.eldenringdatabase.domain.weapons.WeaponRepository
import com.nachopr.eldenringdatabase.model.remote.Weapon

class GetWeaponsUseCase(
    private val weaponRepository: WeaponRepository
) {

    suspend fun getWeapons(): List<Weapon> {
        return weaponRepository.getWeapons()
    }
}