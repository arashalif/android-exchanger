package com.arshalif.paysera.view.di

import com.arshalif.paysera.data.repositories.BalanceRepositoryImpl
import com.arshalif.paysera.data.repositories.RatioRepositoryImpl
import com.arshalif.paysera.domain.repositories.BalanceRepository
import com.arshalif.paysera.domain.repositories.RatioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ViewModelsModule {

    @Binds
    abstract fun bindBalanceRepository(repository: BalanceRepositoryImpl): BalanceRepository

    @Binds
    abstract fun bindRatioRepository(repository: RatioRepositoryImpl): RatioRepository

}