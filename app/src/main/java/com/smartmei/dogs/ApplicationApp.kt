package com.smartmei.dogs

import android.app.Application
import com.smartmei.dogs.di.Componente
import com.smartmei.dogs.di.DaggerComponente
import com.smartmei.dogs.di.Modulo

class ApplicationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setup()
    }

    fun setup() {
        component = DaggerComponente.builder().modulo(Modulo()).build()
    }

    companion object {
        lateinit var component: Componente
    }

}