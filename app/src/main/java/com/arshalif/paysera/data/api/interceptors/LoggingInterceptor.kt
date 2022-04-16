package com.arshalif.paysera.data.api.interceptors

import com.arshalif.paysera.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        if (BuildConfig.DEBUG) {
            Timber.e(message)
        }
    }
}