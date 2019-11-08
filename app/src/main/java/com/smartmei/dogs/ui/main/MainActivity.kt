package com.smartmei.dogs.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartmei.dogs.ApplicationApp
import com.smartmei.dogs.R
import com.smartmei.dogs.ui.adapter.BreedAdapter
import com.smartmei.dogs.ui.details.BreedDetailActivity
import com.smartmei.dogs.utils.Extras
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private var adapter: BreedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        configPresenter()
        configUi()
    }

    private fun injectDependency() {
        ApplicationApp.component.inject(this)
    }

    private fun configPresenter() {
        presenter.atachView(this)
        presenter.getAllBreeds()
    }

    private fun configUi() {
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        swipeToRefresh.setOnRefreshListener {
            presenter.getAllBreeds()
        }
    }

    override fun showLoading() {
        swipeToRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeToRefresh.isRefreshing = false
    }

    override fun updateBreedsList(breeds: ArrayList<String>) {
        adapter = BreedAdapter(breeds) { onClickIssue(it) }
        recyclerView.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    fun onClickIssue(breed: String) {
        val intent = Intent(this, BreedDetailActivity::class.java)
        intent.putExtra(Extras.BREED, breed)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
