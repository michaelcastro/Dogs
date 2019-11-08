package com.smartmei.dogs.ui.main

import android.annotation.SuppressLint
import com.smartmei.dogs.data.source.BreedDataSource
import com.smartmei.dogs.data.source.remote.BreedRemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

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
                    val breedsList = ArrayList<String>()
                    it.message.iterator().forEach {
                        breedsList.add(it.key.substring(0,1).toUpperCase(Locale.ROOT)+it.key.substring(1))
                    }
                    mainView?.updateBreedsList(breedsList)
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

    override fun detachView() {
        mainView = null
    }

}