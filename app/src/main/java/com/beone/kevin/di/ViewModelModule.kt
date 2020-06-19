package com.beone.kevin.di

import com.beone.kevin.SharedPreferenceUtils
import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.ui.login.LoginsViewModel
import com.beone.kevin.ui.register.RegisterFragment
import com.beone.kevin.ui.register.RegisterViewModel
import com.beone.kevin.ui.user.UserPembayaranViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<LoginsViewModel> {
        LoginsViewModel(get<RetrofitService>())
    }
    viewModel<RegisterViewModel> {
        RegisterViewModel(get<RetrofitService>())
    }
    viewModel<UserPembayaranViewModel> {
        UserPembayaranViewModel(get<RetrofitService>())
    }

}