package com.nachopr.eldenringdatabase.data.weapons

import com.nachopr.eldenringdatabase.data.talismans.remote.TalismansRemoteImpl
import com.nachopr.eldenringdatabase.data.weapons.remote.WeaponsRemoteImpl
import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.domain.weapons.WeaponRepository
import com.nachopr.eldenringdatabase.model.remote.Talismans
import com.nachopr.eldenringdatabase.model.remote.Weapon
import com.nachopr.eldenringdatabase.model.remote.WeaponsResponse

class WeaponDataImpl(
    private val weaponsRemoteImpl: WeaponsRemoteImpl
): WeaponRepository {

    override suspend fun getWeapons(): List<Weapon> {
        return weaponsRemoteImpl.getWeapons().weapons
    }

    override suspend fun getWeapon(id: String): Weapon {
        return weaponsRemoteImpl.getWeapon(id)
    }
}