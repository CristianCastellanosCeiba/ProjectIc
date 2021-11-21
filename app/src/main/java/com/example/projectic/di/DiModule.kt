package com.example.projectic.di

import android.content.Context
import com.example.infrastructure.repository.AutoRepositoryImpl
import com.example.projectic.viewModel.AutoViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DiModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideAutoViewModelFactory() = AutoViewModelFactory(AutoRepositoryImpl(context))
}