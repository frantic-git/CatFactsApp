package com.frantic.catfactsapp.presentation.catfavoritelistfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.frantic.catfactsapp.R
import com.frantic.catfactsapp.presentation.catfactslistfragment.CatFactItem
import kotlinx.android.synthetic.main.fragment_cat_favorite_list.*

class CatFavoriteListFragment:  MvpAppCompatFragment(), CatFavoriteListMvpView {

    @InjectPresenter
    lateinit var presenter: CatFavoriteListPresenter

    private val adapter = CatFavoriteListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cat_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
        presenter.getCatFavoriteList()
    }

    override fun refresh() {
    }

    override fun setItemList(itemsList: MutableList<CatFactItem>) {
        adapter.setItemsList(itemsList)
    }

    override fun showDataBaseException(e: Exception) {
        Log.d("db_exception", e.toString())
    }

    override fun onStartLoading() {
        facts_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun onFinishLoading() {
        facts_progressBar.visibility = ProgressBar.GONE
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}