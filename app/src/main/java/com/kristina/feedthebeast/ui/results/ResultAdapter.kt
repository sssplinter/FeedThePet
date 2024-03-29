package com.kristina.feedthebeast.ui.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kristina.feedthebeast.R
import com.kristina.feedthebeast.database.feedingData.Feeding
import com.kristina.feedthebeast.databinding.ResultItemBinding
import java.text.SimpleDateFormat
import java.util.*

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ResultItemBinding.bind(itemView)
        private val formatter = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault())

        fun bind(result: Feeding) = with(binding) {
            userName.text = "User: ${result.userName}"
            resultScore.text = "Score: ${result.score.toString()}"

            val time = formatter.format(result.dateMilli)
            date.text = "Date: $time"
        }
    }

    var data = listOf<Feeding>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.result_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}