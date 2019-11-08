package com.smartmei.dogs.di

import com.smartmei.dogs.ui.details.BreedDetailContract
import com.smartmei.dogs.ui.details.BreedDetailPresenter
import com.smartmei.dogs.ui.main.MainContract
import com.smartmei.dogs.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class Modulo {
    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    @Provides
    fun providePresenterBreed(): BreedDetailContract.Presenter {
        return BreedDetailPresenter()
    }
}