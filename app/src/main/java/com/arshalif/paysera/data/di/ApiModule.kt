package com.arshalif.paysera.data.di

import com.arshalif.paysera.data.api.ApiConstants
import com.arshalif.paysera.data.api.RatiosApi
import com.arshalif.paysera.data.api.interceptors.LoggingInterceptor
import com.google.gson.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient, gson: Gson): RatiosApi {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RatiosApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(loggingInterceptor)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(
            Date::class.java,
            JsonDeserializer<Date> { json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext? ->
                if (json.asString.isEmpty()) null
                try {
                    df.parse(json.asString)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            })
        gsonBuilder.registerTypeAdapter(
            Date::class.java,
            JsonSerializer { src: Date?, typeOfSrc: Type?, context: JsonSerializationContext? ->
                JsonPrimitive(df.format(src))
            })
        return gsonBuilder.create()
    }
}