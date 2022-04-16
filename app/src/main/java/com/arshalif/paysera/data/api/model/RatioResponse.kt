package com.arshalif.paysera.data.api.model

import java.math.BigDecimal

data class RatioResponse(
    val base: String,
    val date: String,
    val rates: HashMap<String, BigDecimal>,
    val success: Boolean,
    val timestamp: Int
)