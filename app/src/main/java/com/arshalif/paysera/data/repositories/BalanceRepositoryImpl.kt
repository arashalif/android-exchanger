package com.arshalif.paysera.data.repositories

import com.arshalif.paysera.data.db.Balance
import com.arshalif.paysera.data.db.entity.*
import com.arshalif.paysera.domain.model.BalanceCurrency
import com.arshalif.paysera.domain.repositories.BalanceRepository
import com.arshalif.paysera.view.model.ResultState
import dagger.hilt.android.scopes.ActivityRetainedScoped
import java.math.BigDecimal
import javax.inject.Inject

@ActivityRetainedScoped
class BalanceRepositoryImpl @Inject constructor(private val balance: Balance) : BalanceRepository {

    override suspend fun fetchBalance(type: String): ResultState<BalanceCurrency> {
        return ResultState.Success(balance.getBalance(type).toBalance())
    }

    override suspend fun fetchBalances(): ResultState<List<BalanceCurrency>> {
        val response = balance.getBalances().toBalanceList()

//      It's First Time Launch App and starting balance is 1000 Euros (EUR)
        if (response.isNullOrEmpty()) {
            val initialBalance = BalanceEntity("EUR", BigDecimal.valueOf(1000.00))
            balance.storeBalance(initialBalance)
            return ResultState.Success(listOf(initialBalance).toBalanceList())
        }

        return ResultState.Success(balance.getBalances().toBalanceList())
    }

    override suspend fun updateBalances(
        soldBalance: BalanceCurrency,
        boughtBalance: BalanceCurrency
    ): ResultState<List<BalanceCurrency>> {
        balance.storeBalances(
            listOf(
                soldBalance.fromBalance(), boughtBalance.fromBalance()
            )
        )
        return fetchBalances()
    }

    override suspend fun fetchNumberOfTransactions(): ResultState<Int> {
        return ResultState.Success(balance.fetchNumberOfTransactions())
    }

    override suspend fun storeTransaction(
        oldBalance: BalanceCurrency,
        newBalance: BalanceCurrency
    ): ResultState<List<BalanceCurrency>> {
        balance.storeTransaction(
            BalanceTransactionEntity(
                oldBalance = oldBalance.fromBalance(),
                newBalance = newBalance.fromBalance()
            )
        )
        return fetchBalances()
    }
}