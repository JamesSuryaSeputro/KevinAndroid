package com.beone.kevin.di

import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.ui.hrd.MenuHrdViewModel
import com.beone.kevin.ui.login.LoginsViewModel
import com.beone.kevin.ui.registertki.RegisterTkiViewModel
import com.beone.kevin.ui.user.userpembayaran.UserPembayaranViewModel
import com.beone.kevin.ui.user.uploaddocument.UploadDocumentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<LoginsViewModel> {
        LoginsViewModel(get<RetrofitService>())
    }

    viewModel<UserPembayaranViewModel> {
        UserPembayaranViewModel(get<RetrofitService>())
    }
    viewModel<MenuHrdViewModel> {
        MenuHrdViewModel(get<RetrofitService>())
    }

    viewModel<UploadDocumentViewModel> {
        UploadDocumentViewModel(get())
    }

    viewModel<RegisterTkiViewModel> {
        RegisterTkiViewModel(get<RetrofitService>())
    }
}