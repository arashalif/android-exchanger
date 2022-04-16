package com.arshalif.paysera.domain.repositories

import com.arshalif.paysera.domain.model.BalanceCurrency

interface BalanceRepository {
    suspend fun fetchBalance(type:String): BalanceCurrency
    suspend fun fetchBalances(): List<BalanceCurrency>
    suspend fun updateBalances(soldBalance: BalanceCurrency, boughtBalance: BalanceCurrency): List<BalanceCurrency>
}