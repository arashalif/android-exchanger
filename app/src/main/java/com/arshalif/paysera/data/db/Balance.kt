package com.arshalif.paysera.data.db

import com.arshalif.paysera.data.db.entity.BalanceEntity

interface Balance {

    suspend fun getBalances(): List<BalanceEntity>
    suspend fun storeBalances(balances: List<BalanceEntity>)
    suspend fun storeBalance(balance: BalanceEntity)
    suspend fun getBalance(type: String): BalanceEntity
    suspend fun deleteAll()
}