package com.arshalif.paysera.data.repositories

import com.arshalif.paysera.data.db.Balance
import com.arshalif.paysera.data.db.entity.fromBalance
import com.arshalif.paysera.data.db.entity.toBalance
import com.arshalif.paysera.data.db.entity.toBalanceList
import com.arshalif.paysera.domain.model.BalanceCurrency
import com.arshalif.paysera.domain.repositories.BalanceRepository
import com.arshalif.paysera.view.model.ResultState
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class BalanceRepositoryImpl @Inject constructor(private val balance: Balance) : BalanceRepository {

    override suspend fun fetchBalance(type: String): ResultState<BalanceCurrency> {
        return ResultState.Success(balance.getBalance(type).toBalance())
    }

    override suspend fun fetchBalances(): ResultState<List<BalanceCurrency>> {
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
}