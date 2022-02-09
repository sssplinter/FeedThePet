package com.kristina.feedthebeast.ui.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kristina.feedthebeast.R
import com.kristina.feedthebeast.databinding.ResultItemBinding
import com.kristina.feedthebeast.databinding.TextItemViewBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ResultItemBinding.bind(itemView)
        private val formatter = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault())

        fun bind(result: Res) = with(binding) {
            userName.text = "User: ${result.userName}"
            resultScore.text = "Score: ${result.score.toString()}"

            val time = formatter.format(result.time)
            date.text = "Date: $time"
        }
    }

    var data = ArrayList<Res>()//MutableList<R>(Res("krisa", 5, 2L), Res("sosi", 2, 8L))
        set(value) {
            field = value
            // todo not the best choice
            notifyDataSetChanged()
        }

    //to tell the RV when the data it's displaying has changed

    // RV doesn't care about views. Do smth only with ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.result_item, parent, false)
        return ItemViewHolder(view)
    }

    // recycler view reuse viewHolders after an item had scrolled off the screen
    // will update the viewHolder contents to the ITEM DATA[POSITION]
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
//
//
    fun add() {
        data.add(Res("krisa", 4, 123334545L))
        notifyDataSetChanged()
    }
}