package com.shid.andelapracticeproject.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shid.andelapracticeproject.ui.main.view.TopLearnersFragment
import com.shid.andelapracticeproject.ui.main.view.TopSkillersFragment

class ViewPagerAdapter internal constructor(fm: FragmentManager) :
    FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val titleList: MutableList<Fragment> = ArrayList()

    private val count = 2
    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                TopLearnersFragment()
            }
            1 -> {
                TopSkillersFragment()
            }
            else -> {
                TopLearnersFragment()
            }
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {return "Learning Leaders"}
            1 -> {return "Skill IQ Leaders"}
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        return count
    }

}