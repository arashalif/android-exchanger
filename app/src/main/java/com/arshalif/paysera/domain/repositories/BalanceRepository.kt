package com.arshalif.paysera.domain.repositories

import com.arshalif.paysera.domain.model.Balance

interface BalanceRepository {
    suspend fun fetchBalance(): Balance
    suspend fun fetchBalances(): List<Balance>
    suspend fun updateBalances(soldBalance: Balance, boughtBalance: Balance): List<Balance>
}