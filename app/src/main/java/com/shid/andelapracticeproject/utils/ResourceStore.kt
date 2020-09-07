package com.shid.andelapracticeproject.utils

import com.shid.andelapracticeproject.R
import com.shid.andelapracticeproject.ui.main.view.TopLearnersFragment
import com.shid.andelapracticeproject.ui.main.view.TopSkillersFragment

interface ResourceStore {

    companion object {
        val tabList = listOf(
            R.string.tab1, R.string.tab2
        )
        val pagerFragments = listOf(
            TopLearnersFragment.create(), TopSkillersFragment.create())
    }
}