package com.smartmei.dogs.ui.main

import com.smartmei.dogs.data.Breed

interface MainContract {

    interface Presenter {
        fun getAllBreeds()
        fun atachView(view: View)
    }

    interface View {
        fun showMessage(message: String)
        fun showLoading()
        fun hideLoading()
        fun updateBreedsList(breeds: ArrayList<String>)
    }

}