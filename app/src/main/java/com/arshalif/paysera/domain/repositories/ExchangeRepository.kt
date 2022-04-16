package com.arshalif.paysera.domain.repositories

import com.arshalif.paysera.domain.model.Balance

interface ExchangeRepository {
    suspend fun fetchBalances(): List<Balance>
    suspend fun updateBalances(soldBalance: Balance, boughtBalance: Balance): List<Balance>
}