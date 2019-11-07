package com.smartmei.dogs.di

import com.smartmei.dogs.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(Modulo::class))
interface Componente {
    fun inject(mainActivity: MainActivity)
}