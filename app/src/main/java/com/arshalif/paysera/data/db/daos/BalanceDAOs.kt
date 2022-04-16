package com.arshalif.paysera.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arshalif.paysera.data.db.entity.BalanceEntity

@Dao
interface BalanceDAOs {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalances(balances: List<BalanceEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalance(balance: BalanceEntity)

    @Query("SELECT * FROM BalanceEntity")
    suspend fun fetchAllBalances(
    ): List<BalanceEntity>

    @Query("SELECT * FROM BalanceEntity WHERE type IS :type")
    suspend fun fetchBalance(type: String): BalanceEntity

    @Query("Delete FROM BalanceEntity")
    suspend fun deleteBalances(): Int
}