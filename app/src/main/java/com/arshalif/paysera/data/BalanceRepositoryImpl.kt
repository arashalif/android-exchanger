package com.arshalif.paysera.data

import com.arshalif.paysera.data.db.Balance
import com.arshalif.paysera.data.db.entity.fromBalance
import com.arshalif.paysera.data.db.entity.toBalance
import com.arshalif.paysera.data.db.entity.toBalanceList
import com.arshalif.paysera.domain.model.BalanceCurrency
import com.arshalif.paysera.domain.repositories.BalanceRepository
import javax.inject.Inject

class BalanceRepositoryImpl @Inject constructor(private val balance: Balance) : BalanceRepository {

    override suspend fun fetchBalance(type: String): BalanceCurrency {
        return balance.getBalance(type).toBalance()
    }

    override suspend fun fetchBalances(): List<BalanceCurrency> {
        return balance.getBalances().toBalanceList()
    }

    override suspend fun updateBalances(
        soldBalance: BalanceCurrency,
        boughtBalance: BalanceCurrency
    ): List<BalanceCurrency> {
        balance.storeBalances(
            listOf(
                soldBalance.fromBalance(), boughtBalance.fromBalance()
            )
        )
        return fetchBalances()
    }
}