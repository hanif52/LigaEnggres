package com.example.ligaenggres.core.di

import androidx.room.Room
import com.example.ligaenggres.core.data.ClubRepository
import com.example.ligaenggres.core.data.source.local.LocalDataSource
import com.example.ligaenggres.core.data.source.local.room.ClubDatabase
import com.example.ligaenggres.core.data.source.remote.RemoteDataSource
import com.example.ligaenggres.core.data.source.remote.network.ApiService
import com.example.ligaenggres.core.domain.repository.IClubRepository
import com.example.ligaenggres.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
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
            ClubDatabase::class.java, "Club.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
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