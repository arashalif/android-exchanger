package com.arshalif.paysera.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class BalanceEntity(
    @PrimaryKey val type: String,
    val value: BigDecimal,
)
