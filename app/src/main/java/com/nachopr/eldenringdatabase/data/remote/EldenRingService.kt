package com.nachopr.eldenringdatabase.data.remote

import com.nachopr.eldenringdatabase.model.remote.Boss
import com.nachopr.eldenringdatabase.model.remote.TalismanResponse
import com.nachopr.eldenringdatabase.model.remote.Talismans
import com.nachopr.eldenringdatabase.model.remote.TalismansResponse
import com.nachopr.eldenringdatabase.model.remote.Weapon
import retrofit2.http.GET
import retrofit2.http.Path

interface EldenRingService {

    @GET("weapons")
    suspend fun getWeapons(): List<Weapon>

    @GET("talismans")
    suspend fun getTalismans(): TalismansResponse

    @GET("talismans/{id}")
    suspend fun getTalisman(@Path("id") id: String): TalismanResponse

    @GET("bosses")
    suspend fun getBosses(): List<Boss>
}