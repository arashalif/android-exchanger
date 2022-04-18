package com.arshalif.paysera.domain.repositories

import com.arshalif.paysera.domain.model.BalanceCurrency
import com.arshalif.paysera.view.model.ResultState

interface BalanceRepository {
    suspend fun fetchBalance(type: String): ResultState<BalanceCurrency?>
    suspend fun fetchBalances(): ResultState<List<BalanceCurrency>>
    suspend fun updateBalances(
        soldBalance: BalanceCurrency,
        boughtBalance: BalanceCurrency
    ): ResultState<List<BalanceCurrency>>

    suspend fun fetchNumberOfTransactions(): Int
    suspend fun storeTransaction(
        sellOldBalance: BalanceCurrency,
        sellNewBalance: BalanceCurrency,
        receiveOldBalance: BalanceCurrency,
        receiveNewBalance: BalanceCurrency,
    ): ResultState<List<BalanceCurrency>>
}