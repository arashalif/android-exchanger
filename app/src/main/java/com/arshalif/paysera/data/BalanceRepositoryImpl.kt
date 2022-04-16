package com.arshalif.paysera.data

import com.arshalif.paysera.domain.model.Balance
import com.arshalif.paysera.domain.repositories.BalanceRepository

class BalanceRepositoryImpl : BalanceRepository {

    override suspend fun fetchBalance(): Balance {
        TODO("Not yet implemented")
    }

    override suspend fun fetchBalances(): List<Balance> {
        TODO("Not yet implemented")
    }

    override suspend fun updateBalances(
        soldBalance: Balance,
        boughtBalance: Balance
    ): List<Balance> {
        TODO("Not yet implemented")
    }
}