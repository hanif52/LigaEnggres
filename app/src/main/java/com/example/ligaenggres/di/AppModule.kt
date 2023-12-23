package com.example.ligaenggres.di

import com.example.ligaenggres.core.domain.usecase.ClubInteractor
import com.example.ligaenggres.core.domain.usecase.ClubUseCase
import com.example.ligaenggres.detail.DetailClubViewModel
import com.example.ligaenggres.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ClubUseCase> { ClubInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailClubViewModel(get()) }
}