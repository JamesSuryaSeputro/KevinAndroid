package com.beone.kevin.di

import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.ui.login.LoginsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<LoginsViewModel> {
        LoginsViewModel(get<RetrofitService>())
    }
}