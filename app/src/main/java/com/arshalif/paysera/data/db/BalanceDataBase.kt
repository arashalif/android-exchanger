package com.arshalif.paysera.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arshalif.paysera.data.db.daos.BalanceDAOs
import com.arshalif.paysera.data.db.entity.BalanceEntity


@Database(
    entities = [BalanceEntity::class],
    version = 1
)
abstract class BalanceDataBase : RoomDatabase() {
    abstract fun balancesDao(): BalanceDAOs

}