package com.yagosouza.meucarro.di

import com.yagosouza.meucarro.core.tools.ToastHelper
import com.yagosouza.meucarro.core.tools.ToastHelperImpl
import com.yagosouza.meucarro.data.api.CarApi
import com.yagosouza.meucarro.data.api.CarApiImpl
import com.yagosouza.meucarro.data.repository.CarDetailRepositoryImpl
import com.yagosouza.meucarro.data.repository.CarRepositoryImpl
import com.yagosouza.meucarro.domain.repository.CarDetailRepository
import com.yagosouza.meucarro.domain.repository.CarRepository
import com.yagosouza.meucarro.domain.usecase.GetCarDetailUseCase
import com.yagosouza.meucarro.domain.usecase.GetCarDetailUseCaseImpl
import com.yagosouza.meucarro.domain.usecase.GetCarsUseCase
import com.yagosouza.meucarro.domain.usecase.GetCarsUseCaseImpl
import com.yagosouza.meucarro.presentation.detail.DetailViewModel
import com.yagosouza.meucarro.presentation.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    factory<CarApi> { CarApiImpl() }

    factory<CarRepository> { CarRepositoryImpl(api = get()) }
    factory<CarDetailRepository> { CarDetailRepositoryImpl(api = get()) }

    factory<GetCarsUseCase> { GetCarsUseCaseImpl(repository = get()) }
    factory<GetCarDetailUseCase> { GetCarDetailUseCaseImpl(repository = get()) }

    viewModel { HomeViewModel(getCarsUseCase = get()) }
    viewModel { (id: Long) -> DetailViewModel(getCarDetailUseCase = get(), id = id) }

    factory<ToastHelper> { ToastHelperImpl(context = androidContext()) }
}
