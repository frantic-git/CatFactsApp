package com.frantic.catfactsapp.presentation.catfactslistfragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.frantic.catfactsapp.App
import com.frantic.catfactsapp.R
import com.frantic.catfactsapp.data.repository.CatFactsRepository
import com.frantic.catfactsapp.other.navigation.Screens
import kotlinx.android.synthetic.main.fragment_cat_facts_list.*
import kotlinx.android.synthetic.main.fragment_cat_facts_list.view.*
import java.lang.Exception

class CatFactsListFragment : MvpAppCompatFragment(), CatFactsListMvpView {

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.favorite) {
            App.fragmentRouter.replace(Screens.FRAGMENTS.CAT_FAVORITE_LIST_FRAGMENT, null)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun refresh() {
    }

    override fun setItemList(itemsList: MutableList<CatFactItem>) {
        adapter.setItemsList(itemsList)
    }

    override fun updatePreference(adapterPosition: Int, preference: Boolean) {
        adapter.updateItemPreference(adapterPosition, preference)
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