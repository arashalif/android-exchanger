package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.domain.model.BalanceCurrency
import com.arshalif.paysera.domain.repositories.BalanceRepository
import com.arshalif.paysera.view.model.ResultState
import java.math.BigDecimal
import javax.inject.Inject


class SubmitExchangeUseCase @Inject constructor(
    private val commissionUseCase: CommissionUseCase,
    private val balanceRepository: BalanceRepository
) {

    suspend operator fun invoke(
        typeSell: String,
        valueSell: String,
        typeReceive: String,
        valueReceive: String
    ): ResultState<String> {

        val sell = BigDecimal.valueOf(valueSell.toDouble())
        val valueCommission = commissionUseCase(sell)
        val valuePayment = sell + valueCommission
        val sellBalance = when (val fetched = balanceRepository.fetchBalance(typeSell)) {
            is ResultState.Success -> {
                if (fetched.data != null) {
                    fetched.data.value
                } else {
                    return ResultState.Error("Error finding your $typeSell balance, are you sure you have enough in your balance?")
                }
            }
            else -> return ResultState.Error("Error finding balance")
        }
        val receiveBalance = when (val fetched = balanceRepository.fetchBalance(typeReceive)) {
            is ResultState.Success -> {
                if (fetched.data != null) {
                    fetched.data.value
                } else {
                    BigDecimal.valueOf(0.0)
                }
            }
            else -> return ResultState.Error("Error finding balance")
        }

        if (valuePayment > sellBalance) {
            return ResultState.Error("Your balance is not enough")
        }

        when (balanceRepository.storeTransaction(
            BalanceCurrency(typeSell, sellBalance),
            BalanceCurrency(typeSell, sellBalance - valuePayment),
            BalanceCurrency(typeReceive, receiveBalance),
            BalanceCurrency(
                typeReceive,
                receiveBalance + BigDecimal.valueOf(valueReceive.toDouble())
            ),
        )
        ) {
            is ResultState.Success -> {}
            else -> return ResultState.Error("There was a problem updating your transaction. Please try again")
        }

        val response = balanceRepository.updateBalances(
            BalanceCurrency(typeSell, sellBalance - valuePayment),
            BalanceCurrency(
                typeReceive,
                receiveBalance + BigDecimal.valueOf(valueReceive.toDouble())
            )
        )

        return when (response) {
            is ResultState.Success -> ResultState.Success("You have converted $valueSell $typeSell to $valueReceive $typeReceive. Commission Fee - $valueCommission $typeSell.")
            else -> {
                ResultState.Error("There was a problem updating your account. Please try again")
            }

        }
    }
}