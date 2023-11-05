package com.nachopr.eldenringdatabase.di

import com.nachopr.eldenringdatabase.data.remote.ApiClient
import com.nachopr.eldenringdatabase.data.remote.EldenRingService
import com.nachopr.eldenringdatabase.data.talismans.TalismanDataImpl
import com.nachopr.eldenringdatabase.data.talismans.remote.TalismansRemoteImpl
import com.nachopr.eldenringdatabase.data.weapons.WeaponDataImpl
import com.nachopr.eldenringdatabase.data.weapons.remote.WeaponsRemoteImpl
import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.domain.talismans.usecase.GetTalismansDetailUseCase
import com.nachopr.eldenringdatabase.domain.talismans.usecase.GetTalismansUseCase
import com.nachopr.eldenringdatabase.domain.weapons.WeaponRepository
import com.nachopr.eldenringdatabase.domain.weapons.usecase.GetWeaponDetailUseCase
import com.nachopr.eldenringdatabase.domain.weapons.usecase.GetWeaponsUseCase
import com.nachopr.eldenringdatabase.view.talismans.TalismanViewModel
import com.nachopr.eldenringdatabase.view.weapons.WeaponViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseModule = module {
    single<EldenRingService> { ApiClient.retrofit.create(EldenRingService::class.java)}
}

val talismanModule = module {
    factory { TalismansRemoteImpl( get()) }
    factory<TalismansRepository> { TalismanDataImpl(get()) }
    factory { GetTalismansUseCase(get()) }
    factory { GetTalismansDetailUseCase(get()) }

    viewModel { TalismanViewModel(get(), get()) }
}

val weaponModule = module {
    factory { WeaponsRemoteImpl( get()) }
    factory<WeaponRepository> { WeaponDataImpl(get()) }
    factory { GetWeaponsUseCase(get()) }
    factory { GetWeaponDetailUseCase(get()) }

    viewModel { WeaponViewModel(get(), get()) }
}

