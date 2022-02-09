package com.kristina.feedthebeast.ui.achievements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kristina.feedthebeast.R
import com.kristina.feedthebeast.database.achievements.Achievement
import com.kristina.feedthebeast.databinding.AchievementItemBinding
import java.text.SimpleDateFormat
import java.util.*

class AchievementsAdapter : RecyclerView.Adapter<AchievementsAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = AchievementItemBinding.bind(itemView)
        private val formatter = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault())

        fun bind(achievement: Achievement) = with(binding) {
            userName.text = "User: ${achievement.userName}"

            achievementName.text = "Achievement: ${achievement.name}"

            val time = formatter.format(achievement.dateMilli)
            date.text = "Date: $time"
        }
    }

    var data = listOf<Achievement>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.achievement_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() =
        data.size

}