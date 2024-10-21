package com.xsoft.satori.core.data.di

import androidx.paging.ExperimentalPagingApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

}