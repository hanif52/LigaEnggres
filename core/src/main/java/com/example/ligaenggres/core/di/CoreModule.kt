package com.example.ligaenggres.core.di

import androidx.room.Room
import com.example.ligaenggres.core.data.ClubRepository
import com.example.ligaenggres.core.data.source.local.LocalDataSource
import com.example.ligaenggres.core.data.source.local.room.ClubDatabase
import com.example.ligaenggres.core.data.source.remote.RemoteDataSource
import com.example.ligaenggres.core.data.source.remote.network.ApiService
import com.example.ligaenggres.core.domain.repository.IClubRepository
import com.example.ligaenggres.core.utils.AppExecutors
import net.sqlcipher.BuildConfig
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ClubDatabase>().clubDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("ligainggris".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            ClubDatabase::class.java, "Club"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/ljTDgm/k397r3IdZEKRul2NCPhqITZKGW8ue2nIVaGc=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()

        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://thesportsdb.com/api/v1/json/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IClubRepository> {
        ClubRepository(
            get(),
            get(),
            get()
        )
    }
}