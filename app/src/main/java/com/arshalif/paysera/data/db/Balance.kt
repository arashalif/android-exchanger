package com.arshalif.paysera.data.db

import com.arshalif.paysera.data.db.entity.BalanceEntity
import com.arshalif.paysera.data.db.entity.BalanceTransactionEntity

interface Balance {

    suspend fun getBalances(): List<BalanceEntity>
    suspend fun storeBalances(balances: List<BalanceEntity>)
    suspend fun storeBalance(balance: BalanceEntity)
    suspend fun getBalance(type: String): BalanceEntity
    suspend fun deleteAll()

    suspend fun fetchNumberOfTransactions(): Int
    suspend fun storeTransaction(transactionEntity: BalanceTransactionEntity)
}