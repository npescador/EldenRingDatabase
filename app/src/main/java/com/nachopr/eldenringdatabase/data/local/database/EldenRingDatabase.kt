package com.nachopr.eldenringdatabase.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nachopr.eldenringdatabase.data.local.database.dao.TalismanDao
import com.nachopr.eldenringdatabase.data.local.database.entities.TalismanEntity

@Database(entities = [TalismanEntity::class], version = 1)
abstract class EldenRingDatabase: RoomDatabase() {

    abstract fun talismanDao(): TalismanDao
}