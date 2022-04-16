package com.arshalif.paysera.view.di

import com.arshalif.paysera.data.repositories.BalanceRepositoryImpl
import com.arshalif.paysera.domain.repositories.BalanceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ViewModelsModule {

    @Binds
    abstract fun bindBalanceRepository(repository: BalanceRepositoryImpl): BalanceRepository

}