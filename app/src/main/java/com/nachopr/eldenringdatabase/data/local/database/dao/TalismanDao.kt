package com.nachopr.eldenringdatabase.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nachopr.eldenringdatabase.data.local.database.entities.TalismanEntity


@Dao
interface TalismanDao {

    @Query("SELECT * FROM talisman_table ORDER BY name DESC")
    suspend fun getAllTalisman():List<TalismanEntity>

    @Query("SELECT * FROM talisman_table WHERE id = :id")
    suspend fun getTalisman(id: String):TalismanEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTalisman(quotes:List<TalismanEntity>)

    @Query("DELETE FROM talisman_table")
    suspend fun deleteAllTalisman()
}