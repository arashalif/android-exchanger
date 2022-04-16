package com.arshalif.paysera.data.db

import androidx.room.Database
import com.arshalif.paysera.data.db.entity.BalanceEntity


@Database(
    entities = [BalanceEntity::class],
    version = 1
)
abstract class BalanceDataBase {
}