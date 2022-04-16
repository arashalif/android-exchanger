package com.arshalif.paysera.data.db

import com.arshalif.paysera.data.db.entity.BalanceEntity

class RoomBalance :Balance {
    override suspend fun getBalances(): List<BalanceEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun storeBalances(balances: List<BalanceEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun storeBalance(balance: BalanceEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getBalance(type: String): BalanceEntity {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}