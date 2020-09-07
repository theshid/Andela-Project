package com.shid.andelapracticeproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shid.andelapracticeproject.R
import com.shid.andelapracticeproject.data.model.SkillItem
import com.shid.andelapracticeproject.data.model.TopLearnersItem
import kotlinx.android.synthetic.main.list_item.view.*

class TopSkillAdapter(private val list:ArrayList<SkillItem>): RecyclerView.Adapter<TopSkillAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(learner: SkillItem) {
            itemView.apply {
                image.setImageResource(R.drawable.skill)
                applicant_name.text = learner.name
                hours.text = learner.score.toString()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSkillAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: TopSkillAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addSkills(skillers: List<SkillItem>) {
        this.list.apply {
            clear()
            addAll(skillers)
        }

    }
}