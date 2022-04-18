package com.arshalif.paysera.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arshalif.paysera.data.db.daos.BalanceDAOs
import com.arshalif.paysera.data.db.entity.BalanceEntity
import com.arshalif.paysera.data.db.entity.BalanceTransactionEntity


@Database(
    entities = [BalanceEntity::class, BalanceTransactionEntity::class],
    version = 2
)
abstract class BalanceDataBase : RoomDatabase() {
    abstract fun balancesDao(): BalanceDAOs
}