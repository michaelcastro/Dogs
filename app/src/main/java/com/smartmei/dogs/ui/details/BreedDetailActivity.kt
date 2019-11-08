package com.smartmei.dogs.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smartmei.dogs.R
import com.smartmei.dogs.utils.Extras
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartmei.dogs.ApplicationApp
import com.smartmei.dogs.ui.adapter.BreedImageAdapter
import kotlinx.android.synthetic.main.activity_breed_detail.*
import javax.inject.Inject


class BreedDetailActivity : AppCompatActivity(), BreedDetailContract.View {

    @Inject
    lateinit var presenter: BreedDetailContract.Presenter

    private var adapter: BreedImageAdapter? = null
    private var breedName = ""
    private var breeds: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed_detail)
        injectDependency()
        configUi()
        configPresenter()
    }

    fun configUi(){
        if (intent.extras != null) {
            breedName = intent.extras!!.getString(Extras.BREED).toString()
            setTitle(breedName)
        }
    }

    private fun injectDependency() {
        ApplicationApp.component.inject(this)
    }

    private fun configPresenter() {
        presenter.atachView(this)
        presenter.getImagesBreed(this.breedName)
    }

    override fun setBreedImagesList(imagesBreed: ArrayList<String>) {
        imagesBreed.add(String())
        breeds = imagesBreed
        adapter = BreedImageAdapter(imagesBreed)
        recyclerView.adapter = adapter

        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                presenter.getNextImagesBreed(
                    breedName,
                    mLayoutManager.itemCount,
                    mLayoutManager.findLastVisibleItemPosition()
                )
            }
        })

        adapter?.notifyDataSetChanged()
    }

    override fun addBreedImagesList(imagesBreed: ArrayList<String>) {
        if (imagesBreed.isEmpty()) {
            (recyclerView.adapter as BreedImageAdapter).closeLoad()
        }
        imagesBreed.add(String())
        breeds.removeAt(breeds.lastIndex)
        breeds.addAll(imagesBreed)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        when (id) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
