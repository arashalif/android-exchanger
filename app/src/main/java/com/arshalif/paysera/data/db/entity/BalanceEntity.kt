package com.arshalif.paysera.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arshalif.paysera.domain.model.Balance
import java.math.BigDecimal

@Entity
data class BalanceEntity(
    @PrimaryKey val type: String,
    val value: BigDecimal,
)

fun BalanceEntity.toBalance() =
    Balance(type, value)

fun List<BalanceEntity>.toVenueList() = map {
    it.toBalance()
}
