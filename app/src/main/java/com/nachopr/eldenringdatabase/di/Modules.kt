package com.nachopr.eldenringdatabase.di

import com.nachopr.eldenringdatabase.data.remote.ApiClient
import com.nachopr.eldenringdatabase.data.remote.EldenRingService
import com.nachopr.eldenringdatabase.data.talismans.TalismanDataImpl
import com.nachopr.eldenringdatabase.data.talismans.remote.TalismansRemoteImpl
import com.nachopr.eldenringdatabase.domain.talismans.TalismansRepository
import com.nachopr.eldenringdatabase.domain.talismans.usecase.GetTalismansDetailUseCase
import com.nachopr.eldenringdatabase.domain.talismans.usecase.GetTalismansUseCase
import com.nachopr.eldenringdatabase.view.talismans.TalismanViewModel
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