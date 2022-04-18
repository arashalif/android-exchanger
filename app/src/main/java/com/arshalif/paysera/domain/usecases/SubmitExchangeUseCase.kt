package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.view.model.CurrencyRatioState
import com.arshalif.paysera.view.model.CurrencyState
import java.math.BigDecimal
import javax.inject.Inject

//todo : commision usecase must be inject
class SubmitExchangeUseCase @Inject constructor() {

    suspend operator fun invoke(
        sell: CurrencyRatioState,
        receiveType: String,
        receiveRatio: BigDecimal,
    ): CurrencyState {

        val newValue = (sell.currencyState.value * receiveRatio) / sell.ratio

        return CurrencyState(receiveType, newValue)
    }
}