package com.arshalif.paysera.data.di

import android.content.Context
import androidx.room.Room
import com.arshalif.paysera.data.db.Balance
import com.arshalif.paysera.data.db.BalanceDataBase
import com.arshalif.paysera.data.db.RoomBalance
import com.arshalif.paysera.data.db.daos.BalanceDAOs
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DBModule {

    @Binds
    abstract fun bindCache(cache: RoomBalance): Balance

    companion object {
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): BalanceDataBase {
            return Room.databaseBuilder(context, BalanceDataBase::class.java, "balances.db")
                .build()
        }

        @Provides
        fun provideBalancesDao(balanceDataBase: BalanceDataBase): BalanceDAOs =
            balanceDataBase.balancesDao()
    }
}