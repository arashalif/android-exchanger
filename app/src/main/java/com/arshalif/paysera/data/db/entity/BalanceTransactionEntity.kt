package com.arshalif.paysera.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.arshalif.paysera.data.db.converter.BigDecimalTypeConverter

@Entity
@TypeConverters(BigDecimalTypeConverter::class)
data class BalanceTransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @Embedded val oldBalance: BalanceEntity,
    @Embedded val newBalance: BalanceEntity
)

