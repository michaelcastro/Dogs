package com.smartmei.dogs.ui.details

import android.annotation.SuppressLint
import com.smartmei.dogs.data.source.BreedDataSource
import com.smartmei.dogs.data.source.remote.BreedRemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class BreedDetailPresenter: BreedDetailContract.Presenter {

    var view: BreedDetailContract.View? = null

    val issueDataSource: BreedDataSource = BreedRemoteDataSource()

    private var pagination = 10
    private var isLoading = false

    @SuppressLint("CheckResult")
    override fun getNextImagesBreed(breed: String, totalItemCount: Int, lastVisibleItem: Int) {
        if (lastVisibleItem == (totalItemCount - 1) && !isLoading ) {
            isLoading = true
            Observable.fromCallable {
                issueDataSource.getBreedImages(breed.toLowerCase(Locale.ROOT), pagination)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading = false
                    it?.let {
                        view?.addBreedImagesList(it.message)
                    }
                },
                    {
                        isLoading = false
                        it?.let {
                            view?.showMessage(it.message ?: "")
                        }
                    }
                )
        }
    }

    @SuppressLint("CheckResult")
    override fun getImagesBreed(breed: String) {
            view?.showLoading()
            Observable.fromCallable {
                issueDataSource.getBreedImages(breed.toLowerCase(Locale.ROOT), pagination)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.hideLoading()
                    it?.let {
                        view?.setBreedImagesList(it.message)
                    }
                },
                    {
                        view?.hideLoading()
                        it?.let {
                            view?.showMessage(it.message ?: "")
                        }
                    }
                )
    }

    override fun atachView(view: BreedDetailContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}