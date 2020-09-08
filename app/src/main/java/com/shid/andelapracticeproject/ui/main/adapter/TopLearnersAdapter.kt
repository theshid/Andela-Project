package com.shid.andelapracticeproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shid.andelapracticeproject.R
import com.shid.andelapracticeproject.data.model.TopLearnersItem
import kotlinx.android.synthetic.main.list_item.view.*

class TopLearnersAdapter(private val list:ArrayList<TopLearnersItem>): RecyclerView.Adapter<TopLearnersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(learner: TopLearnersItem) {
            itemView.apply {
                image.setImageResource(R.drawable.top_learner)
                applicant_name.text = learner.name
                hours.text = learner.hours.toString() + " " + "learning hours,"+" " + learner.country+"."
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopLearnersAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: TopLearnersAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addTopLearners(learner: List<TopLearnersItem>) {
        this.list.apply {
            clear()
            addAll(learner)
        }

    }
}