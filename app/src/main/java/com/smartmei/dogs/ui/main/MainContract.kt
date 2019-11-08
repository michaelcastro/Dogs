package com.smartmei.dogs.ui.main

interface MainContract {

    interface Presenter {
        fun getAllBreeds()
        fun atachView(view: View)
        fun detachView()
    }

    interface View {
        fun showMessage(message: String)
        fun showLoading()
        fun hideLoading()
        fun updateBreedsList(breeds: ArrayList<String>)
    }

}