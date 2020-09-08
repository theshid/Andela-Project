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
import com.shid.andelapracticeproject.data.model.SkillItem
import com.shid.andelapracticeproject.ui.base.MainViewModelFactory
import com.shid.andelapracticeproject.ui.main.adapter.TopSkillAdapter
import com.shid.andelapracticeproject.ui.main.viewmodel.MainViewModel
import com.shid.andelapracticeproject.utils.Status
import com.shid.andelapracticeproject.utils.setDivider
import kotlinx.android.synthetic.main.fragment_top_learners.*
import kotlinx.android.synthetic.main.fragment_top_learners.progressBar
import kotlinx.android.synthetic.main.fragment_top_skillers.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopSkillersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopSkillersFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TopSkillAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top_skillers, container, false)
        setupViewModel()
        setupUI(view)
        setupObservers(view)
        return view


    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    companion object {
        fun create(): TopSkillersFragment {
            return TopSkillersFragment()
        }
    }

    private fun setupUI(view:View) {
        val mRecyclerView: RecyclerView = view!!.findViewById(R.id.recyclerView2)
        mRecyclerView.layoutManager = LinearLayoutManager(view.context)
        adapter = TopSkillAdapter(arrayListOf())
        /*mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                mRecyclerView.context,
                (mRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )*/
        //mRecyclerView.setDivider(R.drawable.recycler_view_divider)
        mRecyclerView.adapter = adapter
    }

    private fun setupObservers(view:View) {
        viewModel.getSkills().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView2.visibility = View.VISIBLE
                        progressBar2.visibility = View.GONE
                        resource.data?.let { skillers -> retrieveListSkills(skillers) }
                    }
                    Status.ERROR -> {
                        recyclerView2.visibility = View.VISIBLE
                        progressBar2.visibility = View.GONE
                        Toast.makeText(view.context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar2.visibility = View.VISIBLE
                        recyclerView2.visibility = View.GONE
                    }
                }
            }
        })

    }

    private fun retrieveListSkills(skillers: List<SkillItem>) {
        adapter.apply {
            addSkills(skillers)
            notifyDataSetChanged()
        }
    }
}