package com.arshalif.paysera.data.db

import com.arshalif.paysera.data.db.daos.BalanceDAOs
import com.arshalif.paysera.data.db.entity.BalanceEntity
import com.arshalif.paysera.data.db.entity.BalanceTransactionEntity
import javax.inject.Inject

class RoomBalance @Inject constructor(private val balanceDAOs: BalanceDAOs) : Balance {

    override suspend fun getBalances(): List<BalanceEntity> {
        return balanceDAOs.fetchAllBalances()
    }

    override suspend fun storeBalances(balances: List<BalanceEntity>) {
        balanceDAOs.insertBalances(balances)
    }

    override suspend fun storeBalance(balance: BalanceEntity) {
        balanceDAOs.insertBalance(balance)
    }

    override suspend fun getBalance(type: String): BalanceEntity {
        return balanceDAOs.fetchBalance(type)
    }

    override suspend fun deleteAll() {
        balanceDAOs.deleteBalances()
    }

    override suspend fun fetchNumberOfTransactions(): Int {
        return balanceDAOs.countBalanceTransaction()
    }

    override suspend fun storeTransaction(transactionEntity: BalanceTransactionEntity) {
        balanceDAOs.insertBalanceTransaction(transactionEntity)
    }
}