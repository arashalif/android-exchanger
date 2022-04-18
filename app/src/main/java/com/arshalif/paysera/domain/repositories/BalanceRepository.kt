package com.arshalif.paysera.domain.repositories

import com.arshalif.paysera.domain.model.BalanceCurrency
import com.arshalif.paysera.view.model.ResultState

interface BalanceRepository {
    suspend fun fetchBalance(type: String): ResultState<BalanceCurrency>
    suspend fun fetchBalances(): ResultState<List<BalanceCurrency>>
    suspend fun updateBalances(
        soldBalance: BalanceCurrency,
        boughtBalance: BalanceCurrency
    ): ResultState<List<BalanceCurrency>>

    suspend fun fetchNumberOfTransactions(): ResultState<Int>
    suspend fun storeTransaction(
        oldBalance: BalanceCurrency,
        newBalance: BalanceCurrency
    ): ResultState<List<BalanceCurrency>>
}