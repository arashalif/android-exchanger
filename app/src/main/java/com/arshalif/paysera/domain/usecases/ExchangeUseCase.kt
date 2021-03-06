package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.view.model.CurrencyRatioState
import com.arshalif.paysera.view.model.CurrencyState
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class ExchangeUseCase @Inject constructor() {

    suspend operator fun invoke(
        sell: CurrencyRatioState,
        receiveType: String,
        receiveRatio: BigDecimal,
    ): CurrencyState {

        val newValue = (sell.currencyState.value * receiveRatio) / sell.ratio
        val roundedValue = newValue.setScale(2, RoundingMode.FLOOR)
        return CurrencyState(receiveType, roundedValue)
    }
}