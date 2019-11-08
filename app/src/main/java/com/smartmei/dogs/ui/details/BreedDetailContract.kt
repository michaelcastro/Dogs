package com.smartmei.dogs.ui.details

interface BreedDetailContract {
    interface Presenter {
        fun getImagesBreed(breed: String)
        fun getNextImagesBreed(breed : String, totalItemCount: Int, lastVisibleItem: Int)
        fun atachView(view: View)
        fun detachView()
    }

    interface View {
        fun setBreedImagesList(imagesBreed: ArrayList<String>)
        fun addBreedImagesList(imagesBreed: ArrayList<String>)
        fun showMessage(message: String)
        fun showLoading()
        fun hideLoading()
    }
}