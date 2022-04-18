package com.arshalif.paysera.domain.usecases

import com.arshalif.paysera.view.model.CurrencyRatioState
import com.arshalif.paysera.view.model.CurrencyState
import java.math.BigDecimal
import javax.inject.Inject


class SubmitExchangeUseCase @Inject constructor(val commissionUseCase: CommissionUseCase) {

    suspend operator fun invoke(
        typeSell: String,
        valueSell: String,
        typeReceive: String,
        valueReceive: String
    ) {

//        val newValue = (sell.currencyState.value * receiveRatio) / sell.ratio
//
//        return CurrencyState(receiveType, newValue)
    }
}