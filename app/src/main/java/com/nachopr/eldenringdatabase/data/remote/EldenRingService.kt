package com.nachopr.eldenringdatabase.data.remote

import com.nachopr.eldenringdatabase.model.remote.TalismanResponse
import com.nachopr.eldenringdatabase.model.remote.TalismansResponse
import com.nachopr.eldenringdatabase.model.remote.WeaponResponse
import com.nachopr.eldenringdatabase.model.remote.WeaponsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EldenRingService {

    @GET("weapons")
    suspend fun getWeapons(): WeaponsResponse

    @GET("weapons/{id}")
    suspend fun getWeapon(@Path("id") id: String): WeaponResponse

    @GET("talismans")
    suspend fun getTalismans(): TalismansResponse

    @GET("talismans/{id}")
    suspend fun getTalisman(@Path("id") id: String): TalismanResponse
}