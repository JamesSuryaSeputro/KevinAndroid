package com.beone.kevin.di

import com.beone.kevin.remote.RetrofitService
import com.beone.kevin.ui.hrd.homehrd.HomeHrdViewModel
import com.beone.kevin.ui.hrd.approvetkidocument.ApproveUserDocumentViewModel
import com.beone.kevin.ui.hrd.approvetkipayment.ApproveUserPaymentViewModel
import com.beone.kevin.ui.hrd.checkuserdocument.CheckDocumentViewModel
import com.beone.kevin.ui.hrd.checkuserpayment.CheckPembayaranViewModel
import com.beone.kevin.ui.hrd.profilehrd.ProfileHrdViewModel
import com.beone.kevin.ui.login.LoginsViewModel
import com.beone.kevin.ui.pelatih.addnilai.AddNilaiViewModel
import com.beone.kevin.ui.pelatih.coachdetailschedule.CoachDetailScheduleViewModel

import com.beone.kevin.ui.pelatih.presensi.PresensiViewModel
import com.beone.kevin.ui.pelatih.presensiujian.PresensiUjianViewModel
import com.beone.kevin.ui.pelatih.profilecoach.ProfilePelatihViewModel

import com.beone.kevin.ui.pelatih.schedulepelatih.SchedulePelatihViewModel
import com.beone.kevin.ui.pelatih.selecttkifortraining.SelectTkiForTrainingViewModel
import com.beone.kevin.ui.registercoach.RegisterCoachViewModel
import com.beone.kevin.ui.registeremployee.RegisterEmployeeViewModel
import com.beone.kevin.ui.registertki.RegisterTkiViewModel
import com.beone.kevin.ui.user.profileuser.ProfileUserViewModel
import com.beone.kevin.ui.user.scheduleuser.ScheduleUserViewModel
import com.beone.kevin.ui.user.scoreview.ScoreViewViewModel
import com.beone.kevin.ui.user.uploaddocument.UploadDocumentViewModel
import com.beone.kevin.ui.user.userpembayaran.UserPembayaranViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel<LoginsViewModel> {
        LoginsViewModel(get<RetrofitService>())
    }

    viewModel<UserPembayaranViewModel> {
        UserPembayaranViewModel(get<RetrofitService>())
    }

    viewModel<HomeHrdViewModel> {
        HomeHrdViewModel(get<RetrofitService>())
    }

    viewModel<UploadDocumentViewModel> {
        UploadDocumentViewModel(get())
    }

    viewModel<RegisterTkiViewModel> {
        RegisterTkiViewModel(get<RetrofitService>())
    }

    viewModel<RegisterCoachViewModel> {
        RegisterCoachViewModel(get<RetrofitService>())
    }

    viewModel<RegisterEmployeeViewModel> {
        RegisterEmployeeViewModel(get<RetrofitService>())
    }

    viewModel<ScoreViewViewModel> {
        ScoreViewViewModel(get())
    }

    viewModel<ScheduleUserViewModel> {
        ScheduleUserViewModel(get())
    }

    viewModel<SchedulePelatihViewModel> {
        SchedulePelatihViewModel(get())
    }

    viewModel<CoachDetailScheduleViewModel> {
        CoachDetailScheduleViewModel(get())
    }

    viewModel<SelectTkiForTrainingViewModel> {
        SelectTkiForTrainingViewModel(get())
    }

    viewModel<AddNilaiViewModel> {
        AddNilaiViewModel(get())
    }

    viewModel<PresensiViewModel> {
        PresensiViewModel(get())
    }

    viewModel<PresensiUjianViewModel> {
        PresensiUjianViewModel(get())
    }

    viewModel<ProfilePelatihViewModel> {
        ProfilePelatihViewModel(get())
    }

    viewModel<CheckDocumentViewModel>{
        CheckDocumentViewModel(get())
    }

    viewModel<ApproveUserDocumentViewModel>{
        ApproveUserDocumentViewModel(get())
    }

    viewModel<CheckPembayaranViewModel>{
        CheckPembayaranViewModel(get())
    }

    viewModel<ApproveUserPaymentViewModel>{
        ApproveUserPaymentViewModel(get())
    }

    viewModel<ProfileHrdViewModel>{
        ProfileHrdViewModel(get())
    }

    viewModel<ProfileUserViewModel>{
        ProfileUserViewModel(get())
    }
}
