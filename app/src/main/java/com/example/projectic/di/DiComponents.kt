package com.example.projectic.di

import com.example.projectic.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DiModule::class])
interface DiComponents {
    fun inject(mainActivity: MainActivity)
}