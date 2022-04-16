package com.arshalif.paysera.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arshalif.paysera.domain.model.BalanceCurrency
import java.math.BigDecimal

@Entity
data class BalanceEntity(
    @PrimaryKey val type: String,
    val value: BigDecimal,
)

fun BalanceEntity.toBalance() =
    BalanceCurrency(type, value)

fun List<BalanceEntity>.toBalanceList() = map {
    it.toBalance()
}

fun BalanceCurrency.fromBalance() =
    BalanceEntity(type, value)

fun List<BalanceCurrency>.fromBalanceList() = map {
    it.fromBalance()
}
