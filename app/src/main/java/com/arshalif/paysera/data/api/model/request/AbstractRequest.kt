package com.arshalif.paysera.data.api.model.request

import com.arshalif.paysera.data.api.ApiConstants
import java.util.HashMap

abstract class AbstractRequest {
    protected abstract fun parameters(): HashMap<String, String>

    fun getParamsMap(): HashMap<String, String> {
        return parameters().apply {
            put("access_key", ApiConstants.API_KEY)
        }
    }
}