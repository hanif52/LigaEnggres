package com.example.ligaenggres

import android.app.Application
import com.example.ligaenggres.core.di.databaseModule
import com.example.ligaenggres.core.di.networkModule
import com.example.ligaenggres.core.di.repositoryModule
import com.example.ligaenggres.di.useCaseModule
import com.example.ligaenggres.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}