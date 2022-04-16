package com.arshalif.paysera.view.model

sealed interface ResultState<out DATA_TYPE> {
    class Success<DATA_TYPE>(val data: DATA_TYPE) : ResultState<DATA_TYPE>
    class Error(val message: String) : ResultState<Nothing>
    object Loading : ResultState<Nothing>
}