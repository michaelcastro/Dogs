package com.smartmei.dogs.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartmei.dogs.ApplicationApp
import com.smartmei.dogs.R
import com.smartmei.dogs.data.Breed
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

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
       // adapter = IssueAdapter(breeds) { onClickIssue(it) }
      //  recyclerView.adapter = adapter
      //  adapter.notifyDataSetChanged()
    }

    fun onClickIssue(breed: Breed) {
        //val intent = Intent(this, IssueDetailActivity::class.java)
       // intent.putExtra(Extras.ISSUE_NUMBER, issue.number)
        //startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
