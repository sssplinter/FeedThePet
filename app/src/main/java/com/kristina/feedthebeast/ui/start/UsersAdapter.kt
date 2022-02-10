package com.kristina.feedthebeast.ui.start

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kristina.feedthebeast.R
import com.kristina.feedthebeast.database.users.User
import com.kristina.feedthebeast.databinding.UserItemBinding

class UsersAdapter() : RecyclerView.Adapter<UsersAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = UserItemBinding.bind(itemView)

        fun bind(user: User) = with(binding) {
            userName.text = user.name
        }
    }

    var data = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.user_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener{
            val userClickListener = UserClickListener(data[position].name)
            userClickListener.onClick(holder.itemView)
        }
    }

    override fun getItemCount() =
        data.size
}