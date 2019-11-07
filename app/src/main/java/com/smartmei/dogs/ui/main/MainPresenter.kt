package com.smartmei.dogs.ui.main

import android.annotation.SuppressLint
import android.util.Log
import com.smartmei.dogs.data.source.BreedDataSource
import com.smartmei.dogs.data.source.remote.BreedRemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter: MainContract.Presenter {

    var mainView: MainContract.View? = null

    val issueDataSource: BreedDataSource = BreedRemoteDataSource()

    @SuppressLint("CheckResult")
    override fun getAllBreeds() {
        mainView?.showLoading()
        Observable.fromCallable {
            issueDataSource.getAllBreeds()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mainView?.hideLoading()
                it?.let {
                    Log.e("Breeds", it.toString())
                    Log.e("Breeds", it.message.toString())
                    //mainView?.updateBreedsList(it)
                }

            },
                {
                    mainView?.hideLoading()
                    it?.let {
                        mainView?.showMessage(it.message ?: "" )
                    }
                }
            )
    }

    override fun atachView(view: MainContract.View) {
        mainView = view
    }

}