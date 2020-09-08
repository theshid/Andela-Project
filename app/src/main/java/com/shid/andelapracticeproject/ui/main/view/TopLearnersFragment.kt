package com.shid.andelapracticeproject.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.shid.andelapracticeproject.R
import com.shid.andelapracticeproject.data.api.ApiHelper
import com.shid.andelapracticeproject.data.api.RetrofitBuilder
import com.shid.andelapracticeproject.data.model.Skill
import com.shid.andelapracticeproject.data.model.TopLearners
import com.shid.andelapracticeproject.data.model.TopLearnersItem
import com.shid.andelapracticeproject.ui.base.MainViewModelFactory
import com.shid.andelapracticeproject.ui.main.adapter.TopLearnersAdapter
import com.shid.andelapracticeproject.ui.main.viewmodel.MainViewModel
import com.shid.andelapracticeproject.utils.Status
import kotlinx.android.synthetic.main.fragment_top_learners.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopLearnersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopLearnersFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TopLearnersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_top_learners, container, false)
        setupViewModel(view)
        setupUI(view)
        setupObservers()
        return view


    }

    companion object {
        fun create(): TopLearnersFragment {
            return TopLearnersFragment()
        }
    }

    private fun setupViewModel(view: View) {
        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI(view: View) {
        val mRecyclerView: RecyclerView = view!!.findViewById(R.id.searchRecycler)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TopLearnersAdapter(arrayListOf())
        mRecyclerView.adapter = adapter
        /*mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                mRecyclerView.context,
                (mRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )*/

    }

    private fun setupObservers() {

        viewModel.getTopLearners().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        searchRecycler.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { learners -> retrieveListSkills(learners) }
                    }
                    Status.ERROR -> {
                        searchRecycler.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        searchRecycler.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveListSkills(learner: List<TopLearnersItem>) {
        adapter.apply {
            addTopLearners(learner)
            notifyDataSetChanged()
        }
    }
}