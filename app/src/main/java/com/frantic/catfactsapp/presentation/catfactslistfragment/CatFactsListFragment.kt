package com.frantic.catfactsapp.presentation.catfactslistfragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.frantic.catfactsapp.R
import kotlinx.android.synthetic.main.fragment_cat_facts_list.*
import kotlinx.android.synthetic.main.fragment_cat_facts_list.view.*
import java.lang.Exception

class CatFactsListFragment :  MvpAppCompatFragment(), CatFactsListMvpView {

    @InjectPresenter
    lateinit var presenter: CatFactsListPresenter

    private val adapter = CatFactsListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cat_facts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        setHasOptionsMenu(true)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
        presenter.getCatFactsList()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.cat_facts_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun refresh() {
    }

    override fun setItemList(itemsList: MutableList<CatFactItem>) {
        adapter.setItemsList(itemsList)
    }

    override fun showDataBaseException(e: Exception) {
        Log.d("db_exception", e.toString())
        Toast.makeText(activity,e.toString(),Toast.LENGTH_LONG).show()
    }

    override fun onStartLoading() {
        facts_progressBar.visibility = View.VISIBLE
    }

    override fun onFinishLoading() {
        facts_progressBar.visibility = View.GONE
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