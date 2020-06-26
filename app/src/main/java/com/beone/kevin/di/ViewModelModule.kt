package com.beone.kevin.di

import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.ui.hrd.MenuHrdViewModel
import com.beone.kevin.ui.login.LoginsViewModel

import com.beone.kevin.ui.registertki.RegisterTkiViewModel
import com.beone.kevin.ui.user.userpembayaran.UserPembayaranViewModel
import com.beone.kevin.ui.pelatih.addschedulepelatih.AddSchedulePelatihViewModel
import com.beone.kevin.ui.pelatih.MainMenu.MainMenuPelatihViewModel
import com.beone.kevin.ui.pelatih.addnilai.AddNilaiViewModel
import com.beone.kevin.ui.pelatih.schedulepelatih.SchedulePelatihFragment
import com.beone.kevin.ui.pelatih.schedulepelatih.SchedulePelatihViewModel
import com.beone.kevin.ui.pelatih.selecttkifortraining.SelectTkiForTrainingViewModel
import com.beone.kevin.ui.user.mainuser.MainUserViewModel
import com.beone.kevin.ui.user.scheduleuser.ScheduleUserViewModel
import com.beone.kevin.ui.user.scoreview.ScoreViewViewModel
import com.beone.kevin.ui.user.uploaddocument.UploadDocumentViewModel
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
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

    viewModel<ScoreViewViewModel> {
        ScoreViewViewModel(get())
    }

    viewModel<MainUserViewModel> {
        MainUserViewModel(get())
    }

    viewModel<ScheduleUserViewModel> {
        ScheduleUserViewModel(get())
    }

    viewModel<MainMenuPelatihViewModel> {
        MainMenuPelatihViewModel(get())
    }

    viewModel<SchedulePelatihViewModel> {
        SchedulePelatihViewModel(get())
    }

    viewModel<SelectTkiForTrainingViewModel>{
        SelectTkiForTrainingViewModel(get())
    }

    viewModel<AddNilaiViewModel>{
        AddNilaiViewModel(get())
    }
}
