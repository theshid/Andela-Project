package com.shid.andelapracticeproject.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.shid.andelapracticeproject.R
import com.shid.andelapracticeproject.data.api.ApiHelper
import com.shid.andelapracticeproject.data.api.RetrofitBuilder
import com.shid.andelapracticeproject.data.model.Skill
import com.shid.andelapracticeproject.ui.base.MainViewModelFactory
import com.shid.andelapracticeproject.ui.main.adapter.ViewPagerAdapter
import com.shid.andelapracticeproject.ui.main.viewmodel.MainViewModel
import com.shid.andelapracticeproject.utils.ResourceStore
import com.shid.andelapracticeproject.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    //private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setUI()
        clickListeners()

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun  clickListeners(){
        btn_toolbar.setOnClickListener {
            val intent = Intent(this,SubmitActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUI() {

        view_pager.adapter = object : FragmentStateAdapter(this) {

            override fun createFragment(position: Int): Fragment {
                return ResourceStore.pagerFragments[position]
            }

            override fun getItemCount(): Int {
                return ResourceStore.tabList.size
            }
        }
        TabLayoutMediator(tablayout_id, view_pager) { tab, position ->
            tab.text = getString(ResourceStore.tabList[position])
        }.attach()



    }


}